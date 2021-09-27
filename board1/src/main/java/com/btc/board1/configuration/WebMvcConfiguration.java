package com.btc.board1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver cmpr = new CommonsMultipartResolver();
		cmpr.setDefaultEncoding("UTF-8"); // 업로드될 파일의 문자셋을 UTF-8 설정
		// 업로드될 파일의 크기 설정
		// 파일의 크기는 byte 단위로 설정이 가능함(10 * 1024 * 1024 = 10Mbyte)
		cmpr.setMaxUploadSizePerFile(10 * 1024 * 1024);
		
		return cmpr;
	}
}
