package io.github.pavansharma36.saas.core.dao.mybatis.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class RetryingInsertMyBatisModel extends MyBatisModel {
  @SuppressFBWarnings
  protected String id; // NOSONAR for mybatis generator
  @SuppressFBWarnings
  protected String createdBy; // NOSONAR for mybatis generator
  @SuppressFBWarnings
  protected Date createdAt; // NOSONAR for mybatis generator
  protected String updatedBy;
  protected Date updatedAt;
  protected String attemptId;
}
