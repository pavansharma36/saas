package io.github.pavansharma36.saas.core.broker.common.dao;

import io.github.pavansharma36.saas.core.broker.common.bean.MessageStatus;
import io.github.pavansharma36.saas.core.broker.common.dao.model.MessageInfo;
import io.github.pavansharma36.saas.core.dao.mongodb.dao.AbstractGlobalMongoDao;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MessageInfoDao extends AbstractGlobalMongoDao<MessageInfo> {

  public MessageInfoDao(MongoTemplate template) {
    super(MessageInfo.class, template);
  }

  public boolean anyPreviousNonCompletedMessage(MessageInfo messageInfo) {
    if (messageInfo.getOrderKey() == null) {
      return false;
    }

    Set<MessageStatus> completedStatus =
        EnumSet.allOf(MessageStatus.class).stream().filter(MessageStatus::isFinal).collect(
            Collectors.toSet());

    Query query =
        new Query(Criteria.where(MessageInfo.FIELD_ORDER_KEY).is(messageInfo.getOrderKey())
            .and(MessageInfo.FIELD_DISPATCHED_AT).lt(messageInfo.getDispatchedAt())
            .and(MessageInfo.FIELD_STATUS).nin(completedStatus));
    
    return exists(query);
  }

}
