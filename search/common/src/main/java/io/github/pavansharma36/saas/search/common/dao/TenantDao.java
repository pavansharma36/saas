package io.github.pavansharma36.saas.search.common.dao;

import io.github.pavansharma36.saas.core.dao.elasticsearch.dao.AbstractElasticSearchDao;
import io.github.pavansharma36.saas.search.common.model.Tenant;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import io.github.pavansharma36.saas.utils.resource.ResourceUtils;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Repository;

@Repository
public class TenantDao extends AbstractElasticSearchDao<Tenant> {

  private final String searchQueryTemplate;

  public TenantDao(ElasticsearchTemplate elasticsearchTemplate) {
    super(Tenant.class, elasticsearchTemplate);
    searchQueryTemplate =
        ResourceUtils.readClasspathFile("queries/tenant.search", TenantDao.class);
  }

  protected Query buildSearchQuery(String query, Pageable pageable) {
    Map<String, Object> context = new HashMap<>();
    context.put("QUERY", query);
    String queryJson = new StringSubstitutor(context).replace(searchQueryTemplate);
    JsonUtils.fromJson(queryJson, Object.class);
    return new StringQuery(queryJson, pageable);
  }

  @Override
  public String entityName() {
    return Tenant.ENTITY_NAME;
  }
}
