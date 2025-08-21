package io.github.pavansharma36.saas.core.common.validation;

import io.github.pavansharma36.saas.core.common.validation.message.AppMessageSource;
import io.github.pavansharma36.saas.utils.ex.code.ErrorCode;
import jakarta.validation.ValidatorFactory;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringSubstitutor;
import org.apache.commons.text.lookup.StringLookup;
import org.apache.commons.text.lookup.StringLookupFactory;
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

  private static String getMessage(String code) {
    return MESSAGE_SOURCE.getMessage(code, null, code, LocaleContextHolder.getLocale());
  }

  private static String getMessageOrNull(String code) {
    return MESSAGE_SOURCE.getMessage(code, null, null, LocaleContextHolder.getLocale());
  }

  public static String formatMessage(String code, Map<String, Object> params) {
    StringLookup defaultLookup = new MapAndMessageStringLookup(params);
    StringSubstitutor ss =
        new StringSubstitutor(StringLookupFactory.INSTANCE.interpolatorStringLookup(defaultLookup),
            "{", "}", '$');
    ss.setEnableSubstitutionInVariables(true);
    return ss.replace(getMessage(code));
  }

  public static void registerAppMessages(String appName) {
    MESSAGE_SOURCE.registerApp(appName);
  }

  public static void registerErrorCodeMessages(String appName, Class<? extends ErrorCode> clazz) {
    MESSAGE_SOURCE.registerErrorCodeMessages(appName, clazz);
  }

  @RequiredArgsConstructor
  private static class MapAndMessageStringLookup implements StringLookup {

    private final Map<String, Object> paramMap;

    @Override
    public String lookup(String s) {
      if (paramMap.containsKey(s)) {
        return String.valueOf(paramMap.get(s));
      }
      return getMessageOrNull(s);
    }
  }


}
