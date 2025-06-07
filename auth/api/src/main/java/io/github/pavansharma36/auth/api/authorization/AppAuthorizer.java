package io.github.pavansharma36.auth.api.authorization;


import io.github.pavansharma36.saas.auth.dto.authorization.UserAccessDto;
import io.github.pavansharma36.saas.utils.collections.CollectionUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("auth")
public class AppAuthorizer {

  private final UserAccessDtoProvider accessDtoProvider;
  private final Map<String, CustomAuthorizer> customAuthorizerMap;

  public AppAuthorizer(UserAccessDtoProvider accessDtoProvider,
                       List<CustomAuthorizer> customAuthorizers) {
    this.accessDtoProvider = accessDtoProvider;
    this.customAuthorizerMap = new HashMap<>(customAuthorizers.size());
    customAuthorizers.forEach(c -> customAuthorizerMap.put(c.resource(), c));
  }


  public boolean hasAccess(String resource, String action) {
    log.info("Checking permission for {} : {}", resource, action);

    UserAccessDto accessDto = accessDtoProvider.access();
    if (accessDto == null ||
        !CollectionUtils.nullSafeMap(accessDto.getResourceActionsMap()).getOrDefault(resource,
            Collections.emptySet()).contains(action)) {
      return false;
    }

    if (customAuthorizerMap.containsKey(resource)) {
      return customAuthorizerMap.get(resource).hasAccess(action);
    }

    return true;
  }

  public boolean hasAccess(String resource, String entityId, String action) {
    log.info("Checking permission for {} : {} on {}", resource, action, entityId);

    UserAccessDto accessDto = accessDtoProvider.access();
    if (accessDto == null ||
        !CollectionUtils.nullSafeMap(accessDto.getResourceActionsMap()).getOrDefault(resource,
            Collections.emptySet()).contains(action)) {
      return false;
    }

    if (customAuthorizerMap.containsKey(resource)) {
      return customAuthorizerMap.get(resource).hasAccess(entityId, action);
    }

    return true;
  }

}
