package com.btc.board1.dto;

import java.util.List;

import lombok.Data;

// testdb1 데이터 베이스의 t_board 테이블의 데이터를 가져오거나 저장하기위한 자바 클래스
// DB는 현재 mysql을 사용하고 있고, 개발 언어는 java이므로 db의 데이터와 java의 객체는 타입이 다름
// DB와 Java의 데이터 타입을 맞춰주기 위해서 사용된 클래스

// @Data : 롬복을 사용하여 자동으로 getter/setter를 생성함
@Data
public class Board1Dto {
//	멤버 변수의 이름이 DB의 컬럼명과 동일하게 맞춰야함
//	MyBatis 설정에서 카멜 명명법을 사용하도록 설정했기 때문에 멤버 변수명도 카멜 명명법 형태로 사용함
	private int boardIdx;
	private String title;
	private String contents;
	private int hitCnt;
	private String creatorId;
	private String createdDate;
	private String updaterId;
	private String updatedDate;
	private List<Board1FileDto> fileList;
	
//	public int getBoardIdx() {
//		return boardIdx;
//	}
//	public void setBoardIdx(int boardIdx) {
//		this.boardIdx = boardIdx;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getContents() {
//		return contents;
//	}
//	public void setContents(String contents) {
//		this.contents = contents;
//	}
//	public int getHitCnt() {
//		return hitCnt;
//	}
//	public void setHitCnt(int hitCnt) {
//		this.hitCnt = hitCnt;
//	}
//	public String getCreatorId() {
//		return creatorId;
//	}
//	public void setCreatorId(String creatorId) {
//		this.creatorId = creatorId;
//	}
//	public String getCreatedDate() {
//		return createdDate;
//	}
//	public void setCreatedDate(String createdDate) {
//		this.createdDate = createdDate;
//	}
//	public String getUpdaterId() {
//		return updaterId;
//	}
//	public void setUpdaterId(String updaterId) {
//		this.updaterId = updaterId;
//	}
//	public String getUpdatedDate() {
//		return updatedDate;
//	}
//	public void setUpdatedDate(String updatedDate) {
//		this.updatedDate = updatedDate;
//	}
	
	
}
