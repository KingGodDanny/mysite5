package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping(value = "/api/guestbook/")
public class ApiGuestbookController {
	
	@Autowired
	private GuestBookService guestBookService;

	//ajax 리스트 가져오기
	@ResponseBody
	@RequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
	public List<GuestBookVo> list() {
		System.out.println("ApiGuestbookController.list()");
		
		List<GuestBookVo> guestbookList = guestBookService.getGuestList();
		System.out.println("API" + guestbookList);
		
		return guestbookList;	//포워드 다이렉트가아니고 게북리스트 데이터를 위에 리스폰스바디에 데이터만 보낸다.
	}
	
	
	//ajax 방명록 저장
	@ResponseBody				//기존 포워드 방식으로 하지말고 밑에 resultVo같은 데이터를 리스폰스바디에 보내주라는 뜻이다.
	@RequestMapping(value = "write", method = {RequestMethod.GET, RequestMethod.POST})
	public GuestBookVo write(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("ApiGuestbookController.write()");
		
		GuestBookVo resultVo = guestBookService.writeResultVo(guestBookVo);
		
		System.out.println("컨트롤러제이슨: " + resultVo);
		
		return resultVo;	//제이슨으로 넘겨주는 방법임	리스폰스body에 넣어서 보내라
	}
	
	
	//ajax 방명록 삭제
	@ResponseBody
	@RequestMapping(value = "remove", method = {RequestMethod.GET, RequestMethod.POST})
	public int remove(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("ApiGuestbookController.remove()");
		
		int count = guestBookService.guestDelete(guestBookVo);
		System.out.println(count);
		
		return count;
	}
	
	
	
	
}
