package btc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 자바 8버전부터 자바7 버전 이하에서 문제가 되었던 시간 관련 클래스들이 추가되었음
// 자바 8버전부터 JSR-310이라는 표준명세로 날짜와 시간에 관련된 새로운 API가 추가 됨
// 날짜 및 시간에 관련된 클래스를 그대로 사용할 경우 MySql 버전에 따라서 문제가 발생할 수 있음
// Jsr310JapConverters 를 적용하여 사용

// @@EntityScan 어노테이션은 에플리케이션이 실행될 때 basePackages로 지정된 패키지의 하위에서 JPA 엔티티 들을 검색함
// 여기서 JPA 엔티티는 @Entity 어노테이션이 설정된 클래스를 뜻함 

@EnableJpaAuditing
@EntityScan(
		basePackageClasses = {Jsr310JpaConverters.class},
		basePackages = {"btc"})

@SpringBootApplication
public class Board3Application {

	public static void main(String[] args) {
		SpringApplication.run(Board3Application.class, args);
	}

}
