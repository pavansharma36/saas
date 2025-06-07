package io.github.pavansharma36.saas.core.dao.mybatis.dao;

import io.github.pavansharma36.core.common.context.providers.RequestInfoContextProvider;
import io.github.pavansharma36.core.common.id.IdGenerator;
import io.github.pavansharma36.saas.core.dao.mybatis.mapper.BaseMapper;
import io.github.pavansharma36.saas.core.dao.mybatis.model.RetryingInsertMyBatisModel;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlColumn;

@Slf4j
public abstract class AbstractRetryingInsertDao<T extends RetryingInsertMyBatisModel, M extends BaseMapper<T>>
    extends AbstractMyBatisDao<T, M> {
  protected AbstractRetryingInsertDao(Class<T> clazz,
                                      IdGenerator idGenerator,
                                      M mapper) {
    super(clazz, idGenerator, mapper);
  }

  @Override
  public T insert(T model) {
    if (RequestInfoContextProvider.getInstance().getOrThrow().isRetry()) {
      String attemptId = RequestInfoContextProvider.getInstance().getOrThrow().getAttemptId();
      Optional<T> optionalT =
          selectOne(q -> q.where(getAttemptIdColumn(), SqlBuilder.isEqualTo(attemptId)));
      if (optionalT.isPresent()) {
        log.warn("Entity with attempt id : {} is already present, skipping creation {}", attemptId,
            optionalT.get().getId());
        return optionalT.get();
      }
    }
    return super.insert(model);
  }

  protected abstract SqlColumn<String> getAttemptIdColumn();
}
