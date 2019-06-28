package kh.spring.dao;

import java.util.List;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.MemberDTO;

public interface BoardDAO {
	public int insert(BoardDTO dto);
	public List<BoardDTO> selectAll();
	public int recordCount();
	public String getNavi(int currentPage);
	public List<BoardDTO> selectByPage(int currentPage);
	public int searchRecordCount(String opt, String searchWord);
	public List<BoardDTO> searchSelectByPage(int currentPage, String opt, String searchWord);
	public String getSearchNavi(int currentPage , String opt , String searchWord);
	public BoardDTO content(int seq);
	public int deletePost(int seq);
	public int viewCount(int seq);
	
}
