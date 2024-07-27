package io.github.pavansharma36.saas.core.dao.mybatis.config;

import java.util.Objects;
import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

@ComponentScan("io.github.pavansharma36.saas.core.dao.mybatis")
public abstract class MyBatisConfig {

  protected abstract DataSource dataSourceInternal();

  @Bean
  public DataSource dataSource() {
    return dataSourceInternal();
  }

  @Bean
  public SqlSessionFactoryBean sqlSessionFactory() {
    SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
    sqlSessionFactory.setDataSource(dataSource());
    return sqlSessionFactory;
  }

  @Bean
  public SqlSessionTemplate sqlSessionTemplate() throws Exception {
    return new SqlSessionTemplate(Objects.requireNonNull(sqlSessionFactory().getObject()));
  }

  @Bean
  public TransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }

}
