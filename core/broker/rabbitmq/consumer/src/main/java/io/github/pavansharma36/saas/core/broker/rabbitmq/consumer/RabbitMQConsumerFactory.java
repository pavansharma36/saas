package io.github.pavansharma36.saas.core.broker.rabbitmq.consumer;

import com.rabbitmq.client.ConnectionFactory;
import io.github.pavansharma36.saas.core.broker.consumer.api.listener.ListenerConsumer;
import io.github.pavansharma36.saas.core.broker.consumer.api.poller.ConsumerFactory;
import io.github.pavansharma36.saas.core.broker.consumer.api.poller.PollerConsumer;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.ConnectionProvider;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.queue.RabbitQueue;
import io.github.pavansharma36.saas.core.broker.rabbitmq.consumer.listener.RabbitMQListenResponse;
import io.github.pavansharma36.saas.core.broker.rabbitmq.consumer.listener.RabbitMQListenerConsumer;
import io.github.pavansharma36.saas.core.broker.rabbitmq.consumer.poller.RabbitMQPollResponse;
import io.github.pavansharma36.saas.core.broker.rabbitmq.consumer.poller.RabbitMQPollerConsumer;
import io.github.pavansharma36.saas.core.common.utils.ShutdownHooks;
import io.github.pavansharma36.saas.core.common.validation.ServerRuntimeException;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumerFactory
    implements ConsumerFactory<RabbitMQListenResponse, RabbitMQPollResponse> {

  private final ConnectionProvider connectionProvider;

  public RabbitMQConsumerFactory(ConnectionFactory connectionFactory) {
    connectionProvider = new ConnectionProvider(connectionFactory);
    ShutdownHooks.registerShutdownHook(1100, "Disconnect RabbitMQ Consumer Connection", () -> {
      try {
        connectionProvider.close();
      } catch (IOException e) {
        throw new ServerRuntimeException(e);
      }
    });
  }

  @Override
  public PollerConsumer<RabbitMQPollResponse> createPollerConsumer() {
    try {
      return new RabbitMQPollerConsumer(connectionProvider.getConnection());
    } catch (IOException | TimeoutException e) {
      throw new ServerRuntimeException(e);
    }
  }

  @Override
  public ListenerConsumer<RabbitMQListenResponse> createListenerConsumer() {
    try {
      return new RabbitMQListenerConsumer(connectionProvider);
    } catch (IOException e) {
      throw new ServerRuntimeException(e);
    }
  }

  @Override
  public String type() {
    return RabbitQueue.RABBITMQ;
  }
}
