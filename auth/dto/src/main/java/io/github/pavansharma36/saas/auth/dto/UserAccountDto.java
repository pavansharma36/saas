package io.github.pavansharma36.saas.auth.dto;

import io.github.pavansharma36.saas.core.dto.Dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserAccountDto extends Dto {

  public static final String FIELD_USERNAME = "username";

  @NotBlank
  @Length(min = 2, max = 64)
  private String username;

  @NotBlank
  private String userInfoId;
}
