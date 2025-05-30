package io.github.pavansharma36.saas.core.broker.rabbitmq.consumer;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.github.pavansharma36.core.common.factory.ExecutorFactory;
import io.github.pavansharma36.core.common.utils.CoreConstants;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.consumer.api.listener.ListenerConsumer;
import io.github.pavansharma36.saas.core.broker.consumer.api.poller.ConsumerFactory;
import io.github.pavansharma36.saas.core.broker.consumer.api.poller.PollerConsumer;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.RabbitQueue;
import io.github.pavansharma36.saas.core.broker.rabbitmq.consumer.listener.RabbitMQListenResponse;
import io.github.pavansharma36.saas.core.broker.rabbitmq.consumer.listener.RabbitMQListenerConsumer;
import io.github.pavansharma36.saas.core.broker.rabbitmq.consumer.poller.RabbitMQPollResponse;
import io.github.pavansharma36.saas.core.broker.rabbitmq.consumer.poller.RabbitMQPollerConsumer;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import jakarta.annotation.PreDestroy;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumerFactory
    implements ConsumerFactory<RabbitMQListenResponse, RabbitMQPollResponse> {

  private final Connection connection;

  public RabbitMQConsumerFactory(ConnectionFactory connectionFactory)
      throws IOException, TimeoutException {
    connection =
        connectionFactory.newConnection(ExecutorFactory.executorService(), String.format("%s-%s-%s",
            CoreConstants.APP_NAME, CoreConstants.APP_TYPE.getName().toLowerCase(),
            CoreConstants.PROCESS_UUID));
  }

  @Override
  public PollerConsumer<RabbitMQPollResponse> createPollerConsumer(Queue queue) {
    try {
      return new RabbitMQPollerConsumer(connection);
    } catch (IOException e) {
      throw new ServerRuntimeException(e);
    }
  }

  @Override
  public ListenerConsumer<RabbitMQListenResponse> createListenerConsumer(Queue queue) {
    try {
      return new RabbitMQListenerConsumer(connection);
    } catch (IOException e) {
      throw new ServerRuntimeException(e);
    }
  }

  @PreDestroy
  public void stop() throws IOException {
    connection.close();
  }

  @Override
  public String type() {
    return RabbitQueue.RABBITMQ;
  }
}
