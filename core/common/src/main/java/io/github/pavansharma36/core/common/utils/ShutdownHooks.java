package io.github.pavansharma36.core.common.utils;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShutdownHooks {

  private static final Set<ShutdownHookInfo> HOOKS = new TreeSet<>(
      Comparator.comparingInt(ShutdownHookInfo::getOrder));

  static {
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      HOOKS.forEach(h -> {
        try {
          log.info("Running shutdown hook {}", h);
          h.getRunnable().run();
        } catch (Exception e) {
          log.error("Error while running shutdown hook {}", e.getMessage(), e);
        }
      });
    }));
  }

  public static void registerShutdownHook(int order, Runnable runnable) {
    HOOKS.add(new ShutdownHookInfo(order, runnable));
  }

  @Getter
  @RequiredArgsConstructor
  private static class ShutdownHookInfo {
    private final int order;
    private final Runnable runnable;
  }


}
