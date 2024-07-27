package io.github.pavansharma36.saas.core.dao.mybatis.migration;

import io.github.pavansharma36.core.common.listener.AppLoaderListener;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FlywayMigrationListener implements AppLoaderListener {

  @Override
  public void onStart(ApplicationContext applicationContext) {
    Map<String, DataSource> datasources =
        applicationContext.getBeansOfType(DataSource.class);
    if (datasources.size() == 1) {
      FlywayMigrationUtils.runFlywayMigration(datasources.values().iterator().next());
    } else {
      throw new ServerRuntimeException("Multiple or none datasource found for migration");
    }
  }

  @Override
  public void onStop(ApplicationContext applicationContext) {

  }

}
