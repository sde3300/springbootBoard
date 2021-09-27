package btc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import btc.dto.BoardDto;
import btc.service.BoardService;

// @RestController : @Controller 어노테이션과 @ResponseBody 어노테이션이 합쳐진 어노테이션
// 서버와 클라이언트의 통신에 JSON 데이터 형식을 사용하여 통신
// 데이터 자체를 전송하기 때문에 웹브라우저가 없어도 통신이 가능함
@RestController
public class RestApiBoardController {

	@Autowired
	private BoardService boardService;
	
//	기존에는 ModelAndView 클래스를 사용하여 데이터를 html 파일과 함께 전송했지만 Restful api 를 사용하여 데이터 자체만 클라이언트로 전송
	@RequestMapping(value="/restApi/board", method=RequestMethod.GET)
	public List<BoardDto> openBoardList() throws Exception {
		return boardService.selectBoardList();
	}
	
//	@RequestBody : 메서드의 파라미터(매개변수)가 반드시 HTTP 패킷의 바디에 담겨 있어야 한다는 것을 뜻하는 어노테이션
//	http의 method 중 GET과 POST의 차이점은 GET은 요청 주소에 파라미터값의 같이 담아 전송하는 것이고, POST는 HTTP 패킷의 바디에 담아서 전송
//	POST, PUT 사용하는 메서드는 @RequestBody 어노테이션을 사용해야함
//	GET 을 사용할 경우 @RequestParam 어노테이션을 사용함
	@RequestMapping(value="/restApi/board/write", method=RequestMethod.POST)
	public void insertBoard(@RequestBody BoardDto board) throws Exception {
		boardService.insertBoard(board);
	}
	
//	GET을 사용할 경우 @RequestParam 어노테이션은 restapi 방식이 아닐 경우에 사용하고 restapi를 사용하는 {}가 들어가 있는 경우에는 @PathVariable 어노테이션을 사용
	@RequestMapping(value="/restApi/board/{boardIdx}", method=RequestMethod.GET)
	public BoardDto openBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception {
		return boardService.selectBoardDetail(boardIdx);
	}
	
	@RequestMapping(value="/restApi/board/{boardIdx}", method=RequestMethod.PUT)
	public String updateBoard(@RequestBody BoardDto board) throws Exception {
		boardService.updateBoard(board);
		
		return "redirect:/restBoard";
	}
	
	@RequestMapping(value="/restApi/board/{boardIdx}", method=RequestMethod.DELETE)
	public String deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception {
		boardService.deleteBoard(boardIdx);
		
		return "redirect:/restBoard";
	}
}









