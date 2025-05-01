package io.github.pavansharma36.saas.core.dao.common.model;

import java.util.Date;

public interface UpdatableModel extends Model {

  String getUpdatedBy();

  Date getUpdatedAt();

}
