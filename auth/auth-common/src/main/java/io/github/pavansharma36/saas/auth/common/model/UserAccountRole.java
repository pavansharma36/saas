package io.github.pavansharma36.saas.auth.common.model;

import io.github.pavansharma36.saas.core.dao.common.Model;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccountRole implements Model {
  private String id;
  private String userAccountId;
  private String roleId;
  private String createdBy;
  private Date createdAt;
}
