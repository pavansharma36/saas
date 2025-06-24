package io.github.pavansharma36.saas.auth.common.dao.mongo.dao;

import io.github.pavansharma36.saas.auth.common.dao.mongo.model.NavItemConfig;
import io.github.pavansharma36.saas.core.dao.mongodb.dao.AbstractGlobalMongoDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NavItemConfigDao extends AbstractGlobalMongoDao<NavItemConfig> {

  public NavItemConfigDao(MongoTemplate mongoTemplate) {
    super(NavItemConfig.class, mongoTemplate);
  }
  
}
