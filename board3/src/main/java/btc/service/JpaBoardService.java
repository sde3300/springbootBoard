package btc.service;

import java.util.List;

import btc.entity.BoardEntity;

public interface JpaBoardService {
	List<BoardEntity> selectBoardList() throws Exception;
	
	void saveBoard(BoardEntity board) throws Exception;
	
	BoardEntity selectBoardDetail(int boardIdx) throws Exception;
	
	void deleteBoard(int boardIdx) throws Exception;

}
