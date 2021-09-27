package com.btc.board1.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.btc.board1.dto.Board1Dto;
import com.btc.board1.dto.Board1FileDto;
import com.btc.board1.service.Board1Service;

import lombok.extern.slf4j.Slf4j;

// @Controller은 해당 파일이 Controller 임을 나타내는 어노테이션
// Soap 타입의 웹 서버로 동작하도록 지정
// @RestController 은 해당 파일이 Restful 방식을 지원하는 Controller 임을 나타내는 어노테이션
@Slf4j
@Controller
public class Board1Controller {

//	@Autowired 어노테이션은 사용자가 직접 new 키워드를 사용하여 객체를 생성하지 않고, Spring Framework 에서 제어하는 방식으로 객체를 생성하여 사용하도록 하는 어노테이션
//	Autowired 어노테이션을 사용 시 사용자가 직접 객체를 생성하지 않기 때문에 해당 객체의 생성 및 삭제에 대해서 신경 쓸 필요가 없음
//	Board1Service는 interface 이므로 객체를 생성할 수 없으나, 인터페이스 다형성에 의해서 자식 클래스인 Board1ServiceImlp의 객체를 저장하여 사용하고 있음
//	이 모든 것을 @Autowired 어노테이션을 사용하면 자동으로 Spring Framework가 진행함
	@Autowired
	private Board1Service board1Service;
	
//	logback 사용 선언
//	getLogger() : 매개변수가 로거의 이름이 됨, getLogger("name") 라고 선언 시 name 이라는 이름을 가진 로거 객체 하나가 생성됨(주로 해당 클래스 객체를 많이 사용함)
//	logback 사용 시 롬복을 사용하게 되면 Logger 객체를 생성할 필요가 없음
//	@Slf4j 어노테이션을 선언하면 객체 선언없이 바로 사용할 수 있음
//	Logger log = LoggerFactory.getLogger(this.getClass());
	
	
//	@RequestMapping 은 매개변수로 넘어온 문자열을 URL과 연동하고, 아래의 메서드와 연동
	@RequestMapping("/test")
	public ModelAndView test() throws Exception {
//		ModelAndView 클래스는 데이터와 사용자 뷰 부분을 설정하는 클래스
//		생성자의 매개변수로 html의 위치와 파일명을 입력
//		resource 아래의 template 폴더를 view의 최상위 폴더로 인식함
		ModelAndView mv = new ModelAndView("/user/usertest");
		
		return mv;
	}
	
	
	@RequestMapping("/board1/openBoardList.do")
	public ModelAndView openBoardList() throws Exception {
//		logback log 실행방법
//		log.trace() : trace 레벨의 로그 정보를 출력
//		log.debug() : debug 레벨의 로그 정보를 출력
//		log.info() : info 레벨의 로그 정보 출력
//		log.warn() : warning 레벨의 로그 정보 출력 
//		log.error() : error 레벨의 로그 정보 출력
		
//		templates의 board1 폴더 안에 있는 boardList.html 파일과 연동
		ModelAndView mv = new ModelAndView("/board1/boardList");
		
		System.out.println("------- System.out.println 로 콘솔 화면에 출력 --------");
		
		log.trace("--------------- trace 실행 ------------------");
		log.debug("--------------- debug 실행 ------------------");
		log.info("--------------- info 실행 ------------------");
		log.warn("--------------- warn 실행 ------------------");
		log.error("--------------- error 실행 ------------------");
		
//		ArrayList 타입의 변수 list 생성
//		Service를 통해서 데이터 베이스에 있는 데이터를 조회하여 가져옴
		List<Board1Dto> list = board1Service.selectBoardList();
//		ModelAndView 클래스 타입의 객체 mv에 addObject() 메서드를 사용하여 ArrayList 타입의 Board1Dto 데이터를 저장
//		ModelAndView 클래스 타입의 객체 mv에 저장된 데이터의 이름을 dataList라고 줌
//		addObject() 메서드를 사용 시 HashMap 타입으로 데이터가 저장됨
		mv.addObject("dataList", list);
		
//		요청한 클라이언트로 ModelAndView 클래스 타입의 객체를 전송
		return mv;
	}
	
//	글쓰기 페이지로 연결
//	해당 메서드는 단순히 화면출력을 위한 메서드이므로 반환값을 String으로 함
//	@Controller 어노테이션을 사용하였을 경우 반환 값으로 String을 사용하면 문자열을 반환하는 것이 아니라 html 파일의 위치를 반환
	@RequestMapping("/board1/openBoardWrite.do")
	public String openBoardWrite() throws Exception {
		return "/board1/boardWrite";
	}
	
//	실제 데이터베이스에 insert
//	파일을 업로드하기 위한 MultipartHttpServletRequest가 매개변수로 추가 됨
//	MultipartHttpServletRequest는 ServletRequest를 상속받아 구현된 인터페이스
//	업로그된 파일을 처리하기 위한 여러가지 메서드를 제공함
	@RequestMapping("/board1/insertBoard.do")
	public String insertBoard(Board1Dto board, MultipartHttpServletRequest mhsr) throws Exception {
//		service를 이용하여 DB에 접근하여 데이터를 추가
		board1Service.insertBoard(board, mhsr);
		
//		redirect : URL 포워딩으로 다른 페이지로 강제 이동하는 명령
//		위의 public String openBoardWrite() 메서드의 return "/board1/boardWrite"; 명령과 다른 것은 위의 명령은 해당 html 파일을 사용자에게 출력하라는 의미이고 return "redirect:/board1/openBoardList.do"; 는 사용자가 웹 브라우저의 주소창에 입력한 주소로 이동하는 것과 동일한 명령을 내린다는 의미임
		return "redirect:/board1/openBoardList.do";
	}
	
//	상세 페이지
	@RequestMapping("/board1/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam int boardIdx) throws Exception {
		ModelAndView mv = new ModelAndView("/board1/boardDetail");
		
		Board1Dto board = board1Service.selectBoardDetail(boardIdx);
		mv.addObject("board", board);
		
		return mv;
	}
	
//	수정 페이지
	@RequestMapping("/board1/updateBoard.do")
	public String updateBoard(Board1Dto board) throws Exception {
//		service를 이용하여 DB에 접근하여 데이터를 수정
		board1Service.updateBoard(board);
		
		return "redirect:/board1/openBoardList.do";
	}
	
//	삭제 페이지
	@RequestMapping("board1/deleteBoard.do")
	public String deleteBoard(int boardIdx) throws Exception {
//		service를 이용하여 DB에 접근, 데이터 삭제
		board1Service.deleteBoard(boardIdx);
		
		return "redirect:/board1/openBoardList.do";
	}
	
//	파일 다운로드
	@RequestMapping("board1/downloadBoard1File.do")
	public void downloadBoardFile(@RequestParam int idx, @RequestParam int boardIdx, HttpServletResponse response) throws Exception {
		Board1FileDto boardFile = board1Service.selectBoardFileInfo(idx, boardIdx);
		
		if (ObjectUtils.isEmpty(boardFile) == false) {
			String fileName = boardFile.getOriginalFileName();
//			common.io.FileUtils 사용
			byte[] files = FileUtils.readFileToByteArray(new File(boardFile.getStoredFilePath()));
			
			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
//			java.net.URLEncoder 사용
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}
}








