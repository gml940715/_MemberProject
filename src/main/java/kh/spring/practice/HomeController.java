package kh.spring.practice;

import java.io.File;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kh.spring.daoimpl.MemberServiceimpl;
import kh.spring.dto.MemberDTO;
import kh.spring.dto.SignUpDTO;


@Controller
public class HomeController {

	@Autowired
	private HttpSession session;
	@Autowired
	private MemberServiceimpl ms;

	@RequestMapping("/")
	public ModelAndView toHome() {
		ModelAndView mav = new ModelAndView();
		if((String)session.getAttribute("loginId") != null) {
		String id = (String)session.getAttribute("loginId");
		
		MemberDTO profile = null;
		try {
		 profile = ms.selectAll(id);
		}catch(Exception e) {e.printStackTrace();}
		mav.addObject("profile", profile);
		}
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/signUpForm")
	public String signUp() {
		return "signUp";
	}
	@RequestMapping("/signUpProc") // 회원 가입 + 프로필 사진 업로드
	public ModelAndView signUpProc(SignUpDTO sdto) {
		ModelAndView mav = new ModelAndView();
		String date = new SimpleDateFormat("yyyyMMdd").format(new java.util.Date());	
		
		if(sdto.getImage().getSize() == 0) {
			MemberDTO mdto = new MemberDTO(0, sdto.getId(),sdto.getPassword(),sdto.getName(),sdto.getPhone(),null);
			int result = 0;
			try {
				result = ms.signUpNoImg(mdto);
			
			}catch(Exception e) {
				e.printStackTrace();
				mav.setViewName("redirect:error.do");
			}
			mav.addObject("result", result);
			mav.setViewName("signUpView");
		}else {
			String resourcePath = session.getServletContext().getRealPath("/resources");
			String usersPath = resourcePath + "/users";
			String datePath = usersPath + "/" + date;
			System.out.println("경로: " + resourcePath);

			File uploadPath = new File(datePath);
			if(!uploadPath.exists()) {
				uploadPath.mkdirs();
			}
			
			String pjPath = "/users/" + date +"/"+ sdto.getId() + "_profile.png";
			System.out.println(pjPath);
			try {
				sdto.getImage().transferTo(new File(datePath + "/" + sdto.getId() + "_profile.png"));
			}catch(Exception e) {
				e.printStackTrace();
			}
			MemberDTO mdto = new MemberDTO(0, sdto.getId(),sdto.getPassword(),sdto.getName(),sdto.getPhone(), pjPath);
			int result = 0;
			try {
				result = ms.signUpProc(mdto);
			
			}catch(Exception e) {
				e.printStackTrace();
				mav.setViewName("redirect:error.do");
			}
			mav.addObject("result", result);
			mav.setViewName("signUpView");
		}
			return mav;
	}


	@ResponseBody
	@RequestMapping(value="/idDupleCheck", produces="text/plain;charset=UTF-8")
	public String idDupleCheck(HttpServletRequest request, String id) {
		boolean result = false;
		try {
			result = ms.idDupleCheck(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(result == true) { // 아이디가 있음
			return  "현재 사용중인 아이디 입니다.";
		}return "사용가능한 아이디입니다.";
	}
	@RequestMapping("/loginProc") // 로그인 
	public ModelAndView loginProc1(String id, String password, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String resourcePath = session.getServletContext().getRealPath("/resources");
		MemberDTO profile = null;
		try {
			profile = ms.loginProc(id, password);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(profile == null) {
			mav.setViewName("loginView");
		}else{
			session.setAttribute("loginId", id);
			mav.addObject("profile", profile);
			mav.setViewName("index");
		}
		
		return mav;
	}
	@RequestMapping("/logoutProc")
	public String logoutProc() {
		session.invalidate();
		return "index";
	}

	@RequestMapping("/checkPw")//비밀번호 확인 페이지로 
	public ModelAndView checkPw(String num) {
		ModelAndView mav = new ModelAndView();
		System.out.println(num);
		session.setAttribute("num", num);//1 : 마이페이지 2: 회원탈퇴
		mav.setViewName("checkPw");
		return mav;
	}

	@RequestMapping("/checkPwProc")//비밀번호 확인 과정
	public ModelAndView checkPwProc(String pw, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String id = (String)session.getAttribute("loginId");
		String num = (String)session.getAttribute("num");

		boolean result = false;
		try {
			result = ms.idPwExist(id, pw);
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.addObject("result", result);
		mav.setViewName("checkPwView");
		return mav;
	}
	@RequestMapping("/toMyPage")
	public ModelAndView toMypage() {
		ModelAndView mav = new ModelAndView();
		String id = (String)session.getAttribute("loginId");
		MemberDTO myInfo = null;
		try {
			myInfo = ms.selectAll(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("이미지:" + myInfo.getImage());
		mav.addObject("myInfo",myInfo);
		mav.setViewName("myPage");
		return mav;
	}
	@ResponseBody
	@RequestMapping(value = "/alterMyInfo", produces = "text/plain;charset=UTF-8")
	public String alterMyInfo(String myInfo) {
		System.out.println(myInfo);
		JsonParser jp = new JsonParser();
		JsonObject root = jp.parse(myInfo).getAsJsonObject();
		String pw = root.get("pw").getAsString();
		String phone = root.get("phone").getAsString();
		String image = root.get("image").getAsString();
		
		String rpImage = ms.replaceAll(image);
		System.out.println(rpImage);
		
		
		

		String id = (String)session.getAttribute("loginId");
		
		System.out.println(pw + ":" + phone +":"+ image);
		MemberDTO alterMyInfo = null;
		try {
		 alterMyInfo = ms.alterMyInfo(pw, phone, rpImage, id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(alterMyInfo == null) {
			return "정보 수정에 실패하였습니다.";
		}else {
			return new Gson().toJson(alterMyInfo);
		}
	}
	@ResponseBody
	@RequestMapping("changeProPic")
	public String changeProPic(MultipartFile image) {
		String id = (String)session.getAttribute("loginId");
		String date = new SimpleDateFormat("yyyyMMdd").format(new java.util.Date());	
		int result = 0;	
		String resourcePath = session.getServletContext().getRealPath("/resources");
		String usersPath = resourcePath + "/users";
		String datePath = usersPath + "/" + date;
		System.out.println("경로: " + resourcePath);
		
		File uploadPath = new File(datePath);
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		try {
			image.transferTo(new File(datePath + "/" + id + "_profile.png"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		String pjPath = "/users/" + date + "/" + id + "_profile.png";
		return pjPath;
	}
	@RequestMapping("toWithdrawal")
	public ModelAndView Withdrawal() {
		ModelAndView mav = new ModelAndView();
		String id = (String)session.getAttribute("loginId");
		int result = 0;
		try {
			result = ms.withdrawal(id);
			if(result > 0) {
				session.invalidate();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		mav.addObject("result",result);
		mav.setViewName("withdrawalView");
		return mav;
	}
	@RequestMapping("toChat")
	public ModelAndView toChat() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("chat/chat");
		return mav;
	}
}
