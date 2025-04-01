package io.github.pavansharma36.saas.galaxy.common.dto.mapper;

import io.github.pavansharma36.saas.core.dao.mybatis.model.BaseMyBatisModel;
import io.github.pavansharma36.saas.core.dao.mybatis.model.MyBatisModel;
import io.github.pavansharma36.saas.core.dto.BaseDto;
import io.github.pavansharma36.saas.core.dto.Dto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseMapper {

  protected static <D extends BaseDto> void setBaseCommonField(D dto, BaseMyBatisModel model) {
    dto.setId(model.getId());
    dto.setCreatedAt(model.getCreatedAt());
    dto.setCreatedBy(model.getCreatedBy());
  }

  protected static <D extends Dto> void setCommonField(D dto, MyBatisModel model) {
    dto.setId(model.getId());
    dto.setCreatedAt(model.getCreatedAt());
    dto.setCreatedBy(model.getCreatedBy());
    dto.setUpdatedAt(model.getUpdatedAt());
    dto.setUpdatedBy(model.getUpdatedBy());
  }

}
