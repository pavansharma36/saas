package io.github.pavansharma36.core.common.context.providers;

import io.github.pavansharma36.core.common.context.ThreadLocalContext;
import io.github.pavansharma36.saas.utils.collections.CollectionUtils;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MDCContextProvider implements ThreadLocalContext {

  public static void put(String key, String value) {
    MDC.put(key, value);
  }

  @Override
  public void clearContext() {
    MDC.clear();
  }

  @Override
  public byte[] toByteArray() {
    Map<String, String> mdc = MDC.getCopyOfContextMap();
    if (CollectionUtils.isEmpty(mdc)) {
      return new byte[0];
    }
    String json = JsonUtils.toJson(mdc);
    return json.getBytes(StandardCharsets.UTF_8);
  }

  @Override
  public void setFromByteArray(byte[] value) {
    Map<String, String> mdc =
        JsonUtils.mapFromJson(new String(value, StandardCharsets.UTF_8), String.class,
            String.class);
    mdc.forEach(MDC::put);
  }
}
