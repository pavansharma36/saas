package io.github.pavansharma36.saas.core.broker.rabbitmq.consumer.poller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.GetResponse;
import io.github.pavansharma36.saas.core.common.validation.ServerRuntimeException;
import io.github.pavansharma36.saas.core.broker.consumer.api.poller.PollerConsumer;
import java.io.IOException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RabbitMQPollerConsumer implements PollerConsumer<RabbitMQPollResponse> {

  private final Channel channel;

  public RabbitMQPollerConsumer(Connection connection) throws IOException {
    this.channel = connection.createChannel();
  }

  @Override
  public Optional<RabbitMQPollResponse> poll(String queueName) {
    try {
      GetResponse res = channel.basicGet(queueName, false);
      if (res != null) {
        return Optional.of(new RabbitMQPollResponse(res.getBody(), res));
      }
    } catch (IOException e) {
      throw new ServerRuntimeException(e);
    }
    return Optional.empty();
  }

  @Override
  public void ack(RabbitMQPollResponse response) {
    try {
      channel.basicAck(response.getGetResponse().getEnvelope().getDeliveryTag(), false);
    } catch (IOException e) {
      throw new ServerRuntimeException(e);
    }
  }
}
