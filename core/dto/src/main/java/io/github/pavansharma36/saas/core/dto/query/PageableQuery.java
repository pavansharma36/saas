package io.github.pavansharma36.saas.core.dto.query;

import io.github.pavansharma36.saas.utils.collections.CollectionUtils;
import io.github.pavansharma36.saas.utils.ex.AppRuntimeException;
import jakarta.validation.constraints.Max;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageableQuery implements Query {

  public static final PageableQuery DEFAULT = new PageableQuery(0, 20, null);

  private int page;

  @Max(1000)
  private int size;

  private List<String> sort;

  public List<Sort> parseSort() {
    if (CollectionUtils.isEmpty(sort)) {
      return Collections.emptyList();
    }
    if (sort.size() == 2 && ("DESC".equals(sort.get(1)) || "ASC".equals(sort.get(1)))) {
      return Collections.singletonList(new Sort(sort.get(0), SortDirection.valueOf(sort.get(1))));
    }
    List<Sort> sorts = new ArrayList<>(sort.size());
    for (String s : sort) {
      String[] tokens = s.split(",");
      if (tokens.length == 1) {
        sorts.add(new Sort(tokens[0], SortDirection.ASC));
      } else if (tokens.length == 2) {
        SortDirection direction = SortDirection.valueOf(tokens[1]);
        sorts.add(new Sort(tokens[0], direction));
      } else {
        throw new AppRuntimeException("") {
        };
      }
    }
    return sorts;
  }

  @Override
  public Map<String, Object> queryMap() {
    Map<String, Object> m = new HashMap<>(3);
    m.put("page", page);
    m.put("size", size);
    if (CollectionUtils.isNotEmpty(sort)) {
      m.put("sort", sort);
    }
    return m;
  }

  public enum SortDirection {
    ASC, DESC
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Sort {
    private String field;
    private SortDirection direction;
  }
}
