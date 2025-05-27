package io.github.pavansharma36.core.common.context.providers;

import io.github.pavansharma36.core.common.context.ThreadLocalContext;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class ThreadLocalContextProviders {

  private static final Map<String, ThreadLocalContext> CLASS_CONTEXT_MAP = new HashMap<>();
  private static final Set<ThreadLocalContext> CONTEXTS = new HashSet<>();

  public static synchronized void register(ThreadLocalContext context) {
    CLASS_CONTEXT_MAP.put(context.getClass().getName(), context);
    CONTEXTS.add(context);
  }

  public static void clearAll() {
    CONTEXTS.forEach(ThreadLocalContext::clearContext);
  }

  public static Map<String, byte[]> serialize() {
    Map<String, byte[]> s = new HashMap<>();
    for (Map.Entry<String, ThreadLocalContext> c : CLASS_CONTEXT_MAP.entrySet()) {
      byte[] b = c.getValue().toByteArray();
      if (b != null && b.length > 0) {
        s.put(c.getKey(), b);
      }
    }
    return s;
  }

  public static void set(Map<String, byte[]> s) {
    for (Map.Entry<String, byte[]> c : s.entrySet()) {
      ThreadLocalContext t = CLASS_CONTEXT_MAP.get(c.getKey());
      if (t != null) {
        t.setFromByteArray(c.getValue());
      } else {
        log.warn("Context {} not found, skipping deserialization", c.getKey());
      }
    }
  }

}
