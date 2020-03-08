package ru.monsterdev.domofon.config;

import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.monsterdev.domofon.PostgresDatabase;

@Slf4j
@Configuration
@ComponentScan(basePackages = "ru.monsterdev.domofon.domain")
@ComponentScan(basePackages = "ru.monsterdev.domofon.services")
@EnableJpaRepositories(basePackages = "ru.monsterdev.domofon.repositories")
@EnableTransactionManagement
public class DomofonContextMemoryDb {


  @Value("${spring.jpa.hibernate.dialect}")
  private String dialect;
  @Value("${spring.jpa.hibernate.format-sql}")
  private Boolean formatSql;
  @Value("${spring.jpa.show-sql}")
  private Boolean showSql;

  @Value("${spring.flyway.locations}")
  String[] dbMigrationScriptsLocation;

  @Bean
  public DataSource dataSource() throws IOException {
    if (PostgresDatabase.getInstance() == null) {
      PostgresDatabase.start();
      log.info("Embedded Postgresql database was started on port {}", PostgresDatabase.getInstance().getPort());
    }
    return PostgresDatabase.getInstance().getPostgresDatabase();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws IOException {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource());
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    entityManagerFactoryBean.setPackagesToScan("ru.monsterdev.domofon.domain");
    Properties jpaProperties = new Properties();
    jpaProperties.put("hibernate.dialect", dialect);
    jpaProperties.put("hibernate.format_sql", formatSql);
    jpaProperties.put("hibernate.show_sql", showSql);
    entityManagerFactoryBean.setJpaProperties(jpaProperties);
    migrate();
    return entityManagerFactoryBean;
  }

  @Bean
  public JpaTransactionManager transactionManager() throws IOException {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return transactionManager;
  }

  private void migrate() throws IOException {
    Flyway
        .configure()
        .dataSource(dataSource())
        .locations(dbMigrationScriptsLocation)
        .load()
        .migrate();
  }
}
