package io.github.pavansharma36.saas.core.broker.common;

import io.github.pavansharma36.saas.utils.Named;

public interface MessageType extends Named {
  Queue queue();
}
