package io.github.pavansharma36.saas.auth.common.dao.mybatis.support;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import java.util.List;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class ResourceConfigDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final ResourceConfig resourceConfig = new ResourceConfig();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> id = resourceConfig.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = resourceConfig.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> description = resourceConfig.description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<List<String>> supportedActions = resourceConfig.supportedActions;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> createdBy = resourceConfig.createdBy;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createdAt = resourceConfig.createdAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> updatedBy = resourceConfig.updatedBy;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updatedAt = resourceConfig.updatedAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class ResourceConfig extends AliasableSqlTable<ResourceConfig> {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<List<String>> supportedActions = column("supported_actions", JDBCType.ARRAY, "io.github.pavansharma36.saas.core.dao.mybatis.type.StringListTypeHandler");

        public final SqlColumn<String> createdBy = column("created_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> createdAt = column("created_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> updatedBy = column("updated_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> updatedAt = column("updated_at", JDBCType.TIMESTAMP);

        public ResourceConfig() {
            super("resource_config", ResourceConfig::new);
        }
    }
}