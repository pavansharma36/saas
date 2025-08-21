package io.github.pavansharma36.saas.search.common.service;

import io.github.pavansharma36.saas.core.common.validation.BadRequestException;
import io.github.pavansharma36.saas.core.common.validation.CoreErrorCode;
import io.github.pavansharma36.saas.core.dao.elasticsearch.dao.AbstractElasticSearchDao;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GenericSearchService {

  private final Map<String, AbstractElasticSearchDao<?>> entityDaoMap;

  public GenericSearchService(List<AbstractElasticSearchDao<?>> daos) {
    entityDaoMap =
        daos.stream().collect(Collectors.toMap(AbstractElasticSearchDao::entityName, k -> k));
  }

  public List<?> search(String entity,
                        String query,
                        int offset,
                        int limit) {
    return getDao(entity).search(query, PageRequest.of(offset, limit));
  }

  private AbstractElasticSearchDao<?> getDao(String entity) {
    AbstractElasticSearchDao<?> dao = entityDaoMap.get(entity);
    if (dao == null) {
      throw new BadRequestException(CoreErrorCode.NOT_FOUND,
          Collections.singletonMap("id", entity));
    }
    return dao;
  }

}
