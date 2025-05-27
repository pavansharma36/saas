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

@Getter
@Setter
@Document
@ToString(callSuper = true)
public class MessageInfo extends MongoModel {
  private MessageStatus status;
  private String queueName;
  private String messageType;

  @Indexed(sparse = true)
  private String orderKey;
  private boolean lockOnProcess;
  private boolean idempotent;

  @Indexed(direction = IndexDirection.DESCENDING)
  private Date dispatchedAt;

  private Date expireAt;
  private Date lastPickedAt;
  private Date completedAt;
  private String message;
}
