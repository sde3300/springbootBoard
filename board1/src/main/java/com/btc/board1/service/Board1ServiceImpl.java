package com.btc.board1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.btc.board1.common.FileUtils;
import com.btc.board1.dto.Board1Dto;
import com.btc.board1.dto.Board1FileDto;
import com.btc.board1.mapper.Board1Mapper;

import lombok.extern.slf4j.Slf4j;

// Board1Service 인터페이스를 구현하는 클래스
// Service 어노테이션은 해당 클래스가 Service 파일을 구현한 것임을 나타내는 어노테이션
// 내부에서 자바 로직을 처리하는데 사용함

// mapper 와 연결하여 데이터 베이스에 접근하여 CRUD작업을 진행함
@Slf4j
@Service
public class Board1ServiceImpl implements Board1Service {

	@Autowired
	private Board1Mapper board1Mapper;
	
	@Autowired
	private FileUtils fileUtils;
	
//	오버라이딩을 통해서 상속받은 메서드 selectBoardList()를 구현
	@Override
	public List<Board1Dto> selectBoardList() throws Exception {
		return board1Mapper.selectBoardList();
	}
	
//	오버라이딩을 통해서 상속받은 메서드 insertBoard()를 구현
//	Mapper 를 통해서 DB서버에 데이터를 저장
//	파일 데이터가 MultipartHttpServletRequest를 통해서 넘어옴
	@Override
	public void insertBoard(Board1Dto board, MultipartHttpServletRequest mhsr) throws Exception {
//		기존의 게시물 등록을 위한 mapper 실행
		board1Mapper.insertBoard(board);
		
//		매개변수로 넘어온 MultipartHttpServletRequest 클래스 타입의 변수 mhsr의 데이터에서 필요한 정보만 가져옴
//		FileUtils 클래스를 사용하여 서버에 파일 저장 및 Board1FileDto 클래스 타입으로 변경
		List<Board1FileDto> list = fileUtils.parseFileInfo(board.getBoardIdx(), mhsr);
		
		for (int i = 0; i < productlIST.size(); i++) {
			insert(productList[i]);
		}
//		CollectionUtils.isEmpty()는 ObjectUtils.isEmpty()와 동일하게 스프링프레임워크에서 지원하고 있는 클래스의 메서드이지만 변수 list가 List<Board1FileDto>으로 만들어졌으므로 CollectionUtils.isEmpty()으로 비었는지 아닌지를 확인
//		업로드된 파일이 존재 시 mapper를 사용하여 t_file 테이블에 등록
		if (CollectionUtils.isEmpty(list) == false) {
			board1Mapper.insertBoardFileList(list);
		}
		
////		넘어온 파일 데이터의 정보를 확인
////		ObjectUtils : 스프링프레임워크에서 지원하는 util클래스임, isEmpty() 를 실행 시 매개변수로 넘어온 객체가 비어있는지 아닌지를 확인(true - 빈 객체, false - 데이터가 있는 객체)
//		if (ObjectUtils.isEmpty(mhsr) == false) {
////			Iterator : 반복자
////			mhsr.getFileNames() : MultipartHttpServletRequest를 통해서 전송받은 파일 정보 중 파일의 이름만 모두 가져옴
//			Iterator<String> iterator = mhsr.getFileNames();
//			String name; // 파일의 이름을 입력받을 변수
//			
////			파일 정보가 있으면 while 문 실행
//			while (iterator.hasNext()) {
//				name = iterator.next(); // 현재 커서 다음의 정보를 가져옴
//				log.debug("file tag name : " + name);
//				// 가져온 이름을 기준으로 실제 파일 정보를 List 타입으로 모두 가져옴
//				List<MultipartFile> list = mhsr.getFiles(name);
//				
////				for문 통해서 list에 저장된 모든 파일 정보를 첫번째 요소부터 마지막 요소까지 출력
//				for (MultipartFile multiFile : list) {
//					log.debug("*** start file information ***");
//					log.debug("file name : " + multiFile.getOriginalFilename());
//					log.debug("file size : " + multiFile.getSize() + "");
//					log.debug("file content type : " + multiFile.getContentType());
//					log.debug("*** end file information ***\n");
//				}
//			}
//		}
	}
	
//	오버라이딩을 통해서 상속받은 메서드 selectBoardDetail()를 구현
//	Mapper 를 통해서 DB서버에 있는 boardIdx 번호의 게시물의 모든 정보를 가져옴
	@Override
	public Board1Dto selectBoardDetail(int boardIdx) throws Exception {
//		파일 리스트를 포함한 게시물 상세 정보를 가져와야 하기 때문에 기존의 소스를 제거
//		return board1Mapper.selectBoardDetail(boardIdx);
//		기존의 게시물 상세 정보 가져오기
		Board1Dto board = board1Mapper.selectBoardDetail(boardIdx);
//		첨부 파일 리스트 가져오기
		List<Board1FileDto> fileList = board1Mapper.selectBoardFileList(boardIdx);
//		기존의 게시물 상세 정보에 첨부 파일 리스트 추가하기
		board.setFileList(fileList);
		
//		해당 게시물의 조회수 증가
		board1Mapper.updateHitCount(boardIdx);
		
		return board;
	}
	
//	오버라이딩을 통해서 상속받은 메서드 updateBoard()를 구현
//	Mapper 를 통해서 DB서버의 데이터를 사용자가 입력한 내용으로 수정
	@Override
	public void updateBoard(Board1Dto board) throws Exception {
		board1Mapper.updateBoard(board);
	}

	
//	오버라이딩을 통해서 상속받은 메서드 deleteBoard()를 구현
//	Mapper 를 통해서 DB서버의 데이터를 삭제
	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		board1Mapper.deleteBoard(boardIdx);
	}
	
	@Override
	public Board1FileDto selectBoardFileInfo(int idx, int boardIdx) throws Exception {
		return board1Mapper.selectBoardFileInfo(idx, boardIdx);
	}
}










