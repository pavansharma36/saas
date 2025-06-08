package io.github.pavansharma36.core.common.utils;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.core.common.config.provider.ConfigProviders;
import io.github.pavansharma36.core.common.config.provider.PropertiesFileConfigProvider;
import io.github.pavansharma36.core.common.context.providers.RequestInfoContextProvider;
import io.github.pavansharma36.core.common.validation.AppValidatorFactory;
import io.github.pavansharma36.core.common.validation.BadRequestException;
import io.github.pavansharma36.core.common.validation.CoreErrorCode;
import io.github.pavansharma36.saas.utils.Enums;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class CoreUtils {

  private static final String AUTH_HEADER_TYPE_SEPARATOR = " ";

  public static void initApp(String appName, Enums.AppType appType) {
    System.setProperty("app.name", appName);
    System.setProperty("app.type", appType.getName());
    ConfigProviders.registerConfigProvider(
        new PropertiesFileConfigProvider(
            String.format("conf/%s.properties", appType.name().toLowerCase()), 20000));
    ConfigProviders.registerConfigProvider(
        new PropertiesFileConfigProvider(String.format("conf/app/%s.properties", appName),
            10000));
    ConfigProviders.registerConfigProvider(
        new PropertiesFileConfigProvider("conf/common.properties", Integer.MAX_VALUE - 20));

    ConfigProviders.registerConfigProvider(
        new PropertiesFileConfigProvider(
            String.format("conf/env/%s.properties", getEnv()), 8000));

    AppValidatorFactory.registerAppMessages("core");
    AppValidatorFactory.registerErrorCodeMessages("core", CoreErrorCode.class);
    AppValidatorFactory.registerAppMessages(appName);
  }

  public static String getEnv() {
    return Config.get("env", "dev");
  }

  public static String getUserId() {
    return RequestInfoContextProvider.getInstance().getOrThrow().getUserId();
  }

  public static String getTenantId() {
    return RequestInfoContextProvider.getInstance().getOrThrow().getTenantId();
  }

  public static String authorizationHeader(String type, String value) {
    return type + AUTH_HEADER_TYPE_SEPARATOR + value;
  }

  public static Pair<String, String> parseAuthorizationHeader(String value) {
    String[] tokens = value.split(AUTH_HEADER_TYPE_SEPARATOR);
    if (tokens.length != 2) {
      throw new BadRequestException(CoreErrorCode.INVALID_AUTHORIZATION_HEADER);
    }
    return Pair.of(tokens[0], tokens[1]);
  }

}
