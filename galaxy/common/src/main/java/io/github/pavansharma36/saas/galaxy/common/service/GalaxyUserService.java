package io.github.pavansharma36.saas.galaxy.common.service;

import io.github.pavansharma36.auth.api.UserAccountApi;
import io.github.pavansharma36.core.common.context.UserContext;
import io.github.pavansharma36.core.common.context.providers.TenantContextProvider;
import io.github.pavansharma36.core.common.context.providers.UserContextProvider;
import io.github.pavansharma36.core.common.service.UserService;
import io.github.pavansharma36.saas.auth.client.AuthClientFactory;
import io.github.pavansharma36.saas.core.dto.common.CreateUserDto;
import io.github.pavansharma36.saas.core.dto.common.TenantDto;
import io.github.pavansharma36.saas.core.dto.common.UserAccountDto;
import io.github.pavansharma36.saas.core.dto.common.UserDto;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.dao.UserInfoDao;
import io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model.UserInfo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GalaxyUserService implements UserService {

  private final UserInfoDao userInfoDao;
  private final GalaxyTenantService tenantService;
  private final UserAccountApi userAccountApi = AuthClientFactory.userAccountApi();

  public UserDto createUser(CreateUserDto userDto) {
    String tenantId = TenantContextProvider.getInstance().get().map(TenantDto::getId).orElse(null);
    String newUserId = userInfoDao.getIdGenerator().nextId(UserInfo.class);

    UserAccountDto accountDto = new UserAccountDto();
    accountDto.setId(newUserId);
    accountDto.setUsername(userDto.getUsername());

    UserInfo userInfo = new UserInfo();
    if (tenantId == null &&
        userDto.getUserInfo().getTenantId() != null) {
      TenantDto tenantDto = tenantService.getTenantById(userDto.getUserInfo().getTenantId());
      TenantContextProvider.executeOnTenantContext(tenantDto,
          () -> userAccountApi.createUserAccount(accountDto));

      userInfo.setTenantId(userDto.getUserInfo().getTenantId());
    } else {
      userAccountApi.createUserAccount(accountDto);
    }

    userInfo.setId(newUserId);
    userInfo.setFirstName(userDto.getUserInfo().getFirstName());
    userInfo.setLastName(userDto.getUserInfo().getLastName());
    userInfo.setEnabled(true);
    userInfoDao.insert(userInfo);

    userDto.getUserInfo().setId(newUserId);
    return userDto.getUserInfo();
  }

  @Override
  public UserDto getUserById(String id) {
    if ("system".equals(id)) {
      UserDto dto = new UserDto();
      dto.setId("system");
      dto.setFirstName("system");
      dto.setLastName("system");
      dto.setEnabled(true);
      return dto;
    }
    UserInfo userInfo = userInfoDao.findByIdOrThrow(id);
    UserDto dto = new UserDto();
    dto.setId(userInfo.getId());
    dto.setFirstName(userInfo.getFirstName());
    dto.setLastName(userInfo.getLastName());
    dto.setEnabled(userInfo.isEnabled());
    return dto;
  }


  @PostConstruct
  public void postConstruct() {
    UserContextProvider.register(new UserContext(this));
  }
}
