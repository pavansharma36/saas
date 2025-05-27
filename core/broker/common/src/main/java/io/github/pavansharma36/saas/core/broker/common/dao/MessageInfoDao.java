package io.github.pavansharma36.saas.core.broker.common.dao;

import io.github.pavansharma36.saas.core.broker.common.dao.model.MessageInfo;
import io.github.pavansharma36.saas.core.dao.mongodb.dao.AbstractGlobalMongoDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MessageInfoDao extends AbstractGlobalMongoDao<MessageInfo> {

  public MessageInfoDao(MongoTemplate template) {
    super(MessageInfo.class, template);
  }

}
