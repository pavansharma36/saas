package io.github.pavansharma36.core.common.factory;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class ExecutorFactory {

  private static final Object LOCK = new Object();
  private static ExecutorService executorService;
  private static ScheduledExecutorService scheduledExecutorService;

  public static ExecutorService executorService() {
    if (executorService == null) {
      ExecutorFactory.init();
    }
    return executorService;
  }

  public static ScheduledExecutorService scheduledExecutorService() {
    if (scheduledExecutorService == null) {
      ExecutorFactory.initScheduled();
    }
    return scheduledExecutorService;
  }

  private static void init() {
    synchronized (LOCK) {
      if (executorService == null) {
        int corePoolSize = Config.getInt("executor.core.pool.size", 5);
        executorService = Executors.newFixedThreadPool(corePoolSize);
        ExecutorFactory.registerShutdownHook(executorService);
      }
    }
  }

  private static void initScheduled() {
    synchronized (LOCK) {
      if (scheduledExecutorService == null) {
        int corePoolSize = Config.getInt("scheduled.executor.core.pool.size", 3);
        scheduledExecutorService = new ScheduledThreadPoolExecutor(corePoolSize);
        ExecutorFactory.registerShutdownHook(scheduledExecutorService);
      }
    }
  }

  private static void registerShutdownHook(ExecutorService executorService) {
    long executorShutdownGracePeriod = Config.getLong("executor.shutdown.grace.period", 30);
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      executorService.shutdown();
      try {
        boolean s = executorService.awaitTermination(executorShutdownGracePeriod, TimeUnit.SECONDS);
        if (s) {
          log.info("Shutdown of executor service {}, success {}", executorService, s);
        } else {
          log.error("Error while shutting down executor service {}", s);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new ServerRuntimeException(e);
      }
    }));
  }

}
