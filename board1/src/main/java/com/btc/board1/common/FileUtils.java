package com.btc.board1.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.btc.board1.dto.Board1FileDto;

@Component
// 사용자가 업로드한 파일을 서버에 저장하기 위해서 사용자가 직접 생성한 클래스
public class FileUtils {
	
//	매개변수로 boardIdx와 MultipartHttpServletRequest 타입의 변수를 넘겨 받음
	public List<Board1FileDto> parseFileInfo(int boardIdx, MultipartHttpServletRequest mhsr) throws Exception {
//		ObjectUtils.isEmpty() 는 스프링프레임워크에서 지원하는 클래스 함수로 해당 오브젝트 타입의 변수가 비었는지 아닌지를 확인해줌(비었을 경우 true)
		if (ObjectUtils.isEmpty(mhsr)) {	
			return null;
		}
		
//		ArrayList 타입의 변수 fileList를 생성
		List<Board1FileDto> fileList = new ArrayList<>();
		
//		날짜를 표시하는 형식을 지원하는 클래스
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
//		날짜 지역에 따라 해당 지역의 시간을 표시
//		컴퓨터(서버)에 지정된 시간대 및 현재 시간에 맞춤
		ZonedDateTime current = ZonedDateTime.now();
//		ZonedDateTime클래스 타입의 변수 current에 DateTimeFormatter 클래스 타입의 변수 format의 형식대로 날짜 및 시간을 표시함
//		현재 format 변수에는 yyyyMMdd 형식으로 표시되어 있기 때문에 '년도월일'형식으로 날짜를 표시함
		String path = "images/" + current.format(format);
		
		File file = new File(path);
//		File 클래스타입의 변수 file에 지정한 폴더와 동일한 폴더가 존재하는지 확인
		if (file.exists() == false) {
//			지정한 폴더와 동일한 폴더가 없을 경우 지정한 폴더를 생성
			file.mkdirs();
		}
		
//		매개변수로 넘겨받은 MultipartHttpServletRequest 클래스 타입의 변수 mhsr에 저장된 파일의 이름 목록을 넘겨 받음
		Iterator<String> iterator = mhsr.getFileNames();
		String newFileName; // 업로드된 파일의 새로운 이름을 저장할 변수, 동시에 여러 사용자가 파일을 업로드 할 수 있기 때문에 현재 시간을 사용하여 이름을 변경하여 저장
		String originalFileExtension; // 원본 파일의 파일 확장자를 확인하여 저장하는 변수
		String contentType; // 실제 파일의 확장자가 저장된 변수
		
//		mhsr에서 넘겨받은 파일 이름 목록을 처음부터 끝까지 확인하기 위한 반복문
		while(iterator.hasNext()) {
			List<MultipartFile> list = mhsr.getFiles(iterator.next());
			
			for (MultipartFile multiFile : list) {
//				가져온 파일이 비었는지 확인
				if (multiFile.isEmpty() == false) {
//					가져온 파일의 확장자를 가져옴
					contentType = multiFile.getContentType();
					
					if (ObjectUtils.isEmpty(contentType)) {
						break;
					}
					else {
//						가져온 파일의 확장자에 지정한 문자열이 있는지 확인
						if (contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						}
						else if (contentType.contains("image/png")) {
							originalFileExtension = ".png";
						}
						else if (contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						}
//						위의 문자열이 아닐 경우 종료
						else {
							break;
						}
					}
					
//					실제로 저장될 파일의 이름을 설정
//					System.nanoTime() 을 사용하는 이유는 동시에 여러 사용자가 파일을 업로드하고, 동일한 파일명을 사용했을 경우 오류가 발생할 수 있으므로 나노초를 기준으로 파일명을 생성하면 그러한 상황이 최소화됨
					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					
//					위에서 확인한 파일의 정보를 DB에 저장하기 위해서 Board1FileDto 타입에 저장하기 위한 Board1FileDto 클래스 타입의 변수 boardFile 생성
					Board1FileDto boardFile = new Board1FileDto();
					boardFile.setBoardIdx(boardIdx);
					boardFile.setFileSize(multiFile.getSize());
					boardFile.setOriginalFileName(multiFile.getOriginalFilename());
//					실제로 저장된 경로 및 파일명 저장
					boardFile.setStoredFilePath(path + "/" + newFileName);
					
//					생성된 Board1FileDto 클래스 타입의 데이터를 List<Board1FileDto> 타입의 변수 fileList에 저장
					fileList.add(boardFile);
					
//					MultipartHttpServletRequest 클래스 타입의 변수 mshr에 의해서 메모리 상의 데이터로만 존재하던 파일 데이터를 실제로 서버의 한 곳에 저장
					file = new File(path + "/" + newFileName);
//					실제 파일 데이터를 File 클래스 타입의 변수 file에 지정된 위치로 파일 데이터를 전송
					multiFile.transferTo(file);
				}
			}
		}
		return fileList;
	}
}

















