package io.github.pavansharma36.core.common.validation.message;

import io.github.pavansharma36.core.common.validation.ServerRuntimeException;
import io.github.pavansharma36.saas.utils.ex.code.ErrorCode;
import io.github.pavansharma36.saas.utils.resource.ResourceUtils;
import java.io.File;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Slf4j
public class AppMessageSource extends AbstractMessageSource implements MessageSource {

  private static final Set<String> REGISTERED_MESSAGES = new HashSet<>();

  public AppMessageSource(String staticMessageBaseName) {
    ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
    ms.setBasename("classpath:messages");
    ms.setDefaultEncoding("UTF-8");
    ms.setDefaultLocale(new Locale.Builder().setLanguage("en").setRegion("US").build());
    setParentMessageSource(ms);
  }

  @Override
  protected MessageFormat resolveCode(String code, Locale locale) {
    return null;
  }

  @Override
  protected String resolveCodeWithoutArguments(String code, Locale locale) {
    return null;
  }

  public void registerApp(String appName) {
    if (REGISTERED_MESSAGES.add(appName)) {
      // locate messages for app.
      String locationToCheck = String.format("conf/message/%s", appName);
      Optional<File> file = ResourceUtils.findFile(locationToCheck, 5);
      if (file.isPresent()) {
        String baseName = String.format("file:%s/messages", file.get().getAbsolutePath());
        log.info("Registering message from {}", baseName);
        ReloadableResourceBundleMessageSource ms =
            (ReloadableResourceBundleMessageSource) getParentMessageSource();
        ms.addBasenames(baseName);
      } else {
        log.warn("No file found at {}", locationToCheck);
      }
    }
  }

  public void registerErrorCodeMessages(String appName, Class<? extends ErrorCode> clazz) {
    String baseName = String.format("%s/%s", appName, clazz.getSimpleName());
    if (REGISTERED_MESSAGES.add(baseName)) {
      String locationToCheck = String.format("conf/message/%s", appName);
      Optional<File> file = ResourceUtils.findFile(locationToCheck, 5);
      if (file.isPresent()) {
        baseName =
            String.format("file:%s/%s_messages", file.get().getAbsolutePath(),
                clazz.getSimpleName());
        log.info("Registering message from {} for {}", baseName, clazz);
        ReloadableResourceBundleMessageSource ms =
            (ReloadableResourceBundleMessageSource) getParentMessageSource();
        ms.addBasenames(baseName);
      } else {
        throw new ServerRuntimeException(
            String.format("No message dir found for %s: %s", appName, clazz.getSimpleName()));
      }
    }
  }
}
