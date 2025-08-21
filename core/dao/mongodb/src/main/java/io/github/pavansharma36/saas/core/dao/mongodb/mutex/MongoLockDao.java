package io.github.pavansharma36.saas.core.dao.mongodb.mutex;

import io.github.pavansharma36.saas.core.common.mutex.bean.LockType;
import io.github.pavansharma36.saas.core.common.mutex.dao.DuplicateModelException;
import io.github.pavansharma36.saas.core.common.mutex.dao.LockDao;
import io.github.pavansharma36.saas.core.dao.mongodb.dao.AbstractGlobalMongoDao;
import io.github.pavansharma36.saas.core.dao.mongodb.model.BaseMongoModel;
import io.github.pavansharma36.saas.utils.collections.CollectionUtils;
import java.util.Date;
import java.util.Set;
import org.springframework.context.annotation.Conditional;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
@Conditional(MongoLockCondition.class)
public class MongoLockDao extends AbstractGlobalMongoDao<MongoLockModel>
    implements LockDao<MongoLockModel> {

  public MongoLockDao(MongoTemplate mongoTemplate) {
    super(MongoLockModel.class, mongoTemplate);
  }

  @Override
  public MongoLockModel insert(MongoLockModel model) {
    try {
      return super.insert(model);
    } catch (DuplicateKeyException e) {
      throw new DuplicateModelException(e.getMessage(), e);
    }
  }

  @Override
  public boolean remove(Set<String> lockIds) {
    if (CollectionUtils.isEmpty(lockIds)) {
      return false;
    }
    Criteria criteria = Criteria.where(BaseMongoModel.FIELD_ID).in(lockIds);
    return deleteByQuery(new Query(criteria));
  }

  @Override
  public long updateExpireAtByLockType(LockType lockType, String processUuid, Date expireAt) {
    Criteria criteria = Criteria.where(MongoLockModel.FIELD_LOCK_TYPE).is(lockType)
        .and(MongoLockModel.FIELD_PROCESS_UUID).is(processUuid);
    Update update = Update.update(MongoLockModel.FIELD_EXPIRE_AT, expireAt);
    return updateMulti(new Query(criteria), update).getModifiedCount();
  }
}
