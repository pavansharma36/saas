package io.github.pavansharma36.saas.core.dao.elasticsearch.dao;

import io.github.pavansharma36.saas.core.dao.common.DefaultEntityListener;
import io.github.pavansharma36.saas.core.dao.common.EntityListener;
import io.github.pavansharma36.saas.core.dao.common.dao.Dao;
import io.github.pavansharma36.saas.core.dao.elasticsearch.model.BaseElasticModel;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;

public abstract class AbstractElasticSearchDao<T extends BaseElasticModel> implements Dao<T> {

  private final Class<T> clazz;
  private final ElasticsearchTemplate elasticsearchTemplate;
  private final List<EntityListener<T>> entityListeners;

  protected AbstractElasticSearchDao(Class<T> clazz, ElasticsearchTemplate elasticsearchTemplate) {
    this(clazz, elasticsearchTemplate,
        Collections.singletonList(new DefaultEntityListener<>(clazz)));
  }

  protected AbstractElasticSearchDao(Class<T> clazz, ElasticsearchTemplate elasticsearchTemplate,
                                     List<EntityListener<T>> entityListeners) {
    this.clazz = clazz;
    this.elasticsearchTemplate = elasticsearchTemplate;
    this.entityListeners = entityListeners;
  }

  @Override
  public T insert(T model) {
    entityListeners.forEach(l -> l.preInsert(model));
    return elasticsearchTemplate.save(model);
  }

  @Override
  public T update(T model) {
    entityListeners.forEach(l -> l.preUpdate(model));
    return elasticsearchTemplate.save(model);
  }

  @Override
  public Optional<T> findById(String id) {
    SearchHit<T> res =
        elasticsearchTemplate.searchOne(Query.multiGetQuery(Collections.singletonList(id)),
            clazz);
    return Optional.ofNullable(res).map(SearchHit::getContent);
  }

  @Override
  public boolean deleteById(String id) {
    entityListeners.forEach(l -> l.preDelete(id));
    elasticsearchTemplate.delete(id, clazz);
    return true;
  }

  public List<T> search(String query, Pageable pageable) {
    return elasticsearchTemplate.search(buildSearchQuery(query, pageable), clazz).get()
        .map(SearchHit::getContent).toList();
  }

  private Query buildSearchQuery(String query, Pageable pageable) {
    Map<String, Object> context = new HashMap<>();
    context.put("QUERY", query);
    String queryJson = new StringSubstitutor(context).replace(getSearchQueryTemplate());
    JsonUtils.fromJson(queryJson, Object.class);
    return new StringQuery(queryJson, pageable);
  }

  protected abstract String getSearchQueryTemplate();

  public abstract String entityName();
}
