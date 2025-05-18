package io.github.pavansharma36.saas.core.dao.mongodb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MongoTemplateService {

  private final MongoTemplate globalMongoTemplate;

  public MongoTemplate globalMongoTemplate() {
    return globalMongoTemplate;
  }

  public MongoTemplate tenantMongoTemplate() {
    // TODO multi tenancy database separation.
    return globalMongoTemplate;
  }

}
