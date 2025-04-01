package io.github.pavansharma36.saas.auth.dto.authorization;

import io.github.pavansharma36.saas.core.dto.Dto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceAccessDto extends Dto {
  private String name;
  private List<String> actions;
}
