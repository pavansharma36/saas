package io.github.pavansharma36.saas.core.dao.mybatis.model;

import io.github.pavansharma36.saas.core.dao.common.model.Model;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseMyBatisModel implements Model {
  protected String id;
  protected String createdBy;
  protected Date createdAt;
}
