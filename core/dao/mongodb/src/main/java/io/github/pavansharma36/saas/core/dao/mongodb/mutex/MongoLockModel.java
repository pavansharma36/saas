package io.github.pavansharma36.saas.core.dao.mongodb.mutex;

import io.github.pavansharma36.core.common.mutex.bean.LockType;
import io.github.pavansharma36.core.common.mutex.dao.LockModel;
import io.github.pavansharma36.saas.core.dao.mongodb.model.BaseMongoModel;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Getter
@Setter
@CompoundIndex(background = true, unique = true, def = "{'" + MongoLockModel.LOCK_NAME_FIELD +
    "': 1, '" + MongoLockModel.INDEX_FIELD + "': 1}")
public class MongoLockModel extends BaseMongoModel implements LockModel {

  public static final String LOCK_NAME_FIELD = "lockName";
  public static final String INDEX_FIELD = "index";
  public static final String PROCESS_UUID_FIELD = "processUuid";
  public static final String LOCK_TYPE_FIELD = "lockType";
  public static final String EXPIRE_AT_FIELD = "expireAt";

  @Field(LOCK_NAME_FIELD)
  private String lockName;

  @Field(INDEX_FIELD)
  private int index;

  @Field(PROCESS_UUID_FIELD)
  private String processUuid;

  @Field(LOCK_TYPE_FIELD)
  private LockType lockType;

  @Field(EXPIRE_AT_FIELD)
  @Indexed(expireAfter = "0", background = true)
  private Date expireAt;

}
