package io.github.pavansharma36.saas.core.dao.common;

import java.io.Serializable;
import java.util.Date;

public interface Model extends Serializable {

  String getId();

  String getCreatedBy();

  Date getCreatedAt();

}
