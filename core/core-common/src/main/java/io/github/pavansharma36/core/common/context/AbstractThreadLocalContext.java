package io.github.pavansharma36.core.common.context;

import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import java.util.Optional;

public abstract class AbstractThreadLocalContext<T> implements ThreadLocalContext {

  protected final ThreadLocal<T> context = new ThreadLocal<>();

  public Optional<T> get() {
    return Optional.ofNullable(context.get());
  }

  public T getOrThrow() {
    T value = context.get();
    if (value == null) {
      throw new ServerRuntimeException(
          String.format("%s doesnt have context", getClass().getSimpleName()));
    }
    return value;
  }

  public void set(T value) {
    context.set(value);
  }

  @Override
  public void clearContext() {
    context.remove();
  }

}
