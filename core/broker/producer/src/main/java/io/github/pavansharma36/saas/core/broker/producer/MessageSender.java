package io.github.pavansharma36.saas.core.broker.producer;

import io.github.pavansharma36.saas.core.broker.common.BrokerUtils;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.common.bean.Message;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageSerializablePayload;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageStatus;
import io.github.pavansharma36.saas.core.broker.common.dao.MessageInfoDao;
import io.github.pavansharma36.saas.core.broker.common.dao.model.MessageInfo;
import io.github.pavansharma36.saas.core.common.context.providers.ThreadLocalContextProviders;
import io.github.pavansharma36.saas.core.common.utils.CoreConstants;
import io.github.pavansharma36.saas.core.common.utils.PreCondition;
import io.github.pavansharma36.saas.core.common.validation.CoreErrorCode;
import io.github.pavansharma36.saas.core.common.validation.ServerRuntimeException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {

  private final MessageInfoDao messageInfoDao;
  private final Map<String, ProducerTemplate> templateMap;

  public MessageSender(MessageInfoDao dao, List<ProducerTemplate> producerTemplates) {
    this.messageInfoDao = dao;
    templateMap =
        producerTemplates.stream().collect(Collectors.toMap(ProducerTemplate::type, k -> k));
  }

  private void validate(Queue queue, Message message) {
    PreCondition.assertNotNull(queue, CoreErrorCode.SERVER_ERROR);
    PreCondition.assertNotNull(message, CoreErrorCode.SERVER_ERROR);
    PreCondition.assertNotNull(message.getPriority(), CoreErrorCode.SERVER_ERROR);
    PreCondition.assertNotNull(message.getMessageType(), CoreErrorCode.SERVER_ERROR);
    PreCondition.assertNotNull(message.getMessageDto(), CoreErrorCode.SERVER_ERROR);
    if (message.getMessageDto().getExpireAt() != null && message.getMessageDto().getExpireAt()
        .before(new Date())) {
      throw new ServerRuntimeException(
          String.format("Message expiration time is in the past for message %s", message));
    }
    PreCondition.assertCondition(queue.supportedPriorities().contains(message.getPriority()),
        CoreErrorCode.SERVER_ERROR);
  }

  private Optional<String> createMessageInfo(Queue queue, Message message) {
    if (message.isTrackWithDatabase()) {
      MessageInfo info = new MessageInfo();
      info.setQueueName(queue.getName());
      info.setMessageType(message.getMessageType().getName());
      info.setDispatchedAt(new Date());
      info.setStatus(MessageStatus.DISPATCHED);
      info.setOrderKey(message.getOrderKey());
      info.setLockOnProcess(message.isLockOnProcess());
      info.setIdempotent(message.isIdempotent());
      info.setExpireAt(message.getMessageDto().getExpireAt());
      info.setOwner(CoreConstants.APP_NAME);

      return Optional.of(messageInfoDao.insert(info).getId());
    }
    return Optional.empty();
  }

  public void send(Queue queue, Message message) {
    validate(queue, message);
    Optional<String> mId = createMessageInfo(queue, message);

    MessageSerializablePayload payload = new MessageSerializablePayload();
    payload.setMessageId(mId.orElse(null));
    payload.setMessageType(message.getMessageType().getName());
    payload.setPriority(message.getPriority());
    payload.setMessageDto(message.getMessageDto());
    payload.setContextMap(ThreadLocalContextProviders.serialize());

    Optional.ofNullable(templateMap.get(queue.type()))
        .orElseThrow(() -> new ServerRuntimeException(
            String.format("Producer for %s not found", queue.type())))
        .produce(queue, message.getPriority(), payload, BrokerUtils::serialize);
  }

}
