package io.github.pavansharma36.saas.search.common.config;

import io.github.pavansharma36.core.common.config.CoreCommonConfiguration;
import io.github.pavansharma36.saas.core.dao.elasticsearch.config.ElasticSearchConfig;
import io.github.pavansharma36.saas.core.dao.redis.config.RedisConfig;
import io.github.pavansharma36.saas.galaxy.client.config.GalaxyClientConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CoreCommonConfiguration.class, GalaxyClientConfig.class, RedisConfig.class,
    ElasticSearchConfig.class})
@ComponentScan("io.github.pavansharma36.saas.search.common")
public class SearchCommonConfiguration {
}
