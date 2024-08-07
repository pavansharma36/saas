package io.github.pavansharma36.saas.core.dao.mongodb;

import io.github.pavansharma36.saas.core.dao.common.Model;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class BaseMongoDBModel implements Model {
  @Id
  private String id;
  private String createdBy;
  private Date createdAt;
}
