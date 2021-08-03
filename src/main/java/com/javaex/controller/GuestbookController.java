package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
public class GuestbookController {
	
	@Autowired
	private GuestBookService guestBookService;
	
	
	//방명록 & 등록폼 가져오기
	@RequestMapping(value = "/guestbook/addList", method = {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("gBookController.addList()");
		
		//1. 서비스로 리스트 요청
		List<GuestBookVo> guestList = guestBookService.getGuestList();
		
		model.addAttribute("guestList", guestList);
		
		return "guestbook/addList";
	}
	
	
	//방명록 등록
	@RequestMapping(value = "/guestbook/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("gBookController.add()");
		
		//1. 정보담아서 서비스구간 토스
		guestBookService.guestAdd(guestBookVo);
		
		
		return "redirect:/guestbook/addList";
	}
	
	
	//삭제폼
	@RequestMapping(value = "guestbook/deleteForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() {
		
		return "guestbook/deleteForm";
	}
	
	
	//삭제
	@RequestMapping(value = "guestbook/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("gBookController.delete()");
		
		//1. 서비스토스
		int count = guestBookService.guestDelete(guestBookVo);
		
		
		if(count == 1) {
			return "redirect:/guestbook/addList";
		} else {
			System.out.println("삭제실패");
			//return "redirect:/guestbook/addList";
			return "redirect:/guestbook/deleteForm?result=fail&no=" + guestBookVo.getNo();
		}
		
	}
	
	
	// ajax 방명록 메인페이지
	@RequestMapping(value = "guestbook/ajaxMain", method = {RequestMethod.GET, RequestMethod.POST})
	public String ajaxMain() {
		System.out.println("gBookController.ajaxMain()");
		
		
		return "guestbook/ajaxList";
	}
}
