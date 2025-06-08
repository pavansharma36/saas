package io.github.pavansharma36.saas.utils.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class CollectionUtils {

  public static boolean isEmpty(Collection<?> c) {
    return c == null || c.isEmpty();
  }

  public static boolean isNotEmpty(Collection<?> c) {
    return c != null && !c.isEmpty();
  }

  public static boolean isEmpty(Map<?, ?> map) {
    return map == null || map.isEmpty();
  }

  public static <T> Collection<T> nullSafe(Collection<T> c) {
    return nullSafe(c, false);
  }

  public static <T> Collection<T> nullSafe(Collection<T> c, boolean mutable) {
    if (c == null) {
      return mutable ? new LinkedList<>() : Collections.emptyList();
    }
    return c;
  }

  public static <T> List<T> nullSafeList(List<T> list) {
    return nullSafeList(list, false);
  }

  public static <T> List<T> nullSafeList(List<T> list, boolean mutable) {
    if (list == null) {
      return mutable ? new LinkedList<>() : Collections.emptyList();
    }
    return list;
  }

  public static <T> Set<T> nullSafeSet(Set<T> set) {
    return nullSafeSet(set, false);
  }

  public static <T> Set<T> nullSafeSet(Set<T> set, boolean mutable) {
    if (set == null) {
      return mutable ? new HashSet<>() : Collections.emptySet();
    }
    return set;
  }

  public static <K, V> Map<K, V> nullSafeMap(Map<K, V> map) {
    return nullSafeMap(map, false);
  }

  public static <K, V> Map<K, V> nullSafeMap(Map<K, V> map, boolean mutable) {
    if (map == null) {
      return mutable ? new HashMap<>() : Collections.emptyMap();
    }
    return map;
  }

}
