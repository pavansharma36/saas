package io.github.pavansharma36.saas.core.client.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.core.common.crypto.CryptUtil;
import io.github.pavansharma36.saas.utils.Constants;

public class B2BRequestInterceptor implements RequestInterceptor {

  private static final String B2B_SECRET = Config.get("b2b.secret");

  @Override
  public void apply(RequestTemplate requestTemplate) {
    requestTemplate.header(Constants.B2B_SECRET_HEADER, CryptUtil.encrypt(B2B_SECRET).value());
  }
}
