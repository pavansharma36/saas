package io.github.pavansharma36.saas.core.dto.tenant;

import io.github.pavansharma36.saas.core.dto.Dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class TenantDto extends Dto {

  public static final String CACHE_NAME = "tenant-dto";

  private String name;

  @Size(min = 2, max = 6, message = "{validation.tenant.code.size}")
  @NotBlank(message = "{validation.tenant.code.notBlank}")
  private String code;
  private int incrementalId;
  private Map<String, String> configs;
}
