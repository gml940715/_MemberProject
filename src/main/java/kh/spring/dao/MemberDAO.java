package kh.spring.dao;

import kh.spring.dto.MemberDTO;

public interface MemberDAO {
	public int insert(MemberDTO dto);
	public int insertNoImg(MemberDTO dto);
	public boolean idDupleCheck(String id);
	public boolean idPwExist(String id, String pw);
	public MemberDTO selectAll(String id);
	public int alterMyInfoWithPw(String pw, String phone,String image, String id);
	public int alterMyinfo(String phone, String image, String id);
	public int withdrawal(String id);
	public String replaceAll(String text);
}
