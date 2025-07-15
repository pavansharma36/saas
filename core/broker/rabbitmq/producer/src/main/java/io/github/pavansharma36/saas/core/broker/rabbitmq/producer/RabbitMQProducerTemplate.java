package io.github.pavansharma36.saas.core.broker.rabbitmq.producer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.core.common.factory.ExecutorFactory;
import io.github.pavansharma36.core.common.utils.CoreConstants;
import io.github.pavansharma36.core.common.utils.ShutdownHooks;
import io.github.pavansharma36.core.common.validation.ServerRuntimeException;
import io.github.pavansharma36.saas.core.broker.common.api.DelayedQueue;
import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageSerializablePayload;
import io.github.pavansharma36.saas.core.broker.producer.ProducerTemplate;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.queue.RabbitQueue;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
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
    Connection connection = connectionFactory.newConnection(ExecutorFactory.executorService(),
        String.format("%s-%s-%s",
            CoreConstants.APP_NAME, CoreConstants.APP_TYPE.getName().toLowerCase(),
            CoreConstants.PROCESS_UUID));
    this.channelPool =
        new GenericObjectPool<>(new RabbitMQChannelPool(connection));
    ShutdownHooks.registerShutdownHook(1000, "Stop RabbitMQ Consumer Channel Pool",
        this.channelPool::close);
    ShutdownHooks.registerShutdownHook(1100, "Disconnect RabbitMQ Consumer Connection", () -> {
      try {
        connection.close();
      } catch (IOException e) {
        throw new ServerRuntimeException(e);
      }
    });
  }

  @Override
  public void produce(Queue queue, MessagePriority priority,
                      MessageSerializablePayload payload,
                      Function<MessageSerializablePayload, byte[]> serializer) {
    try {
      RabbitQueue rabbitQueue = (RabbitQueue) queue;
      String exchange = rabbitQueue.exchange().getName();
      String routingKey = rabbitQueue.routingKey(priority);
      produce(exchange, routingKey, payload, serializer);
    } catch (Exception e) {
      throw new ServerRuntimeException(e);
    }
  }

  @Override
  public void produce(Queue queue, DelayedQueue delayedQueue, MessageSerializablePayload payload,
                      Function<MessageSerializablePayload, byte[]> serializer) {
    try {
      RabbitQueue rabbitQueue = (RabbitQueue) queue;
      String exchange = rabbitQueue.exchange().getName();
      String routingKey = rabbitQueue.routingKey(delayedQueue);
      produce(exchange, routingKey, payload, serializer);
    } catch (Exception e) {
      throw new ServerRuntimeException(e);
    }
  }

  private void produce(String exchange, String routingKey,
                       MessageSerializablePayload payload,
                       Function<MessageSerializablePayload, byte[]> serializer)
      throws Exception {
    log.info("Dispatching message to exchange: {} with routing key: {}", exchange, routingKey);
    Channel channel = channelPool.borrowObject();
    try {

      // set delivery mode to persistent when db tracking is enabled.
      AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
          .deliveryMode(payload.getMessageId() != null ? 2 : 1)
          .messageId(payload.getMessageId())
          .type(payload.getMessageType())
          .appId(CoreConstants.APP_NAME)
          .build();
      channel.basicPublish(exchange, routingKey, true, props, serializer.apply(payload));

      try {
        channel.waitForConfirmsOrDie(Config.getLong("rabbitmq.publish.timeout", 15_000));
      } catch (TimeoutException e) {
        throw new ServerRuntimeException(e);
      }

    } finally {
      channelPool.returnObject(channel);
    }
  }

  @Override
  public String type() {
    return RabbitQueue.RABBITMQ;
  }
}
