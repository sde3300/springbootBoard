package btc.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/applocation.properties")
public class DatabaseConfiguration {

   @Autowired
   private ApplicationContext appContext;
   
    @Bean
      @ConfigurationProperties(prefix="spring.datasource.hikari")
      public HikariConfig hikariConfig() throws Exception{
         return new HikariConfig();
      }
      
      @Bean
      public DataSource dataSource() throws Exception {
         DataSource ds = new HikariDataSource(hikariConfig());

         return ds;
      }
      
      @Bean
      public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
         SqlSessionFactoryBean sqlSFB = new SqlSessionFactoryBean();
         sqlSFB.setDataSource(ds);
         sqlSFB.setMapperLocations(appContext.getResources("classpath:/sql/**/sql-*.xml"));
         sqlSFB.setConfiguration(mybatisConfig());
         
         return sqlSFB.getObject();
      }
      
      @Bean
      public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSF) throws Exception {
         return new SqlSessionTemplate(sqlSF);
      }
      
      @Bean
      @ConfigurationProperties(prefix="mybatis.configuration")
      public org.apache.ibatis.session.Configuration mybatisConfig() throws Exception {
         return new org.apache.ibatis.session.Configuration();
      }
      
      @Bean
      @ConfigurationProperties(prefix="spring.jpa")
      public Properties hiberateConfig() {
    	  return new Properties();
      }
}