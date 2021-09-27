package com.btc.board1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.btc.board1.dto.Board1Dto;
import com.btc.board1.dto.Board1FileDto;

// @Mapper 어노테이션은 해당 파일이 xml 파일을 매핑해서 사용하는 파일임을 나타내는 어노테이션
// 해당 파일을 구현하는 xml 파일이 존재함을 의미
@Mapper
public interface Board1Mapper {
	List<Board1Dto> selectBoardList() throws Exception;
	
	void insertBoard(Board1Dto board) throws Exception;
	
	Board1Dto selectBoardDetail(int boardIdx) throws Exception;
	
	void updateBoard(Board1Dto board) throws Exception;
	
	void deleteBoard(int boardIdx) throws Exception;
	
	void insertBoardFileList(List<Board1FileDto> list) throws Exception;
	
	List<Board1FileDto> selectBoardFileList(int boardIdx) throws Exception;
	
//	@Param : 파라미터를 지정하는 어노테이션
//	mybatis에서 parameterType을 dto 타입으로 사용하지 않을 경우 해당 어노테이션을 사용하면 지정한 이름으로 mybatis에서 매개변수명으로 사용할 수 있음
	Board1FileDto selectBoardFileInfo(@Param("idx") int idx, @Param("boardIdx") int boardIdx) throws Exception;
	
	void updateHitCount(int boardIdx) throws Exception;
}








