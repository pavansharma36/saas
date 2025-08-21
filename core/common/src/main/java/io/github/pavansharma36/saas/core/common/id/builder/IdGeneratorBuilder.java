package io.github.pavansharma36.saas.core.common.id.builder;

import io.github.pavansharma36.saas.core.common.id.IdGenerator;
import io.github.pavansharma36.saas.core.common.id.impl.AdvancedIdGenerator;
import io.github.pavansharma36.saas.core.common.id.impl.SimpleIdGenerator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGeneratorBuilder {

  public static SimpleIdGeneratorBuilder simple() {
    return new SimpleIdGeneratorBuilder();
  }

  public static AdvancedIdGeneratorBuilder advanced() {
    return new AdvancedIdGeneratorBuilder();
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class SimpleIdGeneratorBuilder {
    public SimpleIdGenerator build(int prefixLength, int randomLength) {
      return new SimpleIdGenerator(randomLength, prefixLength);
    }
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class AdvancedIdGeneratorBuilder {
    private final List<IdGenerator.Token<?>> tokens = new LinkedList<>();

    public AdvancedIdGeneratorBuilder appendShortToken(String name, Supplier<Short> supplier) {
      tokens.add(new IdGenerator.ShortToken(name, forClazz -> supplier.get()));
      return this;
    }

    public AdvancedIdGeneratorBuilder appendIntToken(String name, IntSupplier supplier) {
      tokens.add(new IdGenerator.IntToken(name, forClazz -> supplier.getAsInt()));
      return this;
    }

    public AdvancedIdGeneratorBuilder appendSchemaIdToken(int length) {
      tokens.add(new IdGenerator.SchemaIdToken(length));
      return this;
    }

    public AdvancedIdGeneratorBuilder appendTimestamp() {
      tokens.add(new IdGenerator.TimestampToken());
      return this;
    }

    public AdvancedIdGeneratorBuilder appendRandom(int length) {
      tokens.add(new IdGenerator.RandomToken(length));
      return this;
    }

    public AdvancedIdGenerator build() {
      tokens.stream().collect(Collectors.groupingBy(IdGenerator.Token::getName)).entrySet()
          .stream().filter(c -> c.getValue().size() > 1).findAny().ifPresent(t -> {
            throw new RuntimeException("Duplicate token : " + t.getKey());
          });
      return new AdvancedIdGenerator(tokens);
    }
  }

}
