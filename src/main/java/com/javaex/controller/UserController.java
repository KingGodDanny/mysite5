package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class UserController {

	//필드
	@Autowired
	private UserService userService;
	

	// 생성자
	// 메소드(게터세터)
	// 메소드(일반)
	
	//로그인폼
	@RequestMapping(value = "/user/loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("[UserController.loginForm()]");
		
		
		return "user/loginForm";
	}
	
	
	//로그인
	@RequestMapping(value = "/user/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login()]");
		
		UserVo authUser = userService.getUser(userVo);
		
		if(authUser != null) { //로그인 성공하면 (authUser가 null이 아니라면)
			System.out.println("[로그인 성공]");
			session.setAttribute("authUser", authUser);
			
			return "redirect:/main";
		} else { //로그인 실패하면(authUser가 null이라면)
			System.out.println("[로그인 실패]");
			return "redirect:/user/loginForm?result=fail";
		}
		
	}
	
	
	//회원가입폼
	@RequestMapping(value = "/user/joinForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController.joinForm()");
		
		
		return "user/joinForm";
	}
	
	
	//회원가입
	@RequestMapping(value = "/user/join", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinOk(@ModelAttribute UserVo userVo) {
		System.out.println("UserController.join()");
		System.out.println(userVo);
		
		userService.join(userVo);
		
		return "user/joinOk";
	}
	
	
	//회원정보수정폼
	@RequestMapping(value = "user/modifyForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(Model model, HttpSession session) {
		System.out.println("UserController.modifyForm()");
		
		// 1. 사용자 구간에서 넘어온 HTTP세션의 no를 받아준다.
		int no = ((UserVo)session.getAttribute("authUser")).getNo();
		
		System.out.println("회원의 번호" + no);
		
		// 2. 서비스 구간으로 토스!
		UserVo userVo = userService.getUser(no);
		
		//7. 서비스 구간에서 넘어온 정보들을 jsp를 통해 화면에 뿌려주기위해서
		// 리퀘스트의 어트리뷰트 공간에 담아야한다.
		
		System.out.println("컨트롤러 구간: " + userVo);
		model.addAttribute("userVo", userVo);
		
		//8. 어트리뷰트를 jsp에 뿌려주고 사용자에게 수정폼을 보여준다.
		return "user/modifyForm";
	}
	
	
	//회원정보수정
	@RequestMapping(value = "user/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController.modify()");
		
		// 1. 사용자구간에서 넘어오는 수정을 위한 no(세션값)과
		//    이름, 비번 등등을 받아준다. 그리고 서비스로 토스해준다.
		int no = ((UserVo)session.getAttribute("authUser")).getNo();
		userVo.setNo(no);
		userService.userModify(userVo);
		
		// 6. 메인창에서 로그인되있을시 이름을 변경해주어야한다.
		((UserVo)session.getAttribute("authUser")).setName(userVo.getName());
		
		
		return "redirect:/main";
	}
	
	
	//로그아웃
	@RequestMapping(value = "user/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("UserController.logout()");
		
		//http 세션에 관한 내용들은 서비스 구간이상으로 넘어가면 안된다!
		//회원정보 삭제 & 메모리삭제
		session.removeAttribute("authUser");
		session.invalidate();
		
		
		return "redirect:/main";
	}
	
}
