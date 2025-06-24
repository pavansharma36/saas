package io.github.pavansharma36.saas.core.dao.mybatis.dao;

import io.github.pavansharma36.core.common.context.providers.RequestInfoContextProvider;
import io.github.pavansharma36.core.common.id.IdGenerator;
import io.github.pavansharma36.saas.core.dao.common.model.Model;
import io.github.pavansharma36.saas.core.dao.mybatis.mapper.BaseMapper;
import java.util.Optional;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlColumn;

public abstract class AbstractTenantDao<T extends Model, M extends BaseMapper<T>>
    extends AbstractMyBatisDao<T, M> {

  protected AbstractTenantDao(Class<T> clazz,
                              IdGenerator idGenerator,
                              M mapper) {
    super(clazz, idGenerator, mapper);
  }

  @Override
  public Optional<T> findById(String id) {
    Optional<String> tenantId = RequestInfoContextProvider.getTenantId();
    if (tenantId.isPresent()) {
      return super.selectOne(d ->
          d.where(idColumn(), SqlBuilder.isEqualTo(id))
              .and(tenantIdColumn(), SqlBuilder.isEqualTo(tenantId.get()))
      );
    } else {
      return super.findById(id);
    }

  }

  protected abstract SqlColumn<String> idColumn();

  protected abstract SqlColumn<String> tenantIdColumn();
}
