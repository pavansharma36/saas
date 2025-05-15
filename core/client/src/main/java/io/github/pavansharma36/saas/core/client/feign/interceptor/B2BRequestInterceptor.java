package io.github.pavansharma36.saas.core.client.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.github.pavansharma36.core.common.config.Config;
import io.github.pavansharma36.core.common.context.RequestInfo;
import io.github.pavansharma36.core.common.context.providers.RequestInfoContextProvider;
import io.github.pavansharma36.core.common.crypto.CryptUtil;
import io.github.pavansharma36.core.common.utils.CoreConstants;
import io.github.pavansharma36.saas.utils.Constants;
import io.github.pavansharma36.saas.utils.Utils;
import org.slf4j.MDC;

/**
 * B2B interceptor which adds
 */
public class B2BRequestInterceptor implements RequestInterceptor {

  private static final String B2B_SECRET = Config.get("b2b.secret");

  @Override
  public void apply(RequestTemplate requestTemplate) {
    requestTemplate.header(Constants.Header.B2B_SECRET_HEADER,
        CryptUtil.encrypt(B2B_SECRET).value());

    requestTemplate.header(Constants.Header.REQUEST_ID_HEADER,
        MDC.get(Constants.REQUEST_ID_MDC_KEY));

    requestTemplate.header(Constants.Header.APP_NAME_HEADER,
        CoreConstants.APP_NAME);

    requestTemplate.header(Constants.Header.APP_TYPE_HEADER,
        CoreConstants.APP_TYPE.getName());

    requestTemplate.header(Constants.Header.ATTEMPT_ID_HEADER,
        Utils.randomRequestId());

    RequestInfoContextProvider.getInstance().get().map(RequestInfo::getUserId)
        .ifPresent(userId -> requestTemplate.header(Constants.Header.USER_ID_HEADER, userId));

    RequestInfoContextProvider.getInstance().get().map(RequestInfo::getTenantId)
        .ifPresent(
            tenantId -> requestTemplate.header(Constants.Header.TENANT_ID_HEADER, tenantId));

  }
}
