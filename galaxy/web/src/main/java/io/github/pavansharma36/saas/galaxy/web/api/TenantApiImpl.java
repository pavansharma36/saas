package io.github.pavansharma36.saas.galaxy.web.api;

import io.github.pavansharma36.saas.core.broker.common.api.MessagePriority;
import io.github.pavansharma36.saas.core.broker.common.bean.Message;
import io.github.pavansharma36.saas.core.broker.common.bean.MessageDto;
import io.github.pavansharma36.saas.core.broker.producer.MessageSender;
import io.github.pavansharma36.saas.core.dto.common.TenantDto;
import io.github.pavansharma36.saas.core.dto.response.ResponseObject;
import io.github.pavansharma36.saas.galaxy.api.TenantApi;
import io.github.pavansharma36.saas.galaxy.common.broker.GalaxyMessageType;
import io.github.pavansharma36.saas.galaxy.common.broker.GalaxyQueue;
import io.github.pavansharma36.saas.galaxy.common.service.GalaxyTenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TenantApiImpl implements TenantApi {

  private final GalaxyTenantService tenantService;
  private final MessageSender messageSender;

  @Override
  public ResponseObject<TenantDto> getTenant(String id) {
    return ResponseObject.response(tenantService.getTenantById(id));
  }

  @Override
  public ResponseObject<String> createTenant(TenantDto tenantDto) {
    return ResponseObject.response(tenantService.createTenant(tenantDto));
  }

  @Override
  public ResponseObject<Object> updateTenant(String id, TenantDto tenantDto) {
    tenantService.updateTenantById(id, tenantDto);
    return ResponseObject.empty();
  }

  @PostMapping("dispatch")
  public ResponseObject<Object> dispatch() {
    Message m = Message.builder()
        .priority(MessagePriority.NORMAL)
        .messageType(GalaxyMessageType.TEST)
        .messageDto(new MessageDto())
        .trackWithDatabase(true)
        .lockOnProcess(false)
        .build();
    messageSender.send(GalaxyQueue.GALAXY, m);
    return ResponseObject.empty();
  }
}
