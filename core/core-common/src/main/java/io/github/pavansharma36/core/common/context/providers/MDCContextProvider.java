package io.github.pavansharma36.core.common.context.providers;

import io.github.pavansharma36.core.common.context.MDCContext;
import io.github.pavansharma36.core.common.context.ThreadLocalContext;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MDCContextProvider implements ThreadLocalContext<Void> {

  private static final MDCContext MDC_CONTEXT = new MDCContext();

  public static void put(String key, String value) {
    MDC.put(key, value);
  }

  @Override
  public Void get() {
    return null;
  }

  @Override
  public void set(Void value) {

  }

  @Override
  public void clearContext() {
    MDC.clear();
  }

  @Override
  public byte[] toByteArray() {
    return new byte[0];
  }

  @Override
  public void setFromByteArray(byte[] value) {

  }
}
