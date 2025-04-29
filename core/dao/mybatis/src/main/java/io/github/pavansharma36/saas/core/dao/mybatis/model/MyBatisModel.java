package io.github.pavansharma36.saas.core.dao.mybatis.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.github.pavansharma36.saas.core.dao.common.UpdatableModel;
import java.util.Date;

public class MyBatisModel extends BaseMyBatisModel implements UpdatableModel {
  @SuppressFBWarnings
  protected String id; // NOSONAR for mybatis generator
  @SuppressFBWarnings
  protected String createdBy; // NOSONAR for mybatis generator
  @SuppressFBWarnings
  protected Date createdAt; // NOSONAR for mybatis generator
  protected String updatedBy;
  protected Date updatedAt;

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

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
