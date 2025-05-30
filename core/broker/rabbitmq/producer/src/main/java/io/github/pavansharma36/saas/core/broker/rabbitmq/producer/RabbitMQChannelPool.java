package io.github.pavansharma36.saas.core.broker.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

@Slf4j
@RequiredArgsConstructor
public class RabbitMQChannelPool extends BasePooledObjectFactory<Channel> {

  private final Connection connection;

  @Override
  public Channel create() throws Exception {
    Channel channel = connection.createChannel();
    channel.addReturnListener(
        (code, message, exchange, routingKey, basicProperties, bytes) -> log.error(
            "Message was returned : code:{}, text:{} exchange: {}, routingKey: {}", code,
            message, exchange, routingKey));
    return channel;
  }

  @Override
  public void destroyObject(PooledObject<Channel> p) throws Exception {
    super.destroyObject(p);
    log.info("Closing channel {}", p.getObject());
    p.getObject().close();
  }

  @Override
  public PooledObject<Channel> wrap(Channel channel) {
    return new DefaultPooledObject<>(channel);
  }
}
