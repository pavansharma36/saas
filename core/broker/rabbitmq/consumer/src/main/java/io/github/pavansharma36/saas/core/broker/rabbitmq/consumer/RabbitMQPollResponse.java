package io.github.pavansharma36.saas.core.broker.rabbitmq.consumer;

import com.rabbitmq.client.GetResponse;
import io.github.pavansharma36.saas.core.broker.consumer.api.poller.PollResponse;
import lombok.Getter;

@Getter
public class RabbitMQPollResponse extends PollResponse {

  private final GetResponse getResponse;

  public RabbitMQPollResponse(byte[] body, GetResponse getResponse) {
    super(body);
    this.getResponse = getResponse;
  }

}
