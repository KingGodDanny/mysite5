package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	
	public BoardVo getBoard(int no) {
		System.out.println("BoardService.getBoard()");
		System.out.println(no);
		
		//2.컨트롤러에서 넘어온 no값으로 두가지 일을 이곳 서비스구간에서한다
		//  읽으면 히트도 올라가야하기때문에 업뎃과 하나의 게시판정보를
		//  다오로 같이 요청해야한다.
		
		//조회수 올리기
		int count = boardDao.updateHit(no);
		
		//게시판 정보 가져오기
		BoardVo boardVo = boardDao.selectBoard(no);
		
		
		//5.다오에서 리턴해온 boardVo 받아서 컨트롤러로 리턴해준다.
		return boardVo;
	}
	
	
}
