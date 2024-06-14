package io.github.pavansharma36.core.common.context;

public interface ThreadLocalContext<T> {

  T get();

  void set(T value);

  /**
   * to clear all context after procesing request.
   */
  void clearContext();

  /**
   * to serialize context to pass requests across services using messaging.
   *
   * @return - serialized value
   */
  byte[] toByteArray();

  /**
   * set context in current thread from byte array.
   *
   * @param value - byte  array returned from toByteArray.
   */
  void setFromByteArray(byte[] value);

}
