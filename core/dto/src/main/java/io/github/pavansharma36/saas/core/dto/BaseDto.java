package io.github.pavansharma36.saas.core.dto;

import io.github.pavansharma36.saas.utils.json.JsonUtils;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto implements Serializable {
  protected String id;
  protected String createdBy;
  protected Date createdAt;

  @Override
  public String toString() {
    return JsonUtils.toJson(this);
  }
}
