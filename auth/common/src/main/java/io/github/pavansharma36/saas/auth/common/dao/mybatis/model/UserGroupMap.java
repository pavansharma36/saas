package io.github.pavansharma36.saas.auth.common.dao.mybatis.model;

import io.github.pavansharma36.saas.core.dao.mybatis.model.BaseMyBatisModel;
import jakarta.annotation.Generated;

public class UserGroupMap extends BaseMyBatisModel {
  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  private String userId;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  private String userGroupId;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public String getUserId() {
    return userId;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public String getUserGroupId() {
    return userGroupId;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public void setUserGroupId(String userGroupId) {
    this.userGroupId = userGroupId;
  }
}