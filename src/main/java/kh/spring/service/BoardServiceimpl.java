package kh.spring.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.spring.dao.BoardDAO;
import kh.spring.dto.BoardDTO;
import kh.spring.dto.FileDTO;
import kh.spring.tx.BoardService;

@Service
public class BoardServiceimpl implements BoardService{
	@Autowired
	private BoardDAO bdao;
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
	@Override
	public int alterProc1(int seq, String title, String content) {
		int result = bdao.alterContent1(seq, title, content);
		return result;
	}
	@Override
	public int alterProc2(int seq, String title, String content, String img) {
		int result = bdao.alterContent2(seq, title, content, img);
		return result;
	}
	@Override
	public String imageUpload(FileDTO fdto) {
		String date = new SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
		long time = System.currentTimeMillis();
		String id = (String)session.getAttribute("loginId");

		String resourcePath = session.getServletContext().getRealPath("/resources");
		String boardPath = resourcePath + "/board";
		String datePath = boardPath +"/"+ date;
		String usersPath = datePath + "/" + id;
		System.out.println("이미지 경로 : " + resourcePath);

		File uploadPath = new File(usersPath);
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();
		}

		try {
			fdto.getImg().transferTo(new File(usersPath + "/" + time + "_board_image.png"));
		}catch(Exception e) {
			e.printStackTrace();
		} 
		String imgPath = null;
		String pjPath = "/board"+"/"+ date +"/" + id +"/"+ time + "_board_image.png";
		System.out.println(pjPath);
		String regex = "<img src=\"(.+?)\">";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(fdto.getContents());
		if(m.find()) {imgPath = m.group(1);}
		return imgPath;
	}

}
