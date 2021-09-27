package btc.dto;

import lombok.Data;

//	lombok 라이브러리를 사용하여 getter/setter를 자동으로 생성함
@Data
public class bbsDto {
	private int num;
	private String title;
	private String contents;
	private String userId;
	private String createDt;
	private String updateDt;
	private int views;
	private int likes;
//	private String deleteYn;
}
