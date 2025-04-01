package io.github.pavansharma36.core.common.validation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;

public class AppValidatorFactory {

  private static final ValidatorFactory FACTORY;

  static {
    FACTORY = Validation.byProvider(HibernateValidator.class)
        .configure().buildValidatorFactory();
  }

  public static Validator getInstance() {
    return FACTORY.getValidator();
  }
}
