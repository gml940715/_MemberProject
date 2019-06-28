package kh.spring.dto;

public class MemberDTO {
	private int seq;
	private String id;
	private String password;
	private String name;
	private String phone;
	private String image;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public MemberDTO(int seq, String id, String password, String name, String phone, String image) {
		super();
		this.seq = seq;
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.image = image;
	}
	public MemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
