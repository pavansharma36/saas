package io.github.pavansharma36.saas.core.broker.consumer.api.poller;

import lombok.Getter;

@Getter
public class PollResponse {
  private final byte[] body;

  public PollResponse(byte[] body) {
    this.body = body;
  }
}
