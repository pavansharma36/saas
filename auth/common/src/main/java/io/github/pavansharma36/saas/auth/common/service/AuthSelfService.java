package io.github.pavansharma36.saas.auth.common.service;

import io.github.pavansharma36.core.common.context.providers.RequestInfoContextProvider;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.dao.UserGroupMapDao;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.dao.UserGroupResourcesDao;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.model.UserGroupMap;
import io.github.pavansharma36.saas.auth.common.dao.mybatis.model.UserGroupResources;
import io.github.pavansharma36.saas.auth.dto.authorization.UserAccessDto;
import io.github.pavansharma36.saas.utils.collections.CollectionUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthSelfService {

  private final UserGroupMapDao userGroupMapDao;
  private final UserGroupResourcesDao userGroupResourcesDao;

  public UserAccessDto userAccess() {
    Set<String> userGroups = userGroupMapDao.findByUserId(
        RequestInfoContextProvider.getInstance().getOrThrow().getUserId()).stream().map(
        UserGroupMap::getUserGroupId).collect(Collectors.toSet());

    List<UserGroupResources> resources = userGroupResourcesDao.findByUserGroupIds(userGroups);

    Map<String, Set<String>> actions = new HashMap<>();
    for (UserGroupResources resource : resources) {
      actions.computeIfAbsent(resource.getResourceName(), ignore -> new HashSet<>())
          .addAll(CollectionUtils.nullSafe(resource.getAllowedActions()));
    }

    UserAccessDto accessDto = new UserAccessDto();
    accessDto.setResourceActionsMap(actions);
    return accessDto;
  }

}
