package kh.spring.dto;

import org.springframework.web.multipart.MultipartFile;

public class SignUpDTO {
	private String id;
	private String password;
	private String name;
	private String phone;
	private MultipartFile image;
	
	public SignUpDTO() {
		super();
	}
	public SignUpDTO(String id, String password, String name, String phone, MultipartFile image) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.image = image;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
}
