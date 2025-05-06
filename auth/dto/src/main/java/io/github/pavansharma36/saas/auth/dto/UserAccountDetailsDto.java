package io.github.pavansharma36.saas.auth.dto;

import io.github.pavansharma36.saas.core.dto.common.UserAccountDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccountDetailsDto extends UserAccountDto {
  private String password;
  private boolean accountNonExpired;
  private boolean credentialsNonExpired;
  private boolean accountNonLocked;
}
