package io.github.pavansharma36.saas.auth.common.dao.mongo.model;

import io.github.pavansharma36.saas.core.dao.mongodb.model.MongoModel;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NavItemConfig extends MongoModel {
  // unique identifier for this nav item.
  private String name;

  // description
  private String description;

  // UI uri
  private String uri;

  // Label of link
  private String label;

  // icon to show for nav item
  private String icon;

  // order of this nav item, items which have parent will follow their own order
  private int order;

  // parent nav item. usually name of nav item which is wrapper.
  private String parent;

  // only holds child nav item. doesn't have uri,
  private boolean wrapper;

  // weather this nav item is required by default.
  private boolean required;

  // scopes supported for this nav item.
  private List<Scope> scopes;

  @Data
  public static class Scope {
    // name of resource
    private String resource;
    // supported actions.
    private List<Action> actions;
  }

  @Data
  public static class Action {
    // name of action.
    private String action;
    // weather its required for nav item to work.
    private boolean required;
    // if this action requires some other scope. eg EDIT-profile requires VIEW-profile.
    private List<Scope> requiredScopes;
  }
}
