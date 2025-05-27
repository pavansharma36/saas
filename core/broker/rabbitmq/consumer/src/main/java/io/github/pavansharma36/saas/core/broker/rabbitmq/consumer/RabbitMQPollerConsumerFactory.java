package io.github.pavansharma36.saas.core.broker.rabbitmq.consumer;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.consumer.api.poller.PollerConsumer;
import io.github.pavansharma36.saas.core.broker.consumer.api.poller.PollerConsumerFactory;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import jakarta.annotation.PreDestroy;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQPollerConsumerFactory implements PollerConsumerFactory {

  private final Connection connection;

  public RabbitMQPollerConsumerFactory(ConnectionFactory connectionFactory)
      throws IOException, TimeoutException {
    connection = connectionFactory.newConnection();
  }

  @Override
  public PollerConsumer<?> createPollerConsumer(Queue queue) {
    try {
      return new RabbitMQPollerConsumer(connection);
    } catch (IOException e) {
      throw new ServerRuntimeException(e);
    }
  }

  @PreDestroy
  public void stop() throws IOException {
    connection.close();
  }

}
