package btc.dto;

import java.util.List;

import lombok.Data;

@Data
public class BoardDto {
	private int boardIdx;
	private String title;
	private String contents;
	private int hitCnt;
	private String createdDate;
	private String creatorId;
	private String updatedDate;
	private String updaterId;
	private List<BoardFileDto> fileList;
}
