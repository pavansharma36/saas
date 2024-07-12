package io.github.pavansharma36.saas.galaxy.common.dao.mybatis.model;

import io.github.pavansharma36.saas.core.dao.mybatis.model.MyBatisModel;
import jakarta.annotation.Generated;

public class Galaxy extends MyBatisModel {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String token;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String urlScheme;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String urlHost;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer urlPort;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String subDomain;

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
    public String getToken() {
        return token;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setToken(String token) {
        this.token = token;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getUrlScheme() {
        return urlScheme;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUrlScheme(String urlScheme) {
        this.urlScheme = urlScheme;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getUrlHost() {
        return urlHost;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUrlHost(String urlHost) {
        this.urlHost = urlHost;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getUrlPort() {
        return urlPort;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUrlPort(Integer urlPort) {
        this.urlPort = urlPort;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getSubDomain() {
        return subDomain;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSubDomain(String subDomain) {
        this.subDomain = subDomain;
    }
}