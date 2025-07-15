package io.github.pavansharma36.saas.core.broker.consumer.processor;

import io.github.pavansharma36.core.common.mutex.bean.CompositeLockInfo;
import io.github.pavansharma36.core.common.mutex.bean.DefaultLock;
import io.github.pavansharma36.core.common.mutex.bean.Lock;
import io.github.pavansharma36.core.common.mutex.service.LockService;
import io.github.pavansharma36.core.common.utils.CoreConstants;
import io.github.pavansharma36.core.common.validation.ServerRuntimeException;
import io.github.pavansharma36.saas.core.broker.common.BrokerUtils;
import io.github.pavansharma36.saas.core.broker.common.api.Queue;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageDto;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageSerializablePayload;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageStatus;
import io.github.pavansharma36.saas.core.broker.common.dao.MessageInfoDao;
import io.github.pavansharma36.saas.core.broker.common.dao.model.MessageInfo;
import io.github.pavansharma36.saas.utils.poll.AlwaysLogEmitter;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractMessageProcessor<T extends MessageDto> implements MessageProcessor {

  private final Class<T> clazz;
  private final MessageInfoDao messageInfoDao;
  private final LockService lockService;
  private final AlwaysLogEmitter alwaysLogEmitter = AlwaysLogEmitter.getInstance(log);

  protected abstract void processImpl(T messageDto);

  @Override
  public final void process(ProcessInstruction instruction, MessageDto messageDto)
      throws IOException {
    MessageInfo messageInfo = null;
    try {
      messageInfo = instruction.getMessageInfo();
      if (messageInfo != null) {
        messageInfo.setStatus(MessageStatus.PROCESSING);
        messageInfo.setStartedAt(new Date());
        messageInfoDao.update(messageInfo);
      }
      processImpl(clazz.cast(messageDto));
      complete(messageInfo, MessageStatus.SUCCESS, null);
    } catch (Exception e) {
      complete(messageInfo, MessageStatus.FAILED, e);
      throw e;
    } finally {
      instruction.getLockInfo().close();
    }
  }

  protected boolean isOwner(MessageInfo messageInfo) {
    return BrokerUtils.isOwner(messageInfo);
  }

  protected MessageInfo own(MessageInfo messageInfo) {
    log.info("Message is owned by {}, owning to current app {}", messageInfo.getOwner(),
        CoreConstants.APP_NAME);
    messageInfo.setOwner(CoreConstants.APP_NAME);
    messageInfo.setId(null);
    messageInfo.setLastPickedAt(new Date());
    messageInfo.setStatus(MessageStatus.DISPATCHED);
    if (messageInfo.getOrderKey() != null) {
      messageInfo.setOrderKey(CoreConstants.APP_NAME + messageInfo.getOrderKey());
    }
    return messageInfoDao.insert(messageInfo);
  }

  private void complete(MessageInfo messageInfo, MessageStatus status, Exception e) {
    BrokerUtils.completeMessage(messageInfoDao, messageInfo, status, e);
  }

  @Override
  public ProcessInstruction canProcess(Queue queue, MessageSerializablePayload payload)
      throws IOException {
    MessageInfo messageInfo = null;
    if (BrokerUtils.isQueueBlocked(queue, payload.getPriority(), alwaysLogEmitter)
        || BrokerUtils.isMessageTypeBlocked(payload.getMessageType(), log)) {
      return new ProcessInstruction(ProcessInstruction.Instruction.DELAY, messageInfo,
          CompositeLockInfo.build(lockService), payload);
    }
    List<Lock> requiredLocks = new LinkedList<>(requiredLocks(payload));
    boolean expired = payload.getMessageDto().getExpireAt() != null &&
        System.currentTimeMillis() > payload.getMessageDto().getExpireAt().getTime();
    if (payload.getMessageId() != null) {
      messageInfo = messageInfoDao.findByIdOrThrow(payload.getMessageId());

      if (!isOwner(messageInfo)) {
        messageInfo = own(messageInfo);
        payload.setMessageId(messageInfo.getId());
      } else {
        messageInfo.setLastPickedAt(new Date());
        messageInfoDao.update(messageInfo);
      }

      if (messageInfo.getStatus().isFinal()) {
        log.warn("Message status is already in final state ignoring message {}", messageInfo);
        return new ProcessInstruction(ProcessInstruction.Instruction.SKIP, messageInfo,
            CompositeLockInfo.build(lockService), payload);
      }

      if (expired) {
        complete(messageInfo, MessageStatus.EXPIRED, null);
        return new ProcessInstruction(ProcessInstruction.Instruction.SKIP, messageInfo,
            CompositeLockInfo.build(lockService), payload);
      }

      if (messageInfo.getStatus() != MessageStatus.DISPATCHED && !messageInfo.isIdempotent()) {
        log.warn("Message processing was started and its not idempotent {}", payload);
        throw new ServerRuntimeException("Message status is not dispatched and is not idempotent");
      }

      if (messageInfoDao.anyPreviousNonCompletedMessage(messageInfo)) {
        log.info("Previously non processed message found, delaying message {}", payload);
        return new ProcessInstruction(ProcessInstruction.Instruction.DELAY, messageInfo,
            CompositeLockInfo.build(lockService), payload);
      }

      if (messageInfo.isLockOnProcess()) {
        requiredLocks.add(
            DefaultLock.builder()
                .name(String.format("message_processor_%s", payload.getMessageId()))
                .acquireTimeout(Duration.ZERO)
                .build()
        );
      }
    }
    if (expired) {
      log.warn("Message already expired, skipping execution {}", payload);
      return new ProcessInstruction(ProcessInstruction.Instruction.SKIP, messageInfo,
          CompositeLockInfo.build(lockService), payload);
    }
    Optional<CompositeLockInfo> lock = lockService.acquireLock(requiredLocks);
    if (lock.isPresent()) {
      return new ProcessInstruction(ProcessInstruction.Instruction.PROCESS,
          messageInfo, lock.get(), payload);
    } else {
      return new ProcessInstruction(ProcessInstruction.Instruction.DELAY, messageInfo,
          CompositeLockInfo.build(lockService), payload);
    }
  }

  protected List<Lock> requiredLocks(MessageSerializablePayload payload) {
    return Collections.emptyList();
  }
}
