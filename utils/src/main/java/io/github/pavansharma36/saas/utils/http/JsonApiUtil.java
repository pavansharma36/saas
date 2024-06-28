package io.github.pavansharma36.saas.utils.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpEntity;

@Slf4j
@RequiredArgsConstructor
public class JsonApiUtil {

  private final HttpClient client = HttpClientBuilder.create().build();
  private final ObjectMapper mapper = JsonUtils.mapper();

  public <T> T get(String url, Class<T> clazz) {
    HttpGet httpGet = new HttpGet(url);
    try (CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httpGet)) {
      HttpEntity entity = response.getEntity();
      try (InputStream in = entity.getContent()) {
        return mapper.readValue(in, clazz);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

}
