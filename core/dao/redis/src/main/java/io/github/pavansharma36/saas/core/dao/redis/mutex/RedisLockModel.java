package io.github.pavansharma36.saas.core.dao.redis.mutex;

import io.github.pavansharma36.saas.core.common.mutex.bean.LockType;
import io.github.pavansharma36.saas.core.common.mutex.dao.LockModel;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisLockModel implements LockModel {
  private String lockName;
  private int index;
  private String processUuid;
  private LockType lockType;
  private Date expireAt;

  @Override
  public String getId() {
    return "";
  }
}
