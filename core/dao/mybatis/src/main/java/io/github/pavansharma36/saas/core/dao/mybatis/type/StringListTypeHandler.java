package io.github.pavansharma36.saas.core.dao.mybatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class StringListTypeHandler extends BaseTypeHandler<List<String>> {
  private static final String DELIMITER = ",";

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter,
                                  JdbcType jdbcType) throws SQLException {
    ps.setString(i, String.join(DELIMITER, parameter));
  }

  @Override
  public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
    String value = rs.getString(columnName);
    return parseStringToList(value);
  }

  @Override
  public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    String value = rs.getString(columnIndex);
    return parseStringToList(value);
  }

  @Override
  public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    String value = cs.getString(columnIndex);
    return parseStringToList(value);
  }


  private List<String> parseStringToList(String value) {
    if (value == null || value.isEmpty()) {
      return null;
    }
    return Arrays.asList(value.split(DELIMITER));
  }
}
