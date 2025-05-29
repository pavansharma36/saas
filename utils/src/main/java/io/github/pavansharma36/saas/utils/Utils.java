package io.github.pavansharma36.saas.utils;

import io.github.pavansharma36.saas.utils.ex.ServerRuntimeException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.Callable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class Utils {

  public static <R> R executeQuietly(Callable<R> func, Logger logger) {
    try {
      return func.call();
    } catch (Exception e) {
      logger.error("Error : {}", e.getMessage(), e);
      return null;
    }
  }

  public static String randomRequestId() {
    return RandomStringUtils.randomAlphanumeric(12);
  }

  public static void sleep(long millis) throws InterruptedException {
    Thread.sleep(millis);
  }

  public static void sleepQuietly(long millis) {
    try {
      sleep(millis);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new ServerRuntimeException(e) {
      };
    }
  }

  public static void sleepSeconds(int seconds) throws InterruptedException {
    Thread.sleep(seconds * 1000L);
  }

  public static void sleepSecondsQuietly(int seconds) {
    try {
      sleepSeconds(seconds);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new ServerRuntimeException(e);
    }
  }

  public static <T> T deserialize(byte[] object, Class<T> clazz) {
    try (ByteArrayInputStream in = new ByteArrayInputStream(object);
         ObjectInputStream inObject = new ObjectInputStream(in)) {
      return clazz.cast(inObject.readObject());
    } catch (Exception e) {
      throw new ServerRuntimeException(e);
    }
  }

  public static byte[] serialize(Serializable object) {
    try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
         ObjectOutputStream out = new ObjectOutputStream(byteOut)) {
      out.writeObject(object);
      out.flush();
      return byteOut.toByteArray();
    } catch (IOException e) {
      throw new ServerRuntimeException(e);
    }
  }
}
