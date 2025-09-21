package io.github.pavansharma36.saas.core.broker.rabbitmq.consumer.listener;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.consumer.api.listener.ListenerConsumer;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.ConnectionProvider;
import io.github.pavansharma36.saas.core.common.config.Config;
import io.github.pavansharma36.saas.core.common.utils.CoreConstants;
import io.github.pavansharma36.saas.core.common.validation.ServerRuntimeException;
import io.github.pavansharma36.saas.utils.Utils;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RabbitMQListenerConsumer implements ListenerConsumer<RabbitMQListenResponse> {

  private final ConnectionProvider connectionProvider;

  public RabbitMQListenerConsumer(ConnectionProvider connectionProvider)
      throws IOException {
    this.connectionProvider = connectionProvider;
  }

  private synchronized Channel initiateChannel() {
    try {
      Channel channel = connectionProvider.getConnection().createChannel();
      channel.basicQos(Config.getInt("rabbitmq.listen.prefetch.count", 1));
      return channel;
    } catch (IOException | TimeoutException e) {
      throw new ServerRuntimeException(e);
    }
  }

  @Override
  public RabbitMQListenResponse listen(Queue queue, Consumer<byte[]> consumer) {
    Channel channel = initiateChannel();
    String consumerTag = String.format("%s-%s-%s", CoreConstants.APP_NAME, CoreConstants.APP_TYPE,
        Utils.randomRequestId());
    DefaultConsumer c = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope,
                                 AMQP.BasicProperties properties, byte[] body) throws IOException {
        consumer.accept(body);
        channel.basicAck(envelope.getDeliveryTag(), false);
      }
    };

    try {
      channel.basicConsume(queue.getName(), false, consumerTag, c);
    } catch (IOException e) {
      throw new ServerRuntimeException(e);
    }
    return new RabbitMQListenResponse(queue.getName(), consumerTag, channel);
  }

  @Override
  public void stop(RabbitMQListenResponse listener) {
    Utils.executeQuietly(() -> {
      listener.getChannel().basicCancel(listener.getConsumerTag());
      return null;
    }, log);
  }

}
