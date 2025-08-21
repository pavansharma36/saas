package io.github.pavansharma36.saas.core.common.context;

import io.github.pavansharma36.saas.core.common.context.providers.RequestInfoContextProvider;
import io.github.pavansharma36.saas.core.common.service.TenantService;
import io.github.pavansharma36.saas.core.dto.common.TenantDto;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TenantContext extends LazyThreadLocalContext<TenantDto> {

  private final TenantService tenantService;

  public TenantContext(TenantService tenantService) {
    super();
    this.tenantService = tenantService;
  }

  /**
   * for tenant context serialize only id.
   *
   * @return - tenant id.
   */
  @Override
  public byte[] toByteArray() {
    return get().map(t -> t.getId().getBytes(StandardCharsets.UTF_8)).orElseGet(() -> new byte[0]);
  }

  /**
   * create tenant context from id.
   *
   * @param value - byte  array returned from toByteArray.
   */
  @Override
  public void setFromByteArray(byte[] value) {
    String id = new String(value, StandardCharsets.UTF_8);
    set(tenantService.getTenantById(id));
  }

  @Override
  protected Optional<TenantDto> initializeContext() {
    return RequestInfoContextProvider.getTenantId().map(tenantId -> {
      log.info("initializing tenant context: {}", tenantId);
      return tenantService.getTenantById(tenantId);
    });
  }
}
