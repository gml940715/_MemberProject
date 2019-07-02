package kh.spring.service;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;
import kh.spring.tx.MemberService;

@Component
public class MemberServiceimpl implements MemberService{
	@Autowired
	private MemberDAO dao;
	@Autowired
	private DataSource ds;
	@Autowired
	HttpSession session;
	
	@Override
	@Transactional("txManager")
	public int signUpProc(MemberDTO dto) {
		int result = 0;
		try {
			result = dao.insert(dto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	@Transactional("txManager")
	public boolean idDupleCheck(String id) {
		boolean result = dao.idDupleCheck(id);
		return result;
	}
	@Override
	@Transactional("txManager")
	public MemberDTO loginProc(String id, String password) {
		boolean result = dao.idPwExist(id, password);
		MemberDTO profile = null;
		if(result == true) {
			profile = dao.selectAll(id);
		}
		return profile;
	}
	@Override
	@Transactional("txManager")
	public boolean idPwExist(String id, String pw) {
		boolean result = dao.idPwExist(id, pw);
		return result;
	}
	@Override
	@Transactional("txManager")
	public MemberDTO selectAll(String id) {
		MemberDTO myInfo = dao.selectAll(id);
		return myInfo;
	}
	@Override
	@Transactional("txManager")
	public MemberDTO alterMyInfo(String pw, String phone, String image, String id){
		int result = 0;

		if(pw.equals("")) {
			System.out.println("비번 없는 것");
			result = dao.alterMyinfo(phone, image, id);
			if(result < 1) {
				return null;
			}
		}else {
			System.out.println("비번 있는 것");
			result = dao.alterMyInfoWithPw(pw, phone, image, id);
			if(result < 1) {
				return null;
			}
		}
		MemberDTO myInfo = dao.selectAll(id);
		return myInfo;
	}
	@Override
	@Transactional("txManager")
	public int withdrawal(String id) {
		int result = dao.withdrawal(id);
		return result;
	}
	@Override
	@Transactional("txManager")
	public String replaceAll(String text) {
		String result = dao.replaceAll(text);
		return result;
	}
	@Override
	@Transactional("txManager")
	public int signUpNoImg(MemberDTO dto) {
		int result = dao.insertNoImg(dto);
		return result;
	}
	




}
