package kh.spring.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;
@Component
public class MemberDAOimpl implements MemberDAO{
	@Autowired
	private JdbcTemplate template;
	@Autowired
	private SqlSessionTemplate sst;
	@Override
	public int insert(MemberDTO dto) {
		return sst.insert("MemberDAO.insert", dto);
	}
	@Override
	public int insertNoImg(MemberDTO dto) {
		return sst.insert("MemberDAO.insertNoImg",dto);
	}
	@Override
	public boolean idDupleCheck(String id) {
		int result = sst.selectOne("MemberDAO.idDupleCheck", id);
		if(result > 0) {return true;} 
		return false;
	}
	@Override
	public boolean idPwExist(String id, String pw) {
		HashMap<String,String> hm = new HashMap();
		hm.put("id", id);
		hm.put("pw",pw);
		int result = sst.selectOne("MemberDAO.idPwExist",hm);
		if(result > 0) {return true;}
		return false;
	}
	@Override
	public MemberDTO selectAll(String id) {
		return sst.selectOne("MemberDAO.selectAll", id);
	}
	@Override
	public int alterMyInfoWithPw(String pw, String phone, String image, String id) {
		HashMap<String,String> hm = new HashMap();
		hm.put("pw", pw);
		hm.put("phone",phone);
		hm.put("image",image);
		hm.put("id",id);
		return sst.update("MemberDAO.alterMyInfoWithPw",hm);
		
		
	}
	@Override
	public int alterMyinfo(String phone, String image,String id) {
		HashMap<String,String> hm = new HashMap();
		hm.put("phone",phone);
		hm.put("image",image);
		hm.put("id",id);
		return sst.update("MemberDAO.alterMyinfo",hm);
	}
	@Override
	public int withdrawal(String id) {
		return sst.delete("MemberDAO.withdrawal",id);
	}
	public String replaceAll(String text) {
		text = text.replaceAll("\"","'" );
		return text;
	}

}
