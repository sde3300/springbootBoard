package com.btc.board1.configuration;

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

// Configuration 어노테이션은 자바 기반의 설정 파일임을 나타내는 어노테이션임
@Configuration
// PropertySource 어노테이션은 설정 파일을 지정하는 어노테이션
@PropertySource("classpath:/application.properties") 
public class DatabaseConfiguration {

	@Autowired
	private ApplicationContext appContext;
	
//	MySql 서버 설정, HikariCP 사용
	@Bean
//	@ConfigurationProperties 어노테이션은 prefix로 지정한 문자열로 시작하는 설정을 가져온다는 의미의 어노테이션
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	public DataSource dataSource() throws Exception {
//		앞에서 만든 히카리CP의 설정 파일을 이용해서 데이터베이스와 연결하는 데이터 소스 생성
		DataSource dataSource = new HikariDataSource(hikariConfig());
//		정상적으로 데이터 소스가 생성되었는지 확인
		System.out.println(dataSource.toString());
		
		return dataSource;
	}
	
	
//	MyBatis 사용 설정
//	ORM : Java 개체를 데이터베이스 테이블에 매핑하거나 그 반대로 매핑하는 것을 ORM(Object-Relational Mapping)이라고 함
//	MyBatis : 여러 ORM 중에서 우리나라에서 특히 많이 사용되고 있는 ORM
	// xml 파일에 SQL 쿼리를 미리 등록시켜 놓고 자바 클래스에서 호출하여 사용함
//	SqlSessionFactory : 스프링에서 사용하는 데이터 베이스와의 연결 및 SQL의 실행에 대한 모든 것을 가지고 있는 클래스
//	마이바티스에서는 SqlSessionFactory 를 만들기 위해서 SqlSessionFactoryBean 클래스를 사용함
	@Bean 
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//		MyBatis에서 SqlSessionFactory를 생성하기 위해서 SqlSessionFactoryBean을 사용
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		위에서 HikariCP를 사용하여 생성한 dataSource를 sqlSessionFactoryBean에 설정함
		sqlSessionFactoryBean.setDataSource(dataSource);
//		MyBatis 매퍼 파일의 위치를 설정함
//		** : 어떠한 형식도 상관없음
//		sql-*.xml : sql- 로 시작하고, 중간의 문자는 임임의 문자를 사용하며, .xml 로 끝나는 파일을 뜻함
		sqlSessionFactoryBean.setMapperLocations(appContext.getResources("classpath:/mapper/**/sql-*.xml"));
		sqlSessionFactoryBean.setConfiguration(mybatisConfig());
		
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	@Bean
	@ConfigurationProperties(prefix="mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfig() {
		return new org.apache.ibatis.session.Configuration();
	}
}
