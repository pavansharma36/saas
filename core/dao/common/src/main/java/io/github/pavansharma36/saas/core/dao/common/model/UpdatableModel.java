package io.github.pavansharma36.saas.core.dao.common.model;

import java.util.Date;

public interface UpdatableModel extends Model {

  String getUpdatedBy();

  void setUpdatedBy(String updatedBy);

  Date getUpdatedAt();

  void setUpdatedAt(Date updatedAt);

}
