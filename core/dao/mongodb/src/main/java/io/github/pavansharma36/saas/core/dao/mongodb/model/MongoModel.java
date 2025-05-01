package io.github.pavansharma36.saas.core.dao.mongodb.model;

import io.github.pavansharma36.saas.core.dao.common.model.UpdatableModel;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MongoModel extends BaseMongoModel implements UpdatableModel {
  protected String updatedBy;
  protected Date updatedAt;
}
