package io.github.pavansharma36.saas.core.dao.mongodb.model;

import java.util.Date;

public class MongoDbModel extends BaseMongoDbModel {
  protected String updatedBy;
  protected Date updatedAt;

  public String getUpdatedBy() {
    return this.updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Date getUpdatedAt() {
    return this.updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }
}
