package io.github.pavansharma36.saas.core.dto.common;

import io.github.pavansharma36.saas.core.dto.Dto;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserAccountDto extends Dto {

  public static final String FIELD_USERNAME = "username";

  @NotBlank
  @Length(min = 2, max = 64)
  private String username;

  private List<String> authorities;

}
