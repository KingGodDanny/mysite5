package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestBookDao guestBookDao;
	
	
	////방명록 & 등록폼 가져오기
	public List<GuestBookVo> getGuestList() {
		System.out.println("GuestBookService.List<GuestBookVo>");
		
		//다오로 요청!
		List<GuestBookVo> guestList = guestBookDao.guestList();
		
		return guestList;
	}
}
