package io.github.pavansharma36.saas.core.dao.common.model;

import java.io.Serializable;
import java.util.Date;

public interface Model extends Serializable {

  String getId();

  void setId(String id);

  String getCreatedBy();

  void setCreatedBy(String createdBy);

  Date getCreatedAt();

  void setCreatedAt(Date createdAt);

}
