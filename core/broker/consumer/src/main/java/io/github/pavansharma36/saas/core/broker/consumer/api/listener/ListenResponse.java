package io.github.pavansharma36.saas.core.broker.consumer.api.listener;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ListenResponse {
  private final String listeningQueue;

  public abstract boolean isListening();
}
