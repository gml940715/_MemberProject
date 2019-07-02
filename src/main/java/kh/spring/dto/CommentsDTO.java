package kh.spring.dto;

import java.sql.Timestamp;

public class CommentsDTO {
	private int seq;
	private String comments;
	private String writer;
	private int postNum;
	private String ip;
	private Timestamp writeDate;
	
	public CommentsDTO() {
		super();
	}
	public CommentsDTO(int seq, String comments, String writer, int postNum, String ip, Timestamp writeDate) {
		super();
		this.seq = seq;
		this.comments = comments;
		this.writer = writer;
		this.postNum = postNum;
		this.ip = ip;
		this.writeDate = writeDate;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Timestamp getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
	}
}
