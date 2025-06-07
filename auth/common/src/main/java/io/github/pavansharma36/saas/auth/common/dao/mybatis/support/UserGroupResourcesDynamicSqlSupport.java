package io.github.pavansharma36.saas.auth.common.dao.mybatis.support;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import java.util.List;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class UserGroupResourcesDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final UserGroupResources userGroupResources = new UserGroupResources();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> id = userGroupResources.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> userGroupId = userGroupResources.userGroupId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> resourceName = userGroupResources.resourceName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<List<String>> allowedActions = userGroupResources.allowedActions;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> createdBy = userGroupResources.createdBy;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createdAt = userGroupResources.createdAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> updatedBy = userGroupResources.updatedBy;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updatedAt = userGroupResources.updatedAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class UserGroupResources extends AliasableSqlTable<UserGroupResources> {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> userGroupId = column("user_group_id", JDBCType.VARCHAR);

        public final SqlColumn<String> resourceName = column("resource_name", JDBCType.VARCHAR);

        public final SqlColumn<List<String>> allowedActions = column("allowed_actions", JDBCType.ARRAY, "io.github.pavansharma36.saas.core.dao.mybatis.type.StringListTypeHandler");

        public final SqlColumn<String> createdBy = column("created_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> createdAt = column("created_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> updatedBy = column("updated_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> updatedAt = column("updated_at", JDBCType.TIMESTAMP);

        public UserGroupResources() {
            super("user_group_resources", UserGroupResources::new);
        }
    }
}