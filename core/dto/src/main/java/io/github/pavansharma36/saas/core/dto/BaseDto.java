package io.github.pavansharma36.saas.core.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseDto implements Serializable {
  protected String id;
  protected String createdBy;
  protected Date createdAt;
}
