package io.github.pavansharma36.core.common.context;

public abstract class AbstractThreadLocalContext<T> implements ThreadLocalContext {

  protected final ThreadLocal<T> context = new ThreadLocal<>();

  public T get() {
    return context.get();
  }

  public void set(T value) {
    context.set(value);
  }

  @Override
  public void clearContext() {
    context.remove();
  }

}
