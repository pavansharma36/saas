package io.github.pavansharma36.saas.auth.common.dao.mybatis.model;

import io.github.pavansharma36.saas.core.dao.mybatis.model.MyBatisModel;
import jakarta.annotation.Generated;
import java.util.List;

public class UserGroupResources extends MyBatisModel {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String userGroupId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String resourceName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private List<String> allowedActions;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getUserGroupId() {
        return userGroupId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUserGroupId(String userGroupId) {
        this.userGroupId = userGroupId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getResourceName() {
        return resourceName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public List<String> getAllowedActions() {
        return allowedActions;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAllowedActions(List<String> allowedActions) {
        this.allowedActions = allowedActions;
    }
}