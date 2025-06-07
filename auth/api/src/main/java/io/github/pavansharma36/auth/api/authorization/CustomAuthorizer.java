package io.github.pavansharma36.auth.api.authorization;

public interface CustomAuthorizer {
  String resource();

  boolean hasAccess(String action);

  boolean hasAccess(String entityId, String action);
}
