package io.github.pavansharma36.saas.core.dto.tenant;

import io.github.pavansharma36.saas.core.dto.Dto;
import jakarta.validation.constraints.NotBlank;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class TenantDto extends Dto {
  @NotBlank
  private String name;
  @NotBlank
  private String code;
  private int incrementalId;
  private Map<String, String> configs;
}
