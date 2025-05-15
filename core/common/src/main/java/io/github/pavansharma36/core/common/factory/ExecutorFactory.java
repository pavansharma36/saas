package io.github.pavansharma36.core.common.factory;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.saas.utils.Utils;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
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
        int corePoolSize = Config.getInt("executor.core.pool.size", 1);
        int maxPoolSize = Config.getInt("executor.max.pool.size", Runtime.getRuntime()
            .availableProcessors());
        int keepAliveSeconds = Config.getInt("executor.keep.alive.seconds", 60);
        executorService =
            new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveSeconds, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), r -> {
              Thread t = new Thread(r);
              t.setName(String.format("Executor-%s", Utils.randomRequestId()));
              return t;
            });
        long executorShutdownGracePeriod = Config.getLong("executor.shutdown.grace.period", 30);
        ExecutorFactory.registerShutdownHook(executorService, executorShutdownGracePeriod);
      }
    }
  }

  private static void initScheduled() {
    synchronized (LOCK) {
      if (scheduledExecutorService == null) {
        int corePoolSize = Config.getInt("scheduled.executor.core.pool.size", 1);
        scheduledExecutorService = Executors.newScheduledThreadPool(corePoolSize, r -> {
          Thread t = new Thread(r);
          t.setName(String.format("Scheduled-%s", Utils.randomRequestId()));
          return t;
        });
        long executorShutdownGracePeriod =
            Config.getLong("scheduled.executor.shutdown.grace.period", 1);
        ExecutorFactory.registerShutdownHook(scheduledExecutorService, executorShutdownGracePeriod);
      }
    }
  }

  private static void registerShutdownHook(ExecutorService executorService,
                                           long awaitSeconds) {

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      executorService.shutdown();
      try {
        boolean s = executorService.awaitTermination(awaitSeconds, TimeUnit.SECONDS);
        if (s) {
          log.info("Shutdown of executor service {}, success {}", executorService, s);
        } else {
          log.error("Error while shutting down executor service {}", executorService);
          List<Runnable> runnables = executorService.shutdownNow();
          log.warn("Shutdown now:{}, dropped tasks:{}", executorService, runnables.size());
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new ServerRuntimeException(e);
      }
    }));
  }

}
