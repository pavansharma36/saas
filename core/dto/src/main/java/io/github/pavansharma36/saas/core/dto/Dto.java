package io.github.pavansharma36.saas.core.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Dto extends BaseDto {
  protected String updatedBy;
  protected Date updatedAt;
}
