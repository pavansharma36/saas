package io.github.pavansharma36.saas.core.dao.elasticsearch.dao;

import io.github.pavansharma36.saas.core.dao.common.DefaultEntityListener;
import io.github.pavansharma36.saas.core.dao.common.EntityListener;
import io.github.pavansharma36.saas.core.dao.common.dao.Dao;
import io.github.pavansharma36.saas.core.dao.elasticsearch.model.BaseElasticModel;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;

@Slf4j
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
    Query queryObj = buildSearchQuery(query, pageable);
    log.debug("Executing search query for term {} on {} : {}", query, clazz, queryObj);
    return elasticsearchTemplate.search(queryObj, clazz).get()
        .map(SearchHit::getContent).toList();
  }

  protected abstract Query buildSearchQuery(String query, Pageable pageable);

  public abstract String entityName();
}
