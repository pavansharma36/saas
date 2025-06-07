package io.github.pavansharma36.saas.auth.common.dao.mybatis.support;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class UserGroupDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final UserGroup userGroup = new UserGroup();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> id = userGroup.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> tenantId = userGroup.tenantId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = userGroup.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> description = userGroup.description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> createdBy = userGroup.createdBy;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createdAt = userGroup.createdAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> updatedBy = userGroup.updatedBy;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updatedAt = userGroup.updatedAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class UserGroup extends AliasableSqlTable<UserGroup> {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> tenantId = column("tenant_id", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> createdBy = column("created_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> createdAt = column("created_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> updatedBy = column("updated_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> updatedAt = column("updated_at", JDBCType.TIMESTAMP);

        public UserGroup() {
            super("user_group", UserGroup::new);
        }
    }
}