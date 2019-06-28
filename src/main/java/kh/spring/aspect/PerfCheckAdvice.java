package kh.spring.aspect;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
@Aspect
public class PerfCheckAdvice {
	@Autowired
	private HttpSession session;

	@Around("execution(* *kh.spring.practice.HomeController.*(..))")
	public Object perfCheck(ProceedingJoinPoint pjp) {
		Signature sign = pjp.getSignature();
		long startTime = System.currentTimeMillis();

		Object retVal = null;
		try {
			retVal = pjp.proceed();
		}catch(Throwable e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println(sign.getName()+"이" + (endTime - startTime)/1000.0 + "초 동안 실행");
		return retVal;
	}
	
	@Around("execution(* kh.spring.practice.HomeController.toMypage(..)) || execution(* kh.spring.practice.HomeController.checkPw(..))"
			+ "|| execution(* kh.spring.practice.BoardController.*(..)) || execution(* kh.spring.practice.HomeController.toChat(..))")
	public Object loginCheck(ProceedingJoinPoint pjp)throws Throwable {
		String id = (String)session.getAttribute("loginId");
		if(id == null) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("msg","<script>alert('로그인을 먼저 진행해주세요')</script>");
			mav.setViewName("index");
			return mav;
		}else {return pjp.proceed();}
	}
	
}
