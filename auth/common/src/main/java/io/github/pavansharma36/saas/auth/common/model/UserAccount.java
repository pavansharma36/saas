package io.github.pavansharma36.saas.auth.common.model;

import io.github.pavansharma36.saas.core.dao.common.model.Model;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserAccount implements Model {
  private String id;
  private String username;
  @ToString.Exclude
  private String password;
  private boolean enabled;
  private boolean accountNonExpired;
  private boolean credentialsNonExpired;
  private boolean accountNonLocked;
  private String createdBy;
  private Date createdAt;
}
