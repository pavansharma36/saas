package io.github.pavansharma36.core.common.validation;

import io.github.pavansharma36.core.common.validation.message.AppMessageSource;
import io.github.pavansharma36.saas.utils.ex.code.ErrorCode;
import jakarta.validation.ValidatorFactory;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class AppValidatorFactory {

  private static final AppMessageSource MESSAGE_SOURCE = new AppMessageSource("classpath:message");
  private static final ValidatorFactory VALIDATOR_FACTORY;

  static {
    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
    bean.setProviderClass(HibernateValidator.class);
    bean.setValidationMessageSource(MESSAGE_SOURCE);
    bean.afterPropertiesSet();

    VALIDATOR_FACTORY = bean;
  }

  public static MessageSource getMessageSource() {
    return MESSAGE_SOURCE;
  }

  public static ValidatorFactory getInstance() {
    return VALIDATOR_FACTORY;
  }

  public static String formatMessage(String code, Map<String, Object> params) {
    return StringSubstitutor.replace(
        MESSAGE_SOURCE.getMessage(code, null, LocaleContextHolder.getLocale()), params, "{", "}");
  }

  public static void registerAppMessages(String appName) {
    MESSAGE_SOURCE.registerApp(appName);
  }

  public static void registerErrorCodeMessages(String appName, Class<? extends ErrorCode> clazz) {
    MESSAGE_SOURCE.registerErrorCodeMessages(appName, clazz);
  }


}
