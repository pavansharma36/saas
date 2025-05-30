package io.github.pavansharma36.core.common.utils;

import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.core.common.config.provider.ConfigProviders;
import io.github.pavansharma36.core.common.config.provider.PropertiesFileConfigProvider;
import io.github.pavansharma36.core.common.context.providers.RequestInfoContextProvider;
import io.github.pavansharma36.core.common.validation.AppValidatorFactory;
import io.github.pavansharma36.core.common.validation.CoreErrorCode;
import io.github.pavansharma36.saas.utils.Enums;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class CoreUtils {

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

}
