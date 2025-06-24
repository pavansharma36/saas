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
    Optional<T> ot = initializeContext();
    ot.ifPresent(this::set);
    return ot;
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

  protected abstract Optional<T> initializeContext();
}
