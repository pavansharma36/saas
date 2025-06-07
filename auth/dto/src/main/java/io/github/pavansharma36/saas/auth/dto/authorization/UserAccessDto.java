package io.github.pavansharma36.saas.auth.dto.authorization;

import io.github.pavansharma36.saas.core.dto.BaseDto;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccessDto extends BaseDto {
  private Map<String, Set<String>> resourceActionsMap;
}
