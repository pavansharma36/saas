package io.github.pavansharma36.saas.core.dao.mybatis.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
@Setter
public class MyBatisModel extends BaseMyBatisModel {
  private String updatedBy;
  private Date updatedAt;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
