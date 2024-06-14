package io.github.pavansharma36.core.common.context;

import java.util.Map;

public class MDCContext extends AbstractThreadLocalContext<Map<String, String>> {
  @Override
  public byte[] toByteArray() {
    return new byte[0];
  }

  @Override
  public void setFromByteArray(byte[] value) {

  }
}
