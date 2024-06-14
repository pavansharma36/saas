package io.github.pavansharma36.auth.common.model;

import io.github.pavansharma36.saas.core.dao.common.Model;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role implements Model {
  private String id;
  private String role;
  private String createdBy;
  private Date createdAt;
}
