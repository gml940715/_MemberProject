package kh.spring.dto;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class BoardDTO {
	private int seq;
	private String title;
	private String contents;
	private String img;
	private String writer;
	private Timestamp writeDate;
	private int viewCount;
	
	public BoardDTO() {
		super();
	}
	public BoardDTO(int seq, String title, String contents, String img, String writer, Timestamp writeDate,
			int viewCount) {
		super();
		this.seq = seq;
		this.title = title;
		this.contents = contents;
		this.img = img;
		this.writer = writer;
		this.writeDate = writeDate;
		this.viewCount = viewCount;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	
	
	

}
