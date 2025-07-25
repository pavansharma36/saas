package io.github.pavansharma36.core.common.context.providers;

import io.github.pavansharma36.core.common.context.UserContext;
import io.github.pavansharma36.core.common.validation.ServerRuntimeException;

public class UserContextProvider {
  private static UserContext userContext;

  public static void register(UserContext userContext) {
    if (UserContextProvider.userContext != null) {
      throw new ServerRuntimeException("User context already registered");
    }
    UserContextProvider.userContext = userContext;
  }

  public static UserContext getInstance() {
    if (userContext == null) {
      throw new ServerRuntimeException("User context not initialized");
    }
    return userContext;
  }

  public static boolean isInitialized() {
    return userContext != null;
  }
}
