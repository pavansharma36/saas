package io.github.pavansharma36.saas.galaxy.common.dao.mybatis.support;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class ConfigDynamicSqlSupport {
  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final Config config = new Config();

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final SqlColumn<String> id = config.id;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final SqlColumn<String> classifier = config.classifier;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final SqlColumn<String> classifierValue = config.classifierValue;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final SqlColumn<String> configName = config.configName;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final SqlColumn<String> configValue = config.configValue;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final SqlColumn<String> createdBy = config.createdBy;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final SqlColumn<Date> createdAt = config.createdAt;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final SqlColumn<String> updatedBy = config.updatedBy;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final SqlColumn<Date> updatedAt = config.updatedAt;

  @Generated("org.mybatis.generator.api.MyBatisGenerator")
  public static final class Config extends AliasableSqlTable<Config> {
    public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

    public final SqlColumn<String> classifier = column("classifier", JDBCType.VARCHAR);

    public final SqlColumn<String> classifierValue = column("classifier_value", JDBCType.VARCHAR);

    public final SqlColumn<String> configName = column("config_name", JDBCType.VARCHAR);

    public final SqlColumn<String> configValue = column("config_value", JDBCType.VARCHAR);

    public final SqlColumn<String> createdBy = column("created_by", JDBCType.VARCHAR);

    public final SqlColumn<Date> createdAt = column("created_at", JDBCType.TIMESTAMP);

    public final SqlColumn<String> updatedBy = column("updated_by", JDBCType.VARCHAR);

    public final SqlColumn<Date> updatedAt = column("updated_at", JDBCType.TIMESTAMP);

    public Config() {
      super("config", Config::new);
    }
  }
}