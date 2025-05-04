package io.github.pavansharma36.saas.core.dao.mongodb.mutex;

import io.github.pavansharma36.core.common.mutex.bean.LockType;
import io.github.pavansharma36.core.common.mutex.dao.DuplicateModelException;
import io.github.pavansharma36.core.common.mutex.dao.LockDao;
import io.github.pavansharma36.saas.core.dao.mongodb.dao.AbstractBaseDao;
import io.github.pavansharma36.saas.core.dao.mongodb.model.BaseMongoModel;
import java.util.Date;
import org.springframework.context.annotation.Conditional;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
@Conditional(MongoLockCondition.class)
public class MongoLockDao extends AbstractBaseDao<MongoLockModel>
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
  public boolean remove(String lockId) {
    Criteria criteria = Criteria.where(BaseMongoModel.FIELD_ID).is(lockId);
    return mongoTemplate.remove(new Query(criteria), clazz).wasAcknowledged();
  }

  @Override
  public void updateExpireAtByLockType(LockType lockType, String processUuid, Date expireAt) {
    Criteria criteria = Criteria.where(MongoLockModel.FIELD_LOCK_TYPE).is(lockType)
        .and(MongoLockModel.FIELD_PROCESS_UUID).is(processUuid);
    Update update = Update.update(MongoLockModel.FIELD_EXPIRE_AT, expireAt);
    mongoTemplate.updateMulti(new Query(criteria), update, clazz);
  }
}
