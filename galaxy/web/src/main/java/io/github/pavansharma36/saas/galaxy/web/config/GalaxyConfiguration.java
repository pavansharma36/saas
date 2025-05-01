package io.github.pavansharma36.saas.galaxy.web.config;

import io.github.pavansharma36.saas.core.dao.mongodb.config.MongoConfig;
import io.github.pavansharma36.saas.core.web.security.config.WebSecurityConfig;
import io.github.pavansharma36.saas.galaxy.common.config.GalaxyCommonConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({WebSecurityConfig.class, GalaxyCommonConfiguration.class, MongoConfig.class})
public class GalaxyConfiguration {

}
