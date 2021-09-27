package com.btc.board1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

//@SpringBootApplication
// exclude : @SpringBootApplication 어노테이션에서 exclude를 사용하게 되면 해당 클래스를 자동 구성하지 않도록 설정함
@SpringBootApplication(exclude= {MultipartAutoConfiguration.class})
public class Board1Application {

	public static void main(String[] args) {
		SpringApplication.run(Board1Application.class, args);
	}

}
