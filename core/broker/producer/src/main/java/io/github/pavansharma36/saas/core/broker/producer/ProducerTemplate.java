package io.github.pavansharma36.saas.core.broker.producer;

public interface ProducerTemplate {
  void produce(String queueName, byte[] data);

  String type();
}
