package io.github.pavansharma36.core.common.factory;

import io.github.pavansharma36.core.common.context.providers.ThreadLocalContextProviders;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContextAwareExecutorService {

  private final ExecutorService executorService = ExecutorFactory.executorService();

  public <T> Future<T> execute(Callable<T> callable) {
    final Map<String, byte[]> context = ThreadLocalContextProviders.serialize();
    return executorService.submit(() -> {
      ThreadLocalContextProviders.set(context);
      try {
        return callable.call();
      } finally {
        ThreadLocalContextProviders.clearAll();
      }
    });
  }

  public void execute(Runnable runnable) {
    final Map<String, byte[]> context = ThreadLocalContextProviders.serialize();
    executorService.submit(() -> {
      ThreadLocalContextProviders.set(context);
      try {
        runnable.run();
      } finally {
        ThreadLocalContextProviders.clearAll();
      }
    });
  }

}
