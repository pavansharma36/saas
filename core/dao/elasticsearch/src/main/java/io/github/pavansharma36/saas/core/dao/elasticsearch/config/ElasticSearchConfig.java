package io.github.pavansharma36.saas.core.dao.elasticsearch.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import io.github.pavansharma36.saas.core.common.config.Config;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;

@Configuration
public class ElasticSearchConfig {

  @Bean
  @Lazy
  public ElasticsearchTemplate elasticsearchTemplate() {
    String host = Config.get("elasticsearch.host");
    String apiKey = Config.get("elasticsearch.api.key", null);
    RestClientBuilder restClient = RestClient
        .builder(HttpHost.create(host));
    if (apiKey != null) {
      restClient.setDefaultHeaders(new Header[] {
          new BasicHeader("Authorization", "ApiKey " + apiKey)
      });
    }

    ElasticsearchTransport transport = new RestClientTransport(
        restClient.build(), new JacksonJsonpMapper(JsonUtils.mapper()));

    ElasticsearchClient esClient = new ElasticsearchClient(transport);
    return new ElasticsearchTemplate(esClient);
  }

}
