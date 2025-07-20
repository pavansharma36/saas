package io.github.pavansharma36.saas.search.api;

import io.github.pavansharma36.saas.core.dto.response.ListResponseObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("{entity}")
public interface GenericSearchApi {
  ListResponseObject<?> search(@PathVariable("entity") String entity,
                               @RequestParam("q") String query,
                               @RequestParam(name = "offset", defaultValue = "0") int offset,
                               @RequestParam(name = "limit", defaultValue = "20") int limit);
}
