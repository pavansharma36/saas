package io.github.pavansharma36.saas.auth.worker.processor;

import io.github.pavansharma36.saas.core.common.mutex.bean.DefaultLock;
import io.github.pavansharma36.saas.core.common.mutex.bean.Lock;
import io.github.pavansharma36.saas.core.common.mutex.bean.LockType;
import io.github.pavansharma36.saas.core.common.mutex.service.LockService;
import io.github.pavansharma36.saas.core.broker.common.api.MessageType;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageSerializablePayload;
import io.github.pavansharma36.saas.core.broker.common.dao.MessageInfoDao;
import io.github.pavansharma36.saas.core.broker.consumer.processor.AbstractMessageProcessor;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.message.TenantCreatedMessageDto;
import io.github.pavansharma36.saas.core.broker.rabbitmq.common.message.TenantEventMessageTypes;
import io.github.pavansharma36.saas.utils.Utils;
import java.time.Duration;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TenantCreatedMessageProcessor
    extends AbstractMessageProcessor<TenantCreatedMessageDto> {

  public TenantCreatedMessageProcessor(MessageInfoDao messageInfoDao,
                                       LockService lockService) {
    super(TenantCreatedMessageDto.class, messageInfoDao, lockService);
  }

  @Override
  protected void processImpl(TenantCreatedMessageDto messageDto) {
    log.warn("Handling tenant created event {}", messageDto);
    Utils.sleepQuietly(10000L);
  }

  @Override
  public MessageType messageType() {
    return TenantEventMessageTypes.TENANT_CREATED;
  }

  @Override
  protected List<Lock> requiredLocks(MessageSerializablePayload payload) {
    return List.of(
        DefaultLock.builder().name("tenant_create").type(LockType.FIXED).maxCount(1).duration(
            Duration.ofMinutes(3L)).build());
  }
}
