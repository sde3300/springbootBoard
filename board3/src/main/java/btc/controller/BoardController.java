package btc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import btc.entity.BoardEntity;
import btc.service.JpaBoardService;

@Controller
public class BoardController {
	
//	JPA(Java persistence API) : 자바 객체와 데이터베이스 테이블 간의 매핑을 처리하는 OMR 기술 표준
//	ORM은 객체와 관계를 설정하는 것을 뜻함, 여기서 객체는 OOP의 객체 (객체 지향 언어의 객체)이고, 관계란 관계형 데이터베이스를 의미함
//	ORM은 객체와 데이터베이스의 매핑을 도와주는 기술을 의미함
// 	ORM은 특정한 언어에 종속적인 개념이 아니라 객체와 관계형 데이터베이스를 매핑시킨다는 개념이고, 이러한 ORM의 개념을 구현하기 위한 표준이 JPA임
//	JPA는 각 기능의 동작이 어떻게 되어야 한다는 것을 정의한 기술 명세임
//	이러한 JPA 기술명세에 따라 실제로 기능을 구현한 구현체를 JPA 프로바이더라고 함
//	가장 유명한 JPA 프로바이더는 하이버네이트임
	
//	JPA 장점
//	개발이 편함 : 기본적인 CRUD용 SQL을 작성하지 않아도 됨
//	데이터베이스에 독립적인 개발이 가능 : JPA가 해당 데이터베이스에 맞는 쿼리를 생성해 줌
//	유지보수가 쉬움 : JPA를 사용 시 JPA 엔티티만 수정하면 됨
	
//	JPA 단점
//	학습이 어려움 : 기존 방식에 비해서 배워야할 것이 많음, SQL을 직접적으로 사용하지 않기 때문에 DB 튜닝이 어려움
//	특정 DB의 기능을 사용할 수 없음 : DB 전용 함수를 사용할 수 없음
//	객체지향 설계가 필요함 :  보통 데이터베이스를 위주로 프로그램의 설계가 이루어지기 때문에 객체지향적인 설계가 어려움
	 
	@Autowired
	   private JpaBoardService jBService;
	   
	   
	   @RequestMapping("/")
	   public String index() throws Exception {
	      return "/index";
	   }
	   
	   @RequestMapping(value="/jpa/board", method=RequestMethod.GET)
	   public ModelAndView openBoardList(ModelMap model) throws Exception {
	      ModelAndView mv = new ModelAndView("/board/jpaBoardList");
	      
	      List<BoardEntity> list = jBService.selectBoardList();
	      mv.addObject("datas", list);
	      
	      return mv;
	   }
	   
	   
	   @RequestMapping(value="/jpa/board/write", method=RequestMethod.GET)
	   public String openBoardWrite() throws Exception {
	      return "/board/jpaBoardWrite";
	   }
	   
	   @RequestMapping(value="/jpa/board/write", method=RequestMethod.POST)
	   public String writeBoard(BoardEntity board) throws Exception {
	      jBService.saveBoard(board);
	      
	      return "redirect:/jpa/board";
	   }
	   
	   @RequestMapping(value="/jpa/board/{boardIdx}", method=RequestMethod.GET)
	   public ModelAndView openBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception {
	      ModelAndView mv = new ModelAndView("/board/jpaBoardDetail");
	      
	      BoardEntity board = jBService.selectBoardDetail(boardIdx);
	      mv.addObject("board", board);
	      
	      return mv;
	   }
	   
	   @RequestMapping(value="/jpa/board/{boardIdx}", method=RequestMethod. PUT)
	   public String updateBoard(BoardEntity board) throws Exception {
	      jBService.saveBoard(board);
	      
	      return "redirect:/jpa/board";
	   }
	   
	   @RequestMapping(value="/jpa/board/{boardIdx}", method=RequestMethod.DELETE)
	   public String deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception {
	      jBService.deleteBoard(boardIdx);
	      
	      return "redirect:/jpa/board";
	   }

}
