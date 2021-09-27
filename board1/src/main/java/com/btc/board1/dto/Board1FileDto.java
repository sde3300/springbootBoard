package com.btc.board1.dto;

import lombok.Data;

@Data
public class Board1FileDto {
	private int idx; // t_file 테이블에 등록된 순번
	private int boardIdx; // t_board 테이블의 게시물 번호와 연동
	private String originalFileName; // 업로드되는 파일의 원본 파일 이름, 실제 저장되는 파일명은 서버에 여러 사용자가 동시에 업로드할 수 있으므로 파일명이 겹칠 우려가 있어 이름을 변경해서 저장해야 함
	private String storedFilePath; // DB에 파일을 직접 저장할 수 있지만 그럴경우 DB의 사용 효율이 떨어지기 때문에 실제 업로드된 파일은 서버의 다른 위치에 저장하고, 실제 파일이 저장된 위치만 DB에 저장함 
	private long fileSize;
}
