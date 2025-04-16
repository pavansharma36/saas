package io.github.pavansharma36.saas.core.pubsub.redis;

import io.github.pavansharma36.core.common.pubsub.subscriber.Subscriber;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Slf4j
@RequiredArgsConstructor
public class RedisSubscriber implements Subscriber {
  private final String namespace;
  private final RedisConnectionFactory connectionFactory;

  private MessageListener getListener(Consumer<byte[]> consumer) {
    return ((message, pattern) -> {
      consumer.accept(message.getBody());
    });
  }


  @Override
  public void subscribe(Consumer<byte[]> consumer, String... channels) {
    Runnable runnable = () -> {
      while (!Thread.currentThread().isInterrupted()) {
        log.info("Subscribing to channels {}", Arrays.asList(channels));
        connectionFactory.getConnection()
            .subscribe(getListener(consumer),
                Arrays.stream(channels).map(c -> c.getBytes(
                    StandardCharsets.UTF_8)).toArray(byte[][]::new));
      }
    };
    Thread t = new Thread(runnable);
    t.setName("RedisSubscriber");
    t.setDaemon(false);
    t.start();
  }

}
