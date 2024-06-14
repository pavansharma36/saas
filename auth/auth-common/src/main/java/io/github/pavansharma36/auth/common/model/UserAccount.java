package io.github.pavansharma36.auth.common.model;

import io.github.pavansharma36.saas.core.dao.common.Model;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccount implements Model {
  private String id;
  private String username;
  private String password;
  private boolean enabled;
  private boolean accountNonExpired;
  private boolean credentialsNonExpired;
  private boolean accountNonLocked;
  private String createdBy;
  private Date createdAt;
}
