package io.github.pavansharma36.saas.core.client.spring;

import io.github.pavansharma36.saas.utils.json.JsonUtils;
import java.util.List;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class RestTemplateFactory {

  @Getter
  private static final RestTemplate restTemplate;
  @Getter
  private static final List<HttpMessageConverter<?>> httpMessageConverters;

  static {
    RestTemplate temp = new RestTemplate();

    List<HttpMessageConverter<?>> converters = Stream.concat(
        Stream.of(new MappingJackson2HttpMessageConverter(JsonUtils.mapper())),
        temp.getMessageConverters()
            .stream().filter(c -> !(c instanceof MappingJackson2HttpMessageConverter))).toList();
    httpMessageConverters = converters;
    restTemplate = new RestTemplate(converters);
  }

}
