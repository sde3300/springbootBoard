package btc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import btc.dto.bbsDto;
import btc.service.bbsService;

@Controller
public class bbsController {
	
	@Autowired
	private bbsService bbsService;
	
	@RequestMapping("/test")
	public ModelAndView test() throws Exception {
		ModelAndView mv = new ModelAndView("/test");
		
		return mv;
	}
	
	@RequestMapping("/bbs/openBoardList.do")
	public ModelAndView openBoardList() throws Exception{
		ModelAndView mv = new ModelAndView("/bbs/BoardList");
		
		List<bbsDto> list = bbsService.selectBoardList();
		
		mv.addObject("dataList", list);
		
		return mv;
	}
	
//	글쓰기 페이지로 연결
	@RequestMapping("/bbs/openBoardWrite.do")
	public String openBoardWrite() throws Exception {
		return "/bbs/BoardWrite";
	}
	
//	실제 데이터베이스에 insert
	@RequestMapping("/bbs/insertBoard.do")
	public String insertBoardWrite(bbsDto board) throws Exception {
		bbsService.insertBoard(board);
		
		return "redirect:/bbs/openBoardList.do";
	}
	
//	상세페이지
	@RequestMapping("/bbs/openBoardDetail.do")
 	public ModelAndView openBoardDetail(@RequestParam int num) throws Exception {
 		ModelAndView mv = new ModelAndView("/bbs/BoardDetail");
 		
 		bbsDto board = bbsService.selectBoardDetail(num);
 		mv.addObject("board", board);
 	
 		return mv;
 	}
	
//	수정페이지
 	@RequestMapping("/bbs/updateBoard.do")
 	public String updateBoard(bbsDto board) throws Exception {
 		bbsService.updateBoard(board);
 		
 		return "redirect:/bbs/openBoardList.do";
 	}
 	
// 	삭제페이지
 	@RequestMapping("/bbs/deleteBoard.do")
 	public String deleteBoard(int num) throws Exception {
 		bbsService.deleteBoard(num);
 		
 		return "redirect:/bbs/openBoardList.do";
 	}
	
}
