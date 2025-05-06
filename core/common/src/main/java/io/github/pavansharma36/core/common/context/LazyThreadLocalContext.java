package io.github.pavansharma36.core.common.context;

import java.util.Optional;

public abstract class LazyThreadLocalContext<T> extends AbstractThreadLocalContext<T>
    implements ThreadLocalContext {

  @Override
  public Optional<T> get() {
    T t = context.get();
    if (t != null) {
      return Optional.of(t);
    }
    initializeContext();
    return super.get();
  }

  @Override
  public T getOrThrow() {
    T t = context.get();
    if (t != null) {
      return t;
    }
    initializeContext();
    return super.getOrThrow();
  }

  protected abstract void initializeContext();
}
