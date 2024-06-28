package io.github.pavansharma36.saas.core.dao.common;

import java.util.Date;

public interface UpdatableModel extends Model {

  String getModifiedBy();

  Date getModifiedAt();

}
