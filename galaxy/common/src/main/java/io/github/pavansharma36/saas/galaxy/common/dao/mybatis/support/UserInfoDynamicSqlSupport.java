package io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class UserInfoDynamicSqlSupport {
  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final UserInfo userInfo = new UserInfo();

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final SqlColumn<String> id = userInfo.id;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final SqlColumn<String> tenantId = userInfo.tenantId;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final SqlColumn<String> firstName = userInfo.firstName;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final SqlColumn<String> lastName = userInfo.lastName;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final SqlColumn<String> createdBy = userInfo.createdBy;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final SqlColumn<Date> createdAt = userInfo.createdAt;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final SqlColumn<String> updatedBy = userInfo.updatedBy;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final SqlColumn<Date> updatedAt = userInfo.updatedAt;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final class UserInfo extends AliasableSqlTable<UserInfo> {
    public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

    public final SqlColumn<String> tenantId = column("tenant_id", JDBCType.VARCHAR);

    public final SqlColumn<String> firstName = column("first_name", JDBCType.VARCHAR);

    public final SqlColumn<String> lastName = column("last_name", JDBCType.VARCHAR);

    public final SqlColumn<String> createdBy = column("created_by", JDBCType.VARCHAR);

    public final SqlColumn<Date> createdAt = column("created_at", JDBCType.TIMESTAMP);

    public final SqlColumn<String> updatedBy = column("updated_by", JDBCType.VARCHAR);

    public final SqlColumn<Date> updatedAt = column("updated_at", JDBCType.TIMESTAMP);

    public UserInfo() {
      super("user_info", UserInfo::new);
    }
  }
}