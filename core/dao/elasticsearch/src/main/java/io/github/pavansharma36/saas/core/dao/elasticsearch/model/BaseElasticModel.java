package io.github.pavansharma36.saas.core.dao.elasticsearch.model;

import io.github.pavansharma36.saas.core.dao.common.model.Model;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class BaseElasticModel implements Model {

  @Id
  private String id;

  private String createdBy;
  private Date createdAt;
}
