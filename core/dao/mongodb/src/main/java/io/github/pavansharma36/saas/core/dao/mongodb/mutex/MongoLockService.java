package io.github.pavansharma36.saas.core.dao.mongodb.mutex;

import io.github.pavansharma36.core.common.mutex.bean.Lock;
import io.github.pavansharma36.core.common.mutex.service.AbstractLockService;
import io.github.pavansharma36.core.common.mutex.service.LockService;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

@Service
@Conditional(MongoLockCondition.class)
public class MongoLockService extends AbstractLockService<MongoLockModel> implements LockService {

  protected MongoLockService(MongoLockDao lockDao) {
    super(lockDao);
  }

  @Override
  protected MongoLockModel createModel(Lock lock) {
    return new MongoLockModel();
  }
}
