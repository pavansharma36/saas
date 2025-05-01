package io.github.pavansharma36.saas.core.dao.mongodb.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.github.pavansharma36.core.common.config.Config;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
@ComponentScan("io.github.pavansharma36.saas.core.dao.mongodb")
public class MongoConfig extends AbstractMongoClientConfiguration {

  @Override
  protected MongoClient createMongoClient(MongoClientSettings settings) {
    String host = Config.get("global.mongo.host");
    String port = Config.get("global.mongo.port", "27017");
    String username = Config.get("global.mongo.username", null);
    String password = Config.get("global.mongo.password", null);
    boolean useSrv = Config.getBoolean("global.mongo.useSrv", false);
    String params = Config.get("global.mongo.params", null);

    StringBuilder connectionString = new StringBuilder("mongodb");
    if (useSrv) {
      connectionString.append("+srv");
    }
    connectionString.append("://");
    if (StringUtils.isNotEmpty(username)) {
      connectionString.append(username);
      if (StringUtils.isNotEmpty(password)) {
        connectionString.append(":").append(password);
      }
      connectionString.append("@");
    }
    connectionString.append(host).append(":").append(port);
    if (StringUtils.isNotEmpty(params)) {
      connectionString.append("?").append(params);
    }
    return MongoClients.create(connectionString.toString());
  }

  @Override
  protected String getDatabaseName() {
    return Config.get("global.mongo.database", "global");
  }

  @Override
  protected boolean autoIndexCreation() {
    return true;
  }
}
