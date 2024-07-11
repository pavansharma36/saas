package io.github.pavansharma36.saas.core.dao.mybatis.model;

import io.github.pavansharma36.saas.core.dao.common.Model;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
@Setter
public class MyBatisModel implements Model {
  protected String id;
  protected String createdBy;
  protected Date createdAt;
  protected String updatedBy;
  protected Date updatedAt;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
