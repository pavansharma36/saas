package io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model;

import io.github.pavansharma36.saas.core.dao.mybatis.model.MyBatisModel;
import jakarta.annotation.Generated;

public class Tenant extends MyBatisModel {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String code;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer incrementalId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getName() {
        return name;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setName(String name) {
        this.name = name;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getDescription() {
        return description;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDescription(String description) {
        this.description = description;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCode() {
        return code;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCode(String code) {
        this.code = code;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getIncrementalId() {
        return incrementalId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIncrementalId(Integer incrementalId) {
        this.incrementalId = incrementalId;
    }
}