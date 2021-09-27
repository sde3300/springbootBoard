package btc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import btc.dto.BoardDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> selectBoardList() throws Exception;
	BoardDto selectBoardDetail(int boardIdx) throws Exception;
	
	void insertBoard(BoardDto board) throws Exception;
	
	void updateBoard(BoardDto board) throws Exception;
	void updateHitCount(int boardIdx) throws Exception;
	
	void deleteBoard(int boardIdx) throws Exception;
}
