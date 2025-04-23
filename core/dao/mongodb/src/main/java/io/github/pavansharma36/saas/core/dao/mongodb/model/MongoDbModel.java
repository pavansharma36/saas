package io.github.pavansharma36.saas.core.dao.mongodb.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MongoDbModel extends BaseMongoDbModel {
  protected String updatedBy;
  protected Date updatedAt;
}
