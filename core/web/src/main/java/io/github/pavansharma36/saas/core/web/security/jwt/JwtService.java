package io.github.pavansharma36.saas.core.web.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pavansharma36.core.common.crypto.CryptUtil;
import io.github.pavansharma36.core.common.crypto.KeyType;
import io.github.pavansharma36.core.common.utils.CoreConstants;
import io.github.pavansharma36.saas.utils.Utils;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Deserializer;
import io.jsonwebtoken.io.Serializer;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import io.jsonwebtoken.jackson.io.JacksonSerializer;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtService {

  private static final Key key = CryptUtil.getLatestKey(KeyType.JWT);
  private static final ObjectMapper mapper = JsonUtils.mapper();
  private static final Serializer<Map<String, ?>> serializer =
      new JacksonSerializer<>(mapper);

  private static final String CLAIM_PAYLOAD_KEY = "p";
  private static final Deserializer<Map<String, ?>> deserializer =
      new JacksonDeserializer<>(
          Collections.singletonMap(CLAIM_PAYLOAD_KEY, JwtDetails.JwtPayload.class));


  public String generate(String userId, String tenantId, UserDetails userAccount) {
    Date expireAt = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15L));
    JwtDetails.JwtPayload payload = new JwtDetails.JwtPayload(userId, tenantId,
        userAccount.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
    JwtDetails details = new JwtDetails(userAccount.getUsername(), expireAt, payload);
    return generate(details);
  }

  public String generate(JwtDetails details) {
    if (details.getExpireAt() == null) {
      details.setExpireAt(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15L)));
    }
    return Jwts.builder().setSubject(details.getUsername())
        .signWith(key)
        .setIssuedAt(new Date())
        .setId(Utils.randomRequestId())
        .setIssuer(CoreConstants.APP_NAME)
        .claim(CLAIM_PAYLOAD_KEY, details.getPayload())
        .serializeToJsonWith(serializer)
        .setExpiration(details.getExpireAt())
        .compact();
  }

  public JwtDetails parse(String jwt) {
    if (jwt == null) {
      return null;
    }
    try {
      Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key)
          .deserializeJsonWith(deserializer).build()
          .parseClaimsJws(jwt);
      JwtDetails.JwtPayload payload =
          jws.getBody().get(CLAIM_PAYLOAD_KEY, JwtDetails.JwtPayload.class);

      return new JwtDetails(jws.getBody().getSubject(), jws.getBody().getExpiration(), payload);
    } catch (SignatureException | ExpiredJwtException | MalformedJwtException e) {
      return null;
    }
  }


}
