package io.github.pavansharma36.saas.core.dto.tenant;

import io.github.pavansharma36.saas.core.dto.Dto;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class TenantDto extends Dto {
  private String name;
  private String code;
  private int incrementalId;
  private Map<String, String> configs;
}
