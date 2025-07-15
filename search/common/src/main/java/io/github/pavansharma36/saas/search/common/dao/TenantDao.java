package io.github.pavansharma36.saas.search.common.dao;

import io.github.pavansharma36.saas.core.dao.elasticsearch.dao.AbstractElasticSearchDao;
import io.github.pavansharma36.saas.search.common.model.Tenant;
import io.github.pavansharma36.saas.utils.resource.ResourceUtils;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TenantDao extends AbstractElasticSearchDao<Tenant> {

  private final String searchQueryTemplate;

  public TenantDao(ElasticsearchTemplate elasticsearchTemplate) {
    super(Tenant.class, elasticsearchTemplate);
    searchQueryTemplate =
        ResourceUtils.readClasspathFile("queries/tenant.search", TenantDao.class);
  }

  @Override
  public String getSearchQueryTemplate() {
    return searchQueryTemplate;
  }

  @Override
  public String entityName() {
    return Tenant.ENTITY_NAME;
  }
}
