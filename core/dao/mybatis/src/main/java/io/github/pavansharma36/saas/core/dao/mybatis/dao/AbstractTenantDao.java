package io.github.pavansharma36.saas.core.dao.mybatis.dao;

import io.github.pavansharma36.core.common.context.providers.RequestInfoContextProvider;
import io.github.pavansharma36.core.common.id.IdGenerator;
import io.github.pavansharma36.saas.core.dao.common.model.Model;
import io.github.pavansharma36.saas.core.dao.mybatis.mapper.BaseMapper;
import java.util.Optional;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;

public abstract class AbstractTenantDao<T extends Model, M extends BaseMapper<T>>
    extends AbstractMyBatisDao<T, M> {

  protected AbstractTenantDao(Class<T> clazz,
                              IdGenerator idGenerator,
                              M mapper) {
    super(clazz, idGenerator, mapper);
  }

  @Override
  public Optional<T> findById(String id) {
    SelectDSLCompleter c = d -> {
      QueryExpressionDSL<org.mybatis.dynamic.sql.select.SelectModel>.QueryExpressionWhereBuilder
          criteria = d.where(idColumn(), SqlBuilder.isEqualTo(id));
      Optional<String> tenantId = RequestInfoContextProvider.getTenantId();
      tenantId.ifPresent(s -> criteria.and(tenantIdColumn(), SqlBuilder.isEqualTo(s)));
      return d;
    };

    return super.selectOne(c);
  }

  protected abstract SqlColumn<String> idColumn();

  protected abstract SqlColumn<String> tenantIdColumn();
}
