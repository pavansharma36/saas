package io.github.pavansharma36.saas.core.broker.rabbitmq.consumer.listener;

import io.github.pavansharma36.saas.core.broker.consumer.api.listener.ListenResponse;
import lombok.Getter;

@Getter
public class RabbitMQListenResponse extends ListenResponse {
  private final String consumerTag;

  public RabbitMQListenResponse(String listeningQueue, String consumerTag) {
    super(listeningQueue);
    this.consumerTag = consumerTag;
  }
}
