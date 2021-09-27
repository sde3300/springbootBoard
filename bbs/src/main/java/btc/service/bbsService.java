package btc.service;

import java.util.List;

import btc.dto.bbsDto;


public interface bbsService {
//	게시물 목록
	List<bbsDto> selectBoardList() throws Exception;
	
//	게시물 등록
	void insertBoard(bbsDto board) throws Exception;
	
//	상세 게시물 확인
	bbsDto selectBoardDetail(int num) throws Exception;
	
//	게시물 수정
	void updateBoard(bbsDto board) throws Exception;
	
//	게시물 삭제
	void deleteBoard(int num) throws Exception;
	
}
