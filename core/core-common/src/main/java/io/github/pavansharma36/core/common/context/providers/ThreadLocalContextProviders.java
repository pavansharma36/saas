package io.github.pavansharma36.core.common.context.providers;

import io.github.pavansharma36.core.common.context.ThreadLocalContext;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class ThreadLocalContextProviders {

  private static final Set<ThreadLocalContext<?>> CONTEXTS = new HashSet<>();

  public static synchronized void register(ThreadLocalContext<?> context) {
    CONTEXTS.add(context);
  }

  public static void clearAll() {
    CONTEXTS.forEach(ThreadLocalContext::clearContext);
  }

}
