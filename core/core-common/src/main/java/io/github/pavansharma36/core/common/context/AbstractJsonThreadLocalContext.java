package io.github.pavansharma36.core.common.context;

import io.github.pavansharma36.saas.utils.json.JsonUtils;
import java.nio.charset.StandardCharsets;

public class AbstractJsonThreadLocalContext<T> extends AbstractThreadLocalContext<T> {

  protected final Class<T> clazz;

  protected AbstractJsonThreadLocalContext(Class<T> clazz) {
    this.clazz = clazz;
  }

  @Override
  public final byte[] toByteArray() {
    return get().map(r -> {
      String json = JsonUtils.toJson(r);
      return json.getBytes(StandardCharsets.UTF_8);
    }).orElseGet(() -> new byte[0]);
  }

  @Override
  public final void setFromByteArray(byte[] value) {
    T v = JsonUtils.fromJson(new String(value, StandardCharsets.UTF_8), clazz);
    context.set(v);
  }

}
