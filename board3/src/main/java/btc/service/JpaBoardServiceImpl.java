package btc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import btc.entity.BoardEntity;
import btc.repository.JpaBoardRepository;

public class JpaBoardServiceImpl implements JpaBoardService {

   
   @Autowired
   private JpaBoardRepository jBRepository;
   
   @Override
   public List<BoardEntity> selectBoardList() throws Exception {
//	   전체 게시물 목록을 조회하는 명령
//	   jpa를 사용하여 필요한 메서드와 조합하여 사용
	   return jBRepository.findAllByOrderByBoardIdxDesc();
   }

   @Override
   public void saveBoard(BoardEntity board) throws Exception {
//  	세션으로 로그인된 id를 받아서 입력
	   board.setCreatorId("tester1");
//	   mybatis에서는 insert 명령과 update 명령을 따로 사용하였지만 jpa에서는 save 라는 하나의 명령어로 insert와 update 명령 두가지를 모두 처리함
//	   저장할 내용이 새로 생성된 내용이면 insert 명령을 수행하고, 기존의 내용에서 수정되는 내용이면 update 명령을 수행
	   
	   jBRepository.save(board);
   }

   @Override
   public BoardEntity selectBoardDetail(int boardIdx) throws Exception {
//		JPA의 CrudRepository에서 제공하는 기능으로 주어진 id를 가진 엔티티를 조회함
//	   	JPA 2.0 이전에는 findOne 이라는 이름의 메서드였지만 JPA 2.0에서 findById로 변경되고, 결과값도 Optional 클래스 타입으로 변경됨
	   
//	   Optional 클래스 : null이 올 수 있는 값을 감싸는 래핑클래스, NullPointerException이 발생하지 않도록 도와주는 클래스
	   
	   	Optional<BoardEntity> opt = jBRepository.findById(boardIdx);

//	   	해당 게시물 번호를 조회한 결과가 비었는지 아닌지를 확인
	   	if (opt.isPresent()) {
//	   		BoardEntity 클래스 타입의 변수에 Optional 클래스 타입의 객체의 데이터를 대입함
	   		BoardEntity board = opt.get();
//	   		조회수 올리기
	   		board.setHitCnt(board.getHitCnt() + 1);
//	   		조회수를 업데이트한 BoardEntity를 save 메서드를 사용하여 DB에 저장
	   		jBRepository.save (board);
         
//	   		모든 작업이 끝난 BoardEntity 클래스 타입의 변수 board를 controller로 반환함
	   		return board;
	   }
	   else {
//		   간단하게 표시하기 위해서 NullPointerException()을 발생
//		   실제로는 상황에 맞게 예외처리를 해야함
		   throw new NullPointerException();
	   }
   }

   @Override
   public void deleteBoard(int boardIdx) throws Exception {
//	   주어진 id를 가지고 있는 엔티티를 삭제
	   jBRepository.deleteById(boardIdx);

   }

}
