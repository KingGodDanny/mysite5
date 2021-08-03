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
		
		//System.out.println(guestList);
		
		return guestList;
	}
	
	
	//방명록 등록
	public int guestInsert(GuestBookVo guestBookVo) {
		System.out.println("GuestBookDao.guestInsert");
		
		//3. spl > xml
		int count = sqlSession.insert("guestBook.guestInsert", guestBookVo);
		
		System.out.println("다오구간등록: " + guestBookVo);
		
		//4.리턴
		return count;
	}
	
	
	//삭제
	public int delete(GuestBookVo guestBookVo) {
		System.out.println("GuestBookDao.delete");
		
		//3. sql > xml
		int count = sqlSession.delete("guestBook.delete", guestBookVo);
		System.out.println("다오구간 :" + guestBookVo);
		
		
		return count;
	}
	
	
	//방명록 글 저장  -  ajax
	public int insertGuestbookKey(GuestBookVo guestBookVo) {
		System.out.println("[guestbookDao.insertGuestbookKey]");
	    
		System.out.println(guestBookVo);
		
	      return sqlSession.insert("guestBook.insertGuestbookKey", guestBookVo);
	}
	
	
	//방명록 글 가져오기  - ajax
	public GuestBookVo selectGuestbook(int no) {
		System.out.println("[guestbookDao.selectGuestbook]");
		
		return sqlSession.selectOne("guestBook.selectGuestbook", no);
	}
	
	
}
