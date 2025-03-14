package io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model;

import io.github.pavansharma36.saas.core.dao.mybatis.model.MyBatisModel;
import jakarta.annotation.Generated;

public class Config extends MyBatisModel {
  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  private String classifier;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  private String classifierValue;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  private String configName;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  private String configValue;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public String getClassifier() {
    return classifier;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public void setClassifier(String classifier) {
    this.classifier = classifier;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public String getClassifierValue() {
    return classifierValue;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public void setClassifierValue(String classifierValue) {
    this.classifierValue = classifierValue;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public String getConfigName() {
    return configName;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public void setConfigName(String configName) {
    this.configName = configName;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public String getConfigValue() {
    return configValue;
  }

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public void setConfigValue(String configValue) {
    this.configValue = configValue;
  }
}