package kh.spring.dao;

import java.util.List;

import kh.spring.dto.CommentsDTO;

public interface CommentsDAO {
	public int insert(CommentsDTO cdto);
	public List<CommentsDTO> selectCm(int postNum);
}
