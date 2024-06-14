package io.github.pavansharma36.saas.authserver.dto;

import lombok.Data;

@Data
public class LoginDto {
  private String username;
  private String password;
}
