package io.github.pavansharma36.saas.auth.common.config;

import io.github.pavansharma36.core.common.config.CoreCommonConfiguration;
import io.github.pavansharma36.saas.core.dao.redis.config.RedisConfig;
import io.github.pavansharma36.saas.galaxy.client.config.GalaxyClientConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CoreCommonConfiguration.class, GalaxyClientConfig.class, RedisConfig.class})
@ComponentScan({"io.github.pavansharma36.auth.api", "io.github.pavansharma36.saas.auth.common"})
public class AuthCommonConfiguration {

}
