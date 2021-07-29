package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;

@Repository
public class GuestBookDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	//방명록 & 등록폼 가져오기
	public List<GuestBookVo> guestList() {
		System.out.println("GuestBookDao.List<GuestBookVo>");
		
		//3. 세션통해 xml까지
		List<GuestBookVo> guestList = sqlSession.selectList("guestBook.guestList");
		
		System.out.println("게북다오구간: " + guestList);
		System.out.println(guestList);
		
		return guestList;
	}
}
