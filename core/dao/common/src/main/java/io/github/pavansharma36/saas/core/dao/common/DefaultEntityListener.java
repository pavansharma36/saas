package io.github.pavansharma36.saas.core.dao.common;

import io.github.pavansharma36.core.common.utils.CoreUtils;
import io.github.pavansharma36.core.common.validation.ServerRuntimeException;
import io.github.pavansharma36.saas.core.dao.common.model.Model;
import io.github.pavansharma36.saas.core.dao.common.model.UpdatableModel;
import java.util.Date;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultEntityListener<M extends Model> implements EntityListener<M> {

  protected final Class<M> clazz;

  @Override
  public void preInsert(M model) {
    if (model.getCreatedAt() == null) {
      model.setCreatedAt(new Date());
    }
    if (model.getCreatedBy() == null) {
      model.setCreatedBy(CoreUtils.getUserIdOrThrow());
    }
    if (model instanceof UpdatableModel m) {
      if (m.getUpdatedAt() == null) {
        m.setUpdatedAt(new Date());
      }
      if (m.getUpdatedBy() == null) {
        m.setUpdatedBy(CoreUtils.getUserIdOrThrow());
      }
    }
  }

  @Override
  public void preUpdate(M model) {
    if (model instanceof UpdatableModel m) {
      m.setUpdatedAt(new Date());
      m.setUpdatedBy(CoreUtils.getUserIdOrThrow());
    } else {
      throw new ServerRuntimeException("Entity not supported for update " + clazz.getName());
    }
  }

  @Override
  public void preDelete(String id) {
    // nothing to do here.
  }
}
