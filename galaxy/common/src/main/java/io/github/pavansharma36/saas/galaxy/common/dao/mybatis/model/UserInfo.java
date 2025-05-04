package io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model;

import io.github.pavansharma36.saas.core.dao.mybatis.model.MyBatisModel;
import jakarta.annotation.Generated;

public class UserInfo extends MyBatisModel {
  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  private String tenantId;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  private String firstName;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  private String lastName;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public String getTenantId() {
    return tenantId;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public String getFirstName() {
    return firstName;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public String getLastName() {
    return lastName;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}