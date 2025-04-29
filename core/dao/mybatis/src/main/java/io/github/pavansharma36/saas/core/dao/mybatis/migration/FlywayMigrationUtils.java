package io.github.pavansharma36.saas.core.dao.mybatis.migration;

import javax.sql.DataSource;
import org.flywaydb.core.Flyway;

public abstract class FlywayMigrationUtils {

  public FlywayMigrationUtils() {
  }

  public static void runFlywayMigration(DataSource dataSource) {
    FlywayMigrationUtils.runFlywayMigration("db/migration", dataSource);
  }

  public static void runFlywayMigration(String migrationsLocation, DataSource dataSource) {
    final Flyway flyway = new Flyway(
        Flyway.configure()
            .dataSource(dataSource)
            .locations(migrationsLocation)
            .baselineOnMigrate(true)
            .validateOnMigrate(true)
            .placeholderReplacement(false));
    flyway.migrate();
  }

}
