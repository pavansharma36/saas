package io.github.pavansharma36.saas.core.broker.common.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageDto implements Serializable {

  /**
   * if set message will not be processed if picked from queue after specified time.
   */
  protected Date expireAt;

}
