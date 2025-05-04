package io.github.pavansharma36.saas.core.dao.mybatis.model;

import io.github.pavansharma36.saas.core.dao.common.model.UpdatableModel;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class RetryingInsertMyBatisModel implements UpdatableModel {
  protected String id;
  protected String createdBy;
  protected Date createdAt;
  protected String updatedBy;
  protected Date updatedAt;
  protected String attemptId;
}
