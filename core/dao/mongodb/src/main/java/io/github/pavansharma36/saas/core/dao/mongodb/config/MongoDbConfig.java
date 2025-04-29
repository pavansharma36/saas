package io.github.pavansharma36.saas.core.dao.mongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoDbConfig {

  @Bean
  public MongoTemplate globalMongoTemplate() {
    return null;
  }
}
