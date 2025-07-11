package io.github.pavansharma36.core.common.context;

import io.github.pavansharma36.core.common.context.providers.RequestInfoContextProvider;
import io.github.pavansharma36.core.common.service.UserService;
import io.github.pavansharma36.saas.core.dto.common.UserDto;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserContext extends LazyThreadLocalContext<UserDto> {

  private final UserService userService;

  @Override
  public byte[] toByteArray() {
    return get().map(t -> t.getId().getBytes(StandardCharsets.UTF_8)).orElseGet(() -> new byte[0]);
  }

  @Override
  public void setFromByteArray(byte[] value) {
    String id = new String(value, StandardCharsets.UTF_8);
    set(userService.getUserById(id));
  }

  @Override
  protected Optional<UserDto> initializeContext() {
    return RequestInfoContextProvider.getUserId().map(userId -> {
      log.info("initializing user context: {}", userId);
      return userService.getCurrentUser();
    });
  }
}
