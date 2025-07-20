package io.github.pavansharma36.saas.search.dto;

import io.github.pavansharma36.saas.core.dto.Dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class TenantDto extends Dto {

  private String name;
  private String description;

  @Size(min = 2, max = 6, message = "{validation.tenant.code.size}")
  @NotBlank(message = "{validation.tenant.code.notBlank}")
  private String code;
}
