package io.github.pavansharma36.saas.search.web.api;

import io.github.pavansharma36.saas.core.dto.response.ListResponseObject;
import io.github.pavansharma36.saas.search.api.GenericSearchApi;
import io.github.pavansharma36.saas.search.common.service.GenericSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GenericSearchApiImpl implements GenericSearchApi {

  private final GenericSearchService genericSearchService;

  @GetMapping
  public ListResponseObject<?> search(@PathVariable("entity") String entity,
                                      @RequestParam("q") String query,
                                      @RequestParam(name = "offset", defaultValue = "0") int offset,
                                      @RequestParam(name = "limit", defaultValue = "20")
                                      int limit) {
    return ListResponseObject.success(genericSearchService.search(entity, query, offset, limit));
  }

}
