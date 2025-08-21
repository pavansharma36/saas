package io.github.pavansharma36.saas.auth.common.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.pavansharma36.saas.core.common.config.Config;
import io.github.pavansharma36.saas.core.dao.mybatis.config.MyBatisConfig;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("io.github.pavansharma36.saas.auth.common.dao.mybatis.mapper")
public class AuthMyBatisConfig extends MyBatisConfig {

  private static final String DATASOURCE_CONF_PREFIX = "auth.hikari.";

  @Override
  protected DataSource dataSourceInternal() {
    Map<String, String> datasourceConfs = Config.getAllByPrefix(DATASOURCE_CONF_PREFIX);
    Properties props = new Properties();
    for (Map.Entry<String, String> e : datasourceConfs.entrySet()) {
      props.put(e.getKey().replace(DATASOURCE_CONF_PREFIX, ""), e.getValue());
    }
    HikariConfig config = new HikariConfig(props);
    return new HikariDataSource(config);
  }

}
