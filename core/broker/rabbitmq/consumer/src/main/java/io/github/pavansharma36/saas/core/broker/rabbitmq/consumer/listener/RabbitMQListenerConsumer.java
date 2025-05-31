package io.github.pavansharma36.saas.core.broker.rabbitmq.consumer.listener;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.core.common.utils.CoreConstants;
import io.github.pavansharma36.saas.core.broker.consumer.api.listener.ListenerConsumer;
import io.github.pavansharma36.saas.utils.Utils;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import java.io.IOException;
import java.util.function.Consumer;

public class RabbitMQListenerConsumer implements ListenerConsumer<RabbitMQListenResponse> {

  private final Channel channel;

  public RabbitMQListenerConsumer(Connection connection) throws IOException {
    this.channel = connection.createChannel();
    channel.basicQos(Config.getInt("rabbitmq.listen.prefetch.count", 1));
  }

  @Override
  public RabbitMQListenResponse listen(String queueName, Consumer<byte[]> consumer) {
    String consumerTag = String.format("%s-%s", CoreConstants.APP_NAME, Utils.randomRequestId());
    DefaultConsumer c = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope,
                                 AMQP.BasicProperties properties, byte[] body) throws IOException {
        consumer.accept(body);
        channel.basicAck(envelope.getDeliveryTag(), false);
      }
    };

    try {
      channel.basicConsume(queueName, false, consumerTag, c);
    } catch (IOException e) {
      throw new ServerRuntimeException(e);
    }
    return new RabbitMQListenResponse(queueName, consumerTag);
  }

  @Override
  public void stop(RabbitMQListenResponse listener) {
    try {
      channel.basicCancel(listener.getConsumerTag());
    } catch (IOException e) {
      throw new ServerRuntimeException(e);
    }
  }

}
