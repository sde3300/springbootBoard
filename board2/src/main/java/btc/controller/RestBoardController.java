package btc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import btc.dto.BoardDto;
import btc.service.BoardService;

@Controller
public class RestBoardController {

//	RESTful : 자원을 이름으로 구분하여 해당 자원의 정보를 주고 받는 모든 것
//	잘 표현된 HTTP URI로 리소스를 정의하고, HTTP method로 리소스(json, xml)에 대한 행위를 정의함
	
//	Http 의 창시자 중 1명인 로이 필딩이 2000년 논문으로 REST 발표
//	RESTful 방식은 데이터를 전송하는 방법을 지정
//	http method의 기능 중 POST(입력), GET(조회), PUT(수정), DELETE(삭제)에 각각의 역할을 부여하여 사용하는 방식
//	@RequestMapping 사용 시 value, method 속성을 사용하고, value에는 주소를 입력, method에는 ReqeustMethod(GET, POST, PUT, DELETE) 값을 입력함
//	만약 method, value 속성을 사용하지 않으려면 @GetMapping, @PostMapping, @PutMapping, @DeleteMapping 어노테이션을 대신 사용하면 됨
	
//	URL(Uniform Resource Locator) : 자원의 위치
//	URI(Uniform Resource Identifier) : 자원 식별자
//	현재는 두가지 모두 같은 의미로 사용함
	
	@Autowired
	private BoardService boardService;

	@RequestMapping(value="/restBoard", method=RequestMethod.GET)
	public ModelAndView openBoardList() throws Exception {
		ModelAndView mv = new ModelAndView("/restBoard/restBoardList");
		
		List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("datas", list);
		
		return mv;
	}
	
//	글을 등록하기 위한 사용자 입력 페이지를 출력하기 위한 맵핑
//	사용자 입력 페이지의 주소와 데이터 베이스에 등록하기 위한 주소가 동일함
//	RESTful 방식을 사용하여 RequestMethod의 값이 GET, POST, PUT, DELETE에 따라서 실행되는 메서드가 달라짐
	@RequestMapping(value="/restBoard/write", method=RequestMethod.GET)
	public String openBoardWrite() throws Exception {
		return "/restBoard/restBoardWrite";
	}
	
//	실제 데이터 베이스에 글을 등록하기 위한 맵핑
	@RequestMapping(value="/restBoard/write", method=RequestMethod.POST)
	public String insertBoard(BoardDto board) throws Exception {
		boardService.insertBoard(board);
		
		return "redirect:/restBoard";
	}
	
//	리소스를 마치 주소인 것처럼 사용함
//	/restBoard/8 처럼 주소와 실제 리소스가 전부 주소인것처럼 사용
//	@PathVariable : RESTful 을 사용하여 실제 리소스부분의 매개변수명과 서버의 자바메서드의 매개변수명을 연동하는 기능을 하는 어노테이션
	@RequestMapping(value="/restBoard/{boardIdx}", method=RequestMethod.GET)
	public ModelAndView openBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception {
		ModelAndView mv = new ModelAndView("/restBoard/restBoardDetail");
		
		BoardDto board = boardService.selectBoardDetail(boardIdx);
		mv.addObject("board", board);
		
		return mv;
	}
	
	@RequestMapping(value="/restBoard/{boardIdx}", method=RequestMethod.PUT)
	public String updateBoard(BoardDto board) throws Exception {
		boardService.updateBoard(board);
		
		return "redirect:/restBoard";
	}
	
	@RequestMapping(value="/restBoard/{boardIdx}", method=RequestMethod.DELETE)
	public String deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception {
		boardService.deleteBoard(boardIdx);
		
		return "redirect:/restBoard";
	}
	
// 초기 서버 확인용
	@RequestMapping("/test")
	public String test() throws Exception {
		return "/test/test";
	}
	
}








