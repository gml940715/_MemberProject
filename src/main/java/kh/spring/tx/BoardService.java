package kh.spring.tx;

import java.util.List;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.FileDTO;

public interface BoardService {
	public int insert(BoardDTO dto);
	public List<BoardDTO> selectAll();
	public String getNavi(int currentPage);
	public List<BoardDTO> selectByPage(int currentPage);
	public int recordCount();
	public int searchRecordCount(String opt, String searchWord);
	public List<BoardDTO> searchSelectByPage(int currentPage, String opt, String searchWord);
	public String getSearchNavi(int currentPage, String opt, String searchWord);
	public BoardDTO content(int seq);
	public int deletePost(int seq);
	public int alterProc1(int seq, String title, String content);
	public int alterProc2(int seq, String title, String content, String img);
	public String imageUpload(FileDTO fdto);
}
