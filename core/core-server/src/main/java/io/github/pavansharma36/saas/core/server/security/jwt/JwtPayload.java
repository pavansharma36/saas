package io.github.pavansharma36.saas.core.server.security.jwt;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtPayload {
  private String username;
  private List<String> roles;
  private Date expireAt;
}
