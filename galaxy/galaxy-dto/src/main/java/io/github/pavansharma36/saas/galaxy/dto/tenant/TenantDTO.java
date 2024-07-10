package io.github.pavansharma36.saas.galaxy.dto.tenant;

import io.github.pavansharma36.saas.core.dto.BaseDTO;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class TenantDTO extends BaseDTO {
  private String name;
  private String code;
  private int incrementalId;
  private Map<String, String> configs;
}
