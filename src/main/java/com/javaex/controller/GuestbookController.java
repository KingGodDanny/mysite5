package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String deleteForm(Model model, @RequestParam("no") int no) {
		
		model.addAttribute("no", no);
		
		return "guestbook/deleteForm";
	}
}
