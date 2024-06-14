package io.github.pavansharma36.core.common.context;

public abstract class AbstractThreadLocalContext<T> implements ThreadLocalContext<T> {

  protected final ThreadLocal<T> context = new ThreadLocal<>();

  @Override
  public T get() {
    return context.get();
  }

  @Override
  public void set(T value) {
    context.set(value);
  }

  @Override
  public void clearContext() {
    context.remove();
  }

}
