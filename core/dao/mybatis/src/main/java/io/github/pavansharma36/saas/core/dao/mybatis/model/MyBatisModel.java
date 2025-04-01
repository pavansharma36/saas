package io.github.pavansharma36.saas.core.dao.mybatis.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
@Setter
public class MyBatisModel extends BaseMyBatisModel {
  protected String id; // NOSONAR for mybatis generator
  protected String createdBy; // NOSONAR for mybatis generator
  protected Date createdAt; // NOSONAR for mybatis generator
  protected String updatedBy;
  protected Date updatedAt;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
