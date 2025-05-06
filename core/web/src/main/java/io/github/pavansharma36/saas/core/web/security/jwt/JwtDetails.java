package io.github.pavansharma36.saas.core.web.security.jwt;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtDetails {
  private String username;
  private Date expireAt;
  private JwtPayload payload;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class JwtPayload {
    private String userId;
    private String tenantId;
    private List<String> roles;
  }
}
