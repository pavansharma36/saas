package io.github.pavansharma36.saas.core.dao.mongodb.model;

import io.github.pavansharma36.saas.core.dao.common.model.Model;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class BaseMongoModel implements Model {

  public static final String FIELD_ID = "_id";

  @Id
  @Field(FIELD_ID)
  private String id;
  private String createdBy;
  private Date createdAt;
}
