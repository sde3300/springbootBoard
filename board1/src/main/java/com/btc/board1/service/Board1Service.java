package com.btc.board1.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.btc.board1.dto.Board1Dto;
import com.btc.board1.dto.Board1FileDto;

// interface는 객체를 생성할 수 없음
// interface를 상속받아 구현하는 클래스를 사용해야 함
public interface Board1Service {
//	반환값이 ArrayList 타입, ArrayList에 저장되는 데이터는 Board1Dto 클래스 타입
//	게시판의 게시물 목록을 불러오는 메서드
	List<Board1Dto> selectBoardList() throws Exception;
	
//	게시판의 게시물을 등록하는 메서드
	void insertBoard(Board1Dto board, MultipartHttpServletRequest mgsr) throws Exception;
	
//	게시판의 상세 게시물 확인 메서드
	Board1Dto selectBoardDetail(int boardIdx) throws Exception;
	
//	게시물의 내용 수정
	void updateBoard(Board1Dto board) throws Exception;
	
//	게시물 삭제
	void deleteBoard(int boardIdx) throws Exception;
	
//	파일 다운로드
	Board1FileDto selectBoardFileInfo(int idx, int boardIdx) throws Exception;
}
