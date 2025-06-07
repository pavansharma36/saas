package io.github.pavansharma36.saas.auth.common.dao.mybatis.model;

import io.github.pavansharma36.saas.core.dao.mybatis.model.RetryingInsertMyBatisModel;
import jakarta.annotation.Generated;
import java.util.Date;

public class UserAccount extends RetryingInsertMyBatisModel {
  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  private String username;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  private String password;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  private boolean accountNonExpired;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  private boolean accountNonLocked;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  private boolean admin;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  private Date credentialsExpireAt;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  private String attemptId;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public String getUsername() {
    return username;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public void setUsername(String username) {
    this.username = username;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public String getPassword() {
    return password;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public void setPassword(String password) {
    this.password = password;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public void setAccountNonExpired(boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public void setAccountNonLocked(boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public boolean isAdmin() {
    return admin;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public Date getCredentialsExpireAt() {
    return credentialsExpireAt;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public void setCredentialsExpireAt(Date credentialsExpireAt) {
    this.credentialsExpireAt = credentialsExpireAt;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public String getAttemptId() {
    return attemptId;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public void setAttemptId(String attemptId) {
    this.attemptId = attemptId;
  }
}