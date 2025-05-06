package io.github.pavansharma36.saas.galaxy.common.config;

import io.github.pavansharma36.core.common.config.CoreCommonConfiguration;
import io.github.pavansharma36.saas.auth.client.config.AuthClientConfig;
import io.github.pavansharma36.saas.core.dao.mongodb.config.MongoConfig;
import io.github.pavansharma36.saas.core.dao.redis.config.RedisConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("io.github.pavansharma36.saas.galaxy.common")
@Import({CoreCommonConfiguration.class, MongoConfig.class, RedisConfig.class,
    AuthClientConfig.class})
public class GalaxyCommonConfiguration {

}
