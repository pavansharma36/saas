package io.github.pavansharma36.saas.core.broker.common.dao.model;

import io.github.pavansharma36.saas.core.broker.common.bean.MessageStatus;
import io.github.pavansharma36.saas.core.dao.mongodb.model.MongoModel;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document
@ToString(callSuper = true)
public class MessageInfo extends MongoModel {

  public static final String FIELD_DISPATCHED_AT = "dispatchedAt";
  public static final String FIELD_ORDER_KEY = "orderKey";
  public static final String FIELD_STATUS = "status";

  // status is used for tracking purpose
  private MessageStatus status;

  // only for informational purpose.
  private String queueName;
  private String messageType;

  /**
   * A message can be sent to multiple apps, owner takes care of marking it completed.
   * other apps will clone message info.
   */
  private String owner;

  /**
   * when set message will be delayed if any prev non completed message found in db.
   * will be cleared after completion to reduce sparse index size.
   */
  @Indexed(sparse = true)
  private String orderKey;

  /**
   * if true lock will be acquired with message id as key.
   */
  private boolean lockOnProcess;
  private boolean idempotent;

  @Field(FIELD_DISPATCHED_AT)
  @Indexed(direction = IndexDirection.DESCENDING)
  private Date dispatchedAt;
  private Date lastPickedAt;
  private Date startedAt;
  private Date completedAt;

  private Date expireAt;
  private String message;
}
