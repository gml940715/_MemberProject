package kh.spring.daoimpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.dao.CommentsDAO;
import kh.spring.dto.CommentsDTO;

@Repository
public class CommentsDAOimpl implements CommentsDAO{
	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public int insert(CommentsDTO cdto) {
		return sst.insert("CommentsDAO.insert",cdto);
	}
	@Override
	public List<CommentsDTO> selectCm(int postNum){
		return sst.selectList("CommentsDAO.selectCm", postNum);
	}
	
}
