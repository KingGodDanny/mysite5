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
	
	
	//방명록 & 등록폼 가져오기
	public List<GuestBookVo> getGuestList() {
		System.out.println("GuestBookService.List<GuestBookVo>");
		
		//다오로 요청!
		List<GuestBookVo> guestList = guestBookDao.guestList();
		
		return guestList;
	}

	
	//방명록 등록
	public int guestAdd(GuestBookVo guestBookVo) {
		System.out.println("GuestBookService.add()");
				
		//2. 다오로 토스
		int count = guestBookDao.guestInsert(guestBookVo);
				
		//5.리턴
		return count;
	}
	
	
	//삭제
	public int guestDelete(GuestBookVo guestBookVo) {
		System.out.println("GuestBookService.guestDelete()");
		
		//2.다오 토스
		int count = guestBookDao.delete(guestBookVo);
		
		return count;
	}
	
	
	//방명록 글 저장 & 게시글 가져오기
	public GuestBookVo writeResultVo(GuestBookVo guestBookVo) {
		System.out.println("GuestBookService.writeResultVo()");
		
		//글저장
		System.out.println("저장 전: " + guestBookVo);	//no 없다
		int count = guestBookDao.insertGuestbookKey(guestBookVo);
		System.out.println("저장 후: " + guestBookVo);	//no 있다.
		
		int no = guestBookVo.getNo(); //방금 저장한 글번호
		
		//글가져오기(방금등록한 번호)
		GuestBookVo resultVo = guestBookDao.selectGuestbook(no);
		
		System.out.println("서비스:" + resultVo);
		return resultVo;
	}
	
	
	//no값으로 no의 모든정보 가져오기
	public GuestBookVo readGuest(int no) {
		System.out.println("GuestBookService.readGuest()");
		
		GuestBookVo guestBookVo = guestBookDao.selectGuestbook(no);
		
		return guestBookVo;
	}
	
	
}