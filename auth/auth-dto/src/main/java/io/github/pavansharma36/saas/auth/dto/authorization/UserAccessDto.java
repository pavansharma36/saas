package io.github.pavansharma36.saas.auth.dto.authorization;

import io.github.pavansharma36.saas.core.dto.BaseDto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccessDto extends BaseDto {
  private List<ResourceAccessDto> resources;
}
