package io.github.pavansharma36.saas.core.broker.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import io.github.pavansharma36.core.common.factory.ExecutorFactory;
import io.github.pavansharma36.core.common.utils.CoreConstants;
import io.github.pavansharma36.saas.core.broker.common.api.DelayedQueue;
import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.producer.ProducerTemplate;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.RabbitQueue;
import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import jakarta.annotation.PreDestroy;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQProducerTemplate implements ProducerTemplate {

  private final ObjectPool<Channel> channelPool;

  public RabbitMQProducerTemplate(ConnectionFactory connectionFactory)
      throws IOException, TimeoutException {
    this.channelPool =
        new GenericObjectPool<>(new RabbitMQChannelPool(
            connectionFactory.newConnection(ExecutorFactory.executorService(),
                String.format("%s-%s-%s",
                    CoreConstants.APP_NAME, CoreConstants.APP_TYPE.getName().toLowerCase(),
                    CoreConstants.PROCESS_UUID))));
  }

  @Override
  public void produce(Queue queue, MessagePriority priority, byte[] data) {
    try {
      RabbitQueue rabbitQueue = (RabbitQueue) queue;
      String exchange = rabbitQueue.exchange().getName();
      String routingKey = rabbitQueue.routingKey(priority);
      produce(exchange, routingKey, data);
    } catch (Exception e) {
      throw new ServerRuntimeException(e);
    }
  }

  @Override
  public void produce(Queue queue, DelayedQueue delayedQueue, byte[] data) {
    try {
      RabbitQueue rabbitQueue = (RabbitQueue) queue;
      String exchange = rabbitQueue.exchange().getName();
      String routingKey = rabbitQueue.routingKey(delayedQueue);
      produce(exchange, routingKey, data);
    } catch (Exception e) {
      throw new ServerRuntimeException(e);
    }
  }

  private void produce(String exchange, String routingKey, byte[] data) throws Exception {
    log.info("Dispatching message to exchange: {} with routing key: {}", exchange, routingKey);
    Channel channel = channelPool.borrowObject();
    try {
      channel.basicPublish(exchange, routingKey, true, null, data);
    } finally {
      channelPool.returnObject(channel);
    }
  }

  @Override
  public String type() {
    return RabbitQueue.RABBITMQ;
  }

  @PreDestroy
  public void stop() throws IOException, TimeoutException {
    channelPool.close();
  }
}
