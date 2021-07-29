package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	
	//하나의 게시글 + 조회수 증가
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
	
	
	//전체 리스트 + 검색
	public List<BoardVo> getList(String keyword) {
		System.out.println("BoardService.List<BoardVo>");
		
		//2. 컨트롤러에서 넘어온 keyword or ""를 담아서 다오로 토스해준다.
		List<BoardVo> boardList = boardDao.boardList(keyword);
		System.out.println("서비스구간: " + boardList);
		
		//5. 다오에서 넘어온 리턴값 리턴!
		return boardList;
	}
	
	
	//글쓰기
	public int write(BoardVo boardVo) {
		System.out.println("BoardService.write()");

		//2. 컨트롤러 > boardVo 담아서 다오로 토스!
		int count = boardDao.boardWrite(boardVo);
		
		//5. 다오에서 리턴해온 count 또 리턴!
		return count;

	}
	
	
	// 삭제
	public int delete(int no) {
		System.out.println("BoardService.delete()");
		
		//2. 다오로 토스!
		int count = boardDao.boardDelete(no);
		
		//5. 다오에서 리턴해온 count 또 리턴!
		return count;
	}
	
	
	//수정폼을 위한 게시물1개 가져오기
	public BoardVo boardModify(int no) {
		System.out.println("BoardService.boardModify()");
		
		//2. 컨트롤러에서 넘어온 no 다오로 토스!
		//이미 한명읽기정보를 위한 메소드가 다오와 xml에 있기때문에
		//같이 사용하자
		BoardVo boardVo = boardDao.selectBoard(no);
		
		return boardVo;
	}
	
	
	//게시물 수정
	public int modify(BoardVo boardVo) {
		System.out.println("BoardService.modify()");

		//2. 컨트롤러에서 넘어온 boardVo 토스
		int count = boardDao.boardModify(boardVo);
		
		//5.확인후 리턴!
		return count;
	}
}
