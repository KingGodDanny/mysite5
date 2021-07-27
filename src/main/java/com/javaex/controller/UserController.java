package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
}
