package kh.spring.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileDTO {
	private String title;
	private String contents;
	private MultipartFile img;
	
	
	public FileDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FileDTO(String title, String contents, MultipartFile img) {
		super();
		this.title = title;
		this.contents = contents;
		this.img = img;
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
	public MultipartFile getImg() {
		return img;
	}
	public void setImg(MultipartFile img) {
		this.img = img;
	}
	
	
	
}
