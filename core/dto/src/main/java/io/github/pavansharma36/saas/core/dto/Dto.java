package io.github.pavansharma36.saas.core.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dto extends BaseDto {
  protected String updatedBy;
  protected Date updatedAt;
}
