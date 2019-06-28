package kh.spring.tx;

import org.springframework.stereotype.Component;

import kh.spring.dto.MemberDTO;


public interface MemberService {
	public int signUpProc(MemberDTO dto);
	public int signUpNoImg(MemberDTO dto);
	public boolean idDupleCheck(String id);
	public MemberDTO loginProc(String id, String password);
	public boolean idPwExist(String id, String pw);
	public MemberDTO selectAll(String id);
	public MemberDTO alterMyInfo(String pw, String phone, String image, String id);
	public int withdrawal(String id);
	public String replaceAll(String text);
	
}
