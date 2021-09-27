package btc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import btc.dto.bbsDto;


	@Mapper
	public interface bbsMapper {

	List<bbsDto> selectBoardList() throws Exception;
	
	void insertBoard(bbsDto board) throws Exception;

	bbsDto selectBoardDetail(int num) throws Exception;
	
	public void updateBoard(bbsDto board) throws Exception;
	
	public void deleteBoard(int num) throws Exception;
}
