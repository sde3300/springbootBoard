package btc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import btc.dto.BoardDto;
import btc.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		
		return boardMapper.selectBoardList();
	}

	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception {
		BoardDto board = boardMapper.selectBoardDetail(boardIdx);
		
		// 파일 목록 불러오기
		// 조회 수 변경하기
		
		return board;
	}

	@Override
	public void insertBoard(BoardDto board) throws Exception {
		boardMapper.insertBoard(board);
		
//		업로드된 파일 확인하기
//		업로드된 파일 목록 추가하기
	}
	
	@Override
	public void updateBoard(BoardDto board) throws Exception {
		boardMapper.updateBoard(board);
	}
	
	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
	}

}
