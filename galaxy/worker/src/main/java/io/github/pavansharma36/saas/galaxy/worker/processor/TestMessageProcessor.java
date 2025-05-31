package io.github.pavansharma36.saas.galaxy.worker.processor;

import io.github.pavansharma36.core.common.mutex.service.LockService;
import io.github.pavansharma36.saas.core.broker.common.api.MessageType;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageDto;
import io.github.pavansharma36.saas.core.broker.common.dao.MessageInfoDao;
import io.github.pavansharma36.saas.core.broker.consumer.processor.AbstractMessageProcessor;
import io.github.pavansharma36.saas.galaxy.common.broker.GalaxyMessageType;
import io.github.pavansharma36.saas.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestMessageProcessor extends AbstractMessageProcessor<MessageDto> {

  public TestMessageProcessor(MessageInfoDao messageInfoDao,
                              LockService lockService) {
    super(MessageDto.class, messageInfoDao, lockService);
  }

  @Override
  protected void processImpl(MessageDto messageDto) {
    log.info("Processing message {}", messageDto);
    int sleep = RandomUtils.nextInt(10, 100);
    log.warn("Sleeping for {} millis", sleep);
    Utils.sleepQuietly(sleep);
  }

  @Override
  public MessageType messageType() {
    return GalaxyMessageType.TEST;
  }
}
