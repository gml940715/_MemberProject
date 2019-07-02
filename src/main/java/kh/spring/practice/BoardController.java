package kh.spring.practice;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.CommentsDTO;
import kh.spring.dto.FileDTO;
import kh.spring.tx.BoardService;
import kh.spring.tx.CommentsService;

@Controller
public class BoardController {

	@Autowired
	private HttpSession session;
	@Autowired
	private BoardService bs;
	@Autowired
	private CommentsService cs;

	@RequestMapping("toBaordList")
	public ModelAndView toBoard(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		session.setAttribute("currentPage", currentPage);
		int count = 0;
		try {
			count = bs.recordCount();
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(count == 0) {
			mav.addObject("count",count);
			mav.setViewName("board/list");
		}else {
			List<BoardDTO> list = null;
			try {
				list = bs.selectByPage(currentPage);
			}catch(Exception e) {
				e.printStackTrace();
			}
			String pageNavi = null;
			try {
				pageNavi = bs.getNavi(currentPage);
			}catch(Exception e) {
				e.printStackTrace();
			}
			mav.addObject("pageNavi",pageNavi);
			mav.addObject("list",list);
			mav.setViewName("board/list");
		}
		return mav;
	}
	@RequestMapping("toWriteForm")
	public ModelAndView toWriteForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/writeForm");
		return mav;
	}

	@RequestMapping("writeProc.board")
	public ModelAndView insert(FileDTO fdto) {
		ModelAndView mav = new ModelAndView();

		System.out.println("이미지: " + fdto.getImg() + "   제목 : " + fdto.getTitle() + "  글내용: " + fdto.getContents());
		String id = (String)session.getAttribute("loginId");
		String date = new SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
		long time = System.currentTimeMillis();

		if(fdto.getImg().getSize()==0) { // 이미지가 없을 경우
			BoardDTO bdto = new BoardDTO(0, fdto.getTitle(), fdto.getContents(), null, id, null, 0);
			int result = 0;
			try {
				result = bs.insert(bdto);
				mav.setViewName("redirect:/toBaordList");
			}catch(Exception e) {
				e.printStackTrace();
				mav.setViewName("board/writeView");
			}
			mav.setViewName("redirect:/toBaordList?currentPage="+session.getAttribute("currentPage"));
		}else { // 이미지가 있을 경우

			bs.imageUpload(fdto);//서버에 이미지 업로드

			String pjPath = "/board"+"/"+ date +"/" + id +"/"+ time + "_board_image.png";
			System.out.println(pjPath);
			String regex = "<img src=\"(.+?)\">";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(fdto.getContents());
			if(m.find()) {m.group(1);}
			
			
			BoardDTO bdto = new BoardDTO(0, fdto.getTitle(), fdto.getContents(), m.group(1), id, null, 0);
			int result = 0;
			try {
				result = bs.insert(bdto);
				mav.setViewName("redirect:/toBaordList?currentPage="+session.getAttribute("currentPage"));
			}catch(Exception e) {
				e.printStackTrace();
				mav.setViewName("board/writeView");
			}
			mav.addObject("result", result);
		}
		return mav;
	}
	@RequestMapping("searchPost")
	public ModelAndView searchPost(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String opt = request.getParameter("opt");
		String searchWord = request.getParameter("searchWord");
		int searchPage = Integer.parseInt(request.getParameter("searchPage"));
		session.setAttribute("searchPage", searchPage);
		System.out.println("현재페이지: " +searchPage+ " 옵션: "+opt+" 검색어: "+searchWord);

		if(opt.equals("작성자") || opt.equals("writer")) {
			int count = 0;
			try {
				count = bs.searchRecordCount("writer", searchWord);
			}catch(Exception e) {e.printStackTrace();}
			if(count == 0) {
				mav.addObject("count",count);
				mav.setViewName("board/list");
			}else {
				List<BoardDTO> list = null;
				String pageNavi = null;
				try {
					list = bs.searchSelectByPage(searchPage, "writer", searchWord);
				}catch(Exception e) {e.printStackTrace();}
				try {
					pageNavi = bs.getSearchNavi(searchPage, "writer", searchWord);
				}catch(Exception e) {e.printStackTrace();}
				mav.addObject("list",list);
				mav.addObject("pageNavi",pageNavi);
				mav.setViewName("board/list");
			}
		}else if(opt.equals("글제목") || opt.equals("title")) {
			int count = 0;
			try {
				count = bs.searchRecordCount("title", searchWord);
			}catch(Exception e) {e.printStackTrace();}
			if(count == 0) {
				mav.addObject("count",count);
				mav.setViewName("board/list");
			}else {
				List<BoardDTO> list = null;
				String pageNavi = null;
				try {
					list = bs.searchSelectByPage(searchPage, "title", searchWord);
				}catch(Exception e) {e.printStackTrace();}
				try {
					pageNavi = bs.getSearchNavi(searchPage, "title", searchWord);
				}catch(Exception e) {e.printStackTrace();}
				mav.addObject("list",list);
				mav.addObject("pageNavi",pageNavi);
				mav.setViewName("board/list");
			}
		}
		return mav;
	}
	@ResponseBody
	@RequestMapping("uploadImage")
	public String uploadImage(FileDTO fdto) {
		String date = new SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
		long time = System.currentTimeMillis();
		String id = (String)session.getAttribute("loginId");
		bs.imageUpload(fdto);
		String pjPath = "/board"+"/"+ date +"/" + id +"/"+ time + "_board_image.png";
		return pjPath;
	}
	@RequestMapping("toContent")
	public ModelAndView toContent(int seq){
		ModelAndView mav = new ModelAndView();
		BoardDTO content = null;
		try {
			content = bs.content(seq);
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.addObject("content",content);
		mav.setViewName("board/content");
		return mav;
	}
	@RequestMapping("deletePost")
	public ModelAndView deletePost(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int seq = Integer.parseInt(request.getParameter("seq"));
		int result = 0;
		try {
			result = bs.deletePost(seq);
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("redirect:/toBaordList?currentPage="+session.getAttribute("currentPage"));
		return mav;
	}
	@RequestMapping("toAlter")
	public ModelAndView toAlter(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int seq = Integer.parseInt(request.getParameter("seq"));
		BoardDTO content = null;
		try {
			content = bs.content(seq);
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.addObject("content",content);
		mav.setViewName("board/alterForm");
		return mav;
	}
	@RequestMapping("alterProc.board")
	public ModelAndView alterProc(HttpServletRequest request, FileDTO fdto) {
		ModelAndView mav = new ModelAndView();
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		String title = request.getParameter("title");
		String content = request.getParameter("contents");
		System.out.println(content);
		int result = 0;
		if(fdto.getImg().getSize() == 0) {
			
			try {
				result = bs.alterProc1(seq, title, content); // 이미지 없는 것
				mav.addObject("result",result);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
				String pjPath = bs.imageUpload(fdto);
			try {
				result = bs.alterProc2(seq, title, content, pjPath); //이미지 있는 것
				mav.addObject("result",result);
				
			}catch(Exception e) {
				e.printStackTrace();	
			}
		}
		mav.addObject("seq",seq);
		mav.setViewName("board/alterView");
		return mav;
	}
//--댓글!!-----------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="commentProc", produces="text/plain;charset=utf-8")
	public String commentProc(String commentInfo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		JsonParser jp = new JsonParser();
		JsonObject root = jp.parse(commentInfo).getAsJsonObject();
		String comment = root.get("comment").getAsString();
		int postNum = root.get("postNum").getAsInt();
		String id = (String)session.getAttribute("loginId");
		String ip = request.getRemoteAddr();
		
		CommentsDTO cdto = new CommentsDTO(0, comment, id, postNum, ip, null);
		int result = 0;
		try {
			result = cs.comments(cdto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new Gson().toJson(cdto);
	}
}
