package io.github.pavansharma36.saas.core.broker.rabbitmq.consumer.listener;

import com.rabbitmq.client.Channel;
import io.github.pavansharma36.saas.core.broker.consumer.api.listener.ListenResponse;
import lombok.Getter;

@Getter
public class RabbitMQListenResponse extends ListenResponse {
  private final String consumerTag;
  private final Channel channel;

  public RabbitMQListenResponse(String listeningQueue, String consumerTag,
                                Channel channel) {
    super(listeningQueue);
    this.consumerTag = consumerTag;
    this.channel = channel;
  }

  @Override
  public boolean isListening() {
    return channel.isOpen();
  }
}
