package io.github.pavansharma36.saas.core.broker.common.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MessageDto implements Serializable {

  /**
   * if set message will not be processed if picked from queue after specified time.
   */
  protected Date expireAt;

}
