package io.github.pavansharma36.saas.core.dao.elasticsearch.model;

import io.github.pavansharma36.saas.core.dao.common.model.UpdatableModel;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElasticModel extends BaseElasticModel implements UpdatableModel {
  private String updatedBy;
  private Date updatedAt;
}
