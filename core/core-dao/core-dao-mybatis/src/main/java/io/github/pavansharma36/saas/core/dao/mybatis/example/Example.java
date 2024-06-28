package io.github.pavansharma36.saas.core.dao.mybatis.example;

public class Example {

  protected String orderByClause;

  protected boolean distinct;

  public String getOrderByClause() {
    return orderByClause;
  }

  public void setOrderByClause(final String orderByClause) {
    this.orderByClause = orderByClause;
  }

  public boolean isDistinct() {
    return distinct;
  }

  public void setDistinct(final boolean distinct) {
    this.distinct = distinct;
  }

}
