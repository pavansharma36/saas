package io.github.pavansharma36.saas.core.server.security.jwt;

import io.github.pavansharma36.core.common.crypto.CryptUtil;
import io.github.pavansharma36.core.common.crypto.KeyType;
import io.github.pavansharma36.saas.utils.json.JsonUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
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
  private static final Serializer<Map<String, ?>> serializer =
      new JacksonSerializer<>(JsonUtils.mapper());

  private static final String CLAIM_PAYLOAD_KEY = "p";
  private static final Deserializer<Map<String, ?>> deserializer = new JacksonDeserializer<>(
      Collections.singletonMap(CLAIM_PAYLOAD_KEY, JwtPayload.class));

//  static {
//    byte[] keyValue =
//        new byte[] {34, 60, 74, -58, 79, 115, 120, 66, -46, 40, 93, 20, 121, -63, 69, -112, -83, 81,
//            -11, 52, 18, -111, 94, -114, -60, 115, 12, 36, -108, 100, -37, 115};
//    key = Keys.hmacShaKeyFor(keyValue);
//  }

  public String generate(UserDetails userAccount) {
    return Jwts.builder().setSubject(userAccount.getUsername())
        .signWith(key).claim(CLAIM_PAYLOAD_KEY, new JwtPayload(userAccount.getUsername(),
            userAccount.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(),
            null))
        .serializeToJsonWith(serializer)
        .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15L)))
        .compact();
  }

  public JwtPayload parse(String jwt) {
    if (jwt == null) {
      return null;
    }
    try {
      Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key)
          .deserializeJsonWith(deserializer).build()
          .parseClaimsJws(jwt);
      JwtPayload payload = jws.getBody().get(CLAIM_PAYLOAD_KEY, JwtPayload.class);
      payload.setExpireAt(jws.getBody().getExpiration());
      return payload;
    } catch (SignatureException | ExpiredJwtException e) {
      return null;
    }
  }


}
