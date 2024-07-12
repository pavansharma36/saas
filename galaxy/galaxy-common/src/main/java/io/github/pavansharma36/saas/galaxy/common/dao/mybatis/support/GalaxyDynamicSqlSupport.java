package io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class GalaxyDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Galaxy galaxy = new Galaxy();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> id = galaxy.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = galaxy.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> description = galaxy.description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> token = galaxy.token;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> urlScheme = galaxy.urlScheme;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> urlHost = galaxy.urlHost;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> urlPort = galaxy.urlPort;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> subDomain = galaxy.subDomain;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> createdBy = galaxy.createdBy;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createdAt = galaxy.createdAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> updatedBy = galaxy.updatedBy;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updatedAt = galaxy.updatedAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Galaxy extends AliasableSqlTable<Galaxy> {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> token = column("token", JDBCType.VARCHAR);

        public final SqlColumn<String> urlScheme = column("url_scheme", JDBCType.VARCHAR);

        public final SqlColumn<String> urlHost = column("url_host", JDBCType.VARCHAR);

        public final SqlColumn<Integer> urlPort = column("url_port", JDBCType.INTEGER);

        public final SqlColumn<String> subDomain = column("sub_domain", JDBCType.VARCHAR);

        public final SqlColumn<String> createdBy = column("created_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> createdAt = column("created_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> updatedBy = column("updated_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> updatedAt = column("updated_at", JDBCType.TIMESTAMP);

        public Galaxy() {
            super("galaxy", Galaxy::new);
        }
    }
}