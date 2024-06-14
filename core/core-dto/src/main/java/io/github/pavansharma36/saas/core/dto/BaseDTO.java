package io.github.pavansharma36.saas.core.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDTO implements Serializable {
  protected String id;
}
