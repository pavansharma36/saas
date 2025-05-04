package io.github.pavansharma36.saas.core.dto.common;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CreateUserDto {

  @Length(min = 2, max = 64)
  @NotBlank
  private String username;

  @Valid
  @NotNull
  private UserDto userInfo;

}
