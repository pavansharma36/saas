package io.github.pavansharma36.saas.auth.common.dao.mybatis.support;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class UserGroupMapDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final UserGroupMap userGroupMap = new UserGroupMap();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> id = userGroupMap.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> userId = userGroupMap.userId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> userGroupId = userGroupMap.userGroupId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> createdBy = userGroupMap.createdBy;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createdAt = userGroupMap.createdAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class UserGroupMap extends AliasableSqlTable<UserGroupMap> {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> userId = column("user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> userGroupId = column("user_group_id", JDBCType.VARCHAR);

        public final SqlColumn<String> createdBy = column("created_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> createdAt = column("created_at", JDBCType.TIMESTAMP);

        public UserGroupMap() {
            super("user_group_map", UserGroupMap::new);
        }
    }
}