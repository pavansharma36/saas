package io.github.pavansharma36.core.common.pubsub.subscriber;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractSubscriptionHandler<T extends Serializable>
    implements SubscriptionHandler {

  private final Class<T> clazz;

  @Override
  public final void handle(Serializable data) {
    T tData = clazz.cast(data);
    handleImpl(tData);
  }

  protected abstract void handleImpl(T data);
}
