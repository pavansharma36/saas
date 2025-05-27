package io.github.pavansharma36.saas.core.broker.common;

import io.github.pavansharma36.saas.core.broker.common.bean.MessageSerializablePayload;
import io.github.pavansharma36.saas.utils.Utils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class BrokerUtils {

  public byte[] serialize(MessageSerializablePayload payload) {
    return Utils.serialize(payload);
  }

  public MessageSerializablePayload deserialize(byte[] data) {
    return Utils.deserialize(data, MessageSerializablePayload.class);
  }

}
