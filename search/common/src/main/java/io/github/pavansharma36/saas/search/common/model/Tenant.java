package io.github.pavansharma36.saas.search.common.model;

import io.github.pavansharma36.saas.core.dao.elasticsearch.model.ElasticModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Document(indexName = Tenant.ENTITY_NAME, createIndex = true)
public class Tenant extends ElasticModel {

  public static final String ENTITY_NAME = "tenant";

  private String name;
  private String description;
  private String code;
}
