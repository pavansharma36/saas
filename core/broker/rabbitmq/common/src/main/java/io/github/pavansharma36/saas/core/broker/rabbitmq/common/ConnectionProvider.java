package io.github.pavansharma36.saas.core.broker.rabbitmq.common;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.github.pavansharma36.saas.core.common.factory.ExecutorFactory;
import io.github.pavansharma36.saas.core.common.utils.CoreConstants;
import io.github.pavansharma36.saas.utils.Utils;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ConnectionProvider implements Closeable {

  private final ConnectionFactory connectionFactory;
  private volatile Connection connection;

  public synchronized Connection getConnection() throws IOException, TimeoutException {
    if (connection != null && !connection.isOpen()) {
      close();
      connection = null;
    }
    if (connection == null) {
      connection = connectionFactory.newConnection(ExecutorFactory.executorService(),
          String.format("%s-%s-%s",
              CoreConstants.APP_NAME, CoreConstants.APP_TYPE.getName().toLowerCase(),
              CoreConstants.PROCESS_UUID));
    }
    return connection;
  }


  @Override
  public void close() throws IOException {
    if (connection != null) {
      Utils.executeQuietly(() -> {
        connection.close();
        return null;
      }, log);
    }
  }
}
