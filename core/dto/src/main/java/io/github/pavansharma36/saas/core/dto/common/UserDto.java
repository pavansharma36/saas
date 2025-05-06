package io.github.pavansharma36.saas.core.dto.common;

import io.github.pavansharma36.saas.core.dto.Dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserDto extends Dto {
  @NotBlank
  @Length(min = 2, max = 64)
  private String firstName;
  @NotBlank
  @Length(min = 2, max = 64)
  private String lastName;
  private String tenantId;

  private boolean enabled;
}
