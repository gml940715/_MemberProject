package kh.spring.daoimpl;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.spring.dto.BoardDTO;
import kh.spring.tx.BoardService;

@Service
public class BoardServiceimpl implements BoardService{
	@Autowired
	private BoardDAOimpl bdao;
	@Autowired
	private DataSource ds;
	@Autowired
	private HttpSession session;
	
	@Override
	@Transactional("txManager")
	public int insert(BoardDTO dto) {
		int result = bdao.insert(dto);
		return result;
	}
	@Override
	@Transactional("txManager")
	public List<BoardDTO> selectAll() {
		List<BoardDTO> list = bdao.selectAll();
		return list;
	}

	@Override
	@Transactional("txManager")
	public String getNavi(int currentPage) {
		String pageNavi = bdao.getNavi(currentPage);
		return pageNavi;
	}

	@Override
	@Transactional("txManager")
	public List<BoardDTO> selectByPage(int currentPage) {
		List<BoardDTO> list = bdao.selectByPage(currentPage);
		return list;
	}

	@Override
	@Transactional("txManager")
	public int recordCount() {
		
		int count = bdao.recordCount();
		return count;
	}
	@Override
	@Transactional("txManager")
	public int searchRecordCount(String opt, String searchWord) {
		int result = bdao.searchRecordCount(opt, searchWord);
		return result;
	}
	@Override
	@Transactional("txManager")
	public List<BoardDTO> searchSelectByPage(int currentPage, String opt, String searchWord){
		List<BoardDTO> list = bdao.searchSelectByPage(currentPage, opt, searchWord);
		return list;
	}
	@Override
	@Transactional("txManager")
	public String getSearchNavi(int currentPage, String opt, String searchWord) {
		String navi = bdao.getSearchNavi(currentPage, opt, searchWord);
		return navi;
	}
	@Override
	@Transactional("txManager")
	public BoardDTO content(int seq) {
		BoardDTO content = bdao.content(seq);
		bdao.viewCount(seq);
		return content;
	}
	@Override
	@Transactional("txManager")
	public int deletePost(int seq) {
		int result = bdao.deletePost(seq);
		return result;
	}
	
	
}
