package io.github.pavansharma36.saas.core.dao.mybatis.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
@Setter
public class MyBatisModel extends BaseMyBatisModel {
  @SuppressFBWarnings
  protected String id; // NOSONAR for mybatis generator
  @SuppressFBWarnings
  protected String createdBy; // NOSONAR for mybatis generator
  @SuppressFBWarnings
  protected Date createdAt; // NOSONAR for mybatis generator
  protected String updatedBy;
  protected Date updatedAt;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
