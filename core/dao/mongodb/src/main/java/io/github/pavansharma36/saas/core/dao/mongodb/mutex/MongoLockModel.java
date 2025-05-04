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
@CompoundIndex(background = true, unique = true, def = "{'" + MongoLockModel.FIELD_LOCK_NAME +
    "': 1, '" + MongoLockModel.FIELD_INDEX + "': 1}")
public class MongoLockModel extends BaseMongoModel implements LockModel {

  public static final String FIELD_LOCK_NAME = "lockName";
  public static final String FIELD_INDEX = "index";
  public static final String FIELD_PROCESS_UUID = "processUuid";
  public static final String FIELD_LOCK_TYPE = "lockType";
  public static final String FIELD_EXPIRE_AT = "expireAt";

  @Field(FIELD_LOCK_NAME)
  private String lockName;

  @Field(FIELD_INDEX)
  private int index;

  @Field(FIELD_PROCESS_UUID)
  private String processUuid;

  @Field(FIELD_LOCK_TYPE)
  private LockType lockType;

  @Field(FIELD_EXPIRE_AT)
  @Indexed(expireAfter = "0", background = true)
  private Date expireAt;

}
