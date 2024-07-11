package io.github.pavansharma36.saas.core.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDTO implements Serializable {
  protected String id;
  protected String createdBy;
  protected Date createdAt;
}
