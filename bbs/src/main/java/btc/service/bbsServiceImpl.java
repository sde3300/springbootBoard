package btc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import btc.dto.bbsDto;
import btc.mapper.bbsMapper;

@Service
public class bbsServiceImpl implements bbsService {
	
	@Autowired
	private bbsMapper bbsMapper;
	
	@Override
	public List<bbsDto> selectBoardList() throws Exception {
		return bbsMapper.selectBoardList();
	}
	
	@Override
	public void insertBoard(bbsDto board) throws Exception{
		bbsMapper.insertBoard(board);
	}
	
	@Override
	public bbsDto selectBoardDetail(int num) throws Exception {
		return bbsMapper.selectBoardDetail(num);
	}
	
	@Override
	public void updateBoard(bbsDto board) throws Exception {
		bbsMapper.updateBoard(board);
	}
	
	@Override
	public void deleteBoard(int num) throws Exception {
		bbsMapper.deleteBoard(num);
	}
	
	
}