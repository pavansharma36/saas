package io.github.pavansharma36.saas.core.dao.mybatis.model;

import io.github.pavansharma36.saas.core.dao.common.Model;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyBatisModel implements Model {
  private String id;
  private String createdBy;
  private Date createdAt;
}
