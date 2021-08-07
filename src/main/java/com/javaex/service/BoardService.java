package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	
	//게시판 페이징 연습용 리스트
	public Map<String, Object> getList2(int crtPage, String keyword) {
		System.out.println("BoardService.getList2()");
		System.out.println("서비스다옛다: " + crtPage);
		
		//////////////////////////////////////////////
		// 리스트 가져오기
		//////////////////////////////////////////////
		int listCnt = 10;	//한페이징에 10개씩 출력하라는뜻
		
		//crtPage 계산(- 값일때 1page 처리)
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1); //삼항연산자; 아래랑 같은 논리
		
//		if(crtPage > 0) {
//			//crtPage = crtPage;
//		} else {
//			crtPage = 1;
//		}
		
		
		//시작번호 계산하기
		int startRnum = (crtPage-1) * listCnt + 1;
				
		//끝번호 계산기하기
		int endRnum = (startRnum + listCnt) - 1;	//int endRnum = crtPage * listCnt; 이것도 사용가능
		
		
		List<BoardVo> boardList = boardDao.selectList2(startRnum, endRnum, keyword);	//()안에 시작번호 끝번호를 넘겨줘야함
		
		
		//////////////////////////////////////////////
		// 페이징 계산하기
		//////////////////////////////////////////////
		
		//전체글갯수
		int totalCount = boardDao.selectTotalCnt(keyword);
		System.out.println("서비스토탈: " + totalCount);
		
		
		//페이지당 버튼갯수
		int pageBtnCount = 5;
		
		
		//마지막 버튼 번호
		//1~5페이지 -> 1~5 페이징
		//6~10페이지 -> 6~10페이징
		int endPageBtnNo = (int)Math.ceil((crtPage/(double)pageBtnCount))* pageBtnCount;
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount-1);
		
		//다음 화살표 펴현 유무
		boolean next = false;
		if(endPageBtnNo * listCnt < totalCount) {
			next = true;
		} else {
			//	다음 화살표 버튼이 없을때 endPageBtnNo 를 다시 계산해야한다.
			//	전체 글의 갯수/한페이지의 글갯수(10)
			//	127  /  10	= 12.7(자바에선 12로나옴)		--> 12.1여도 13페이지까지 나와야하기때문에 올림처리함
			
			endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
							// 127	/ 10.0  = 12.7	-->올림	-> 13.0 --> 13
		}
		
		
		
		//이전 화살표 표현 유무
		boolean prev = false;
		if(startPageBtnNo !=1) {
			prev = true;
		}
		
		
		//////////////////////////////////////////////
		// Map으로 리턴하기
		//////////////////////////////////////////////
		
		
		//리턴하기
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("boardList", boardList);
		listMap.put("prev", prev);
		listMap.put("startPageBtnNo", startPageBtnNo);
		listMap.put("endPageBtnNo", endPageBtnNo);
		listMap.put("next", next);
		
		
		return listMap;
	}
	
	
	// 하나의 게시글 + 조회수 증가
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
	
	
	// 전체 리스트 + 검색
	public List<BoardVo> getList(String keyword) {
		System.out.println("BoardService.List<BoardVo>");
		
		//2. 컨트롤러에서 넘어온 keyword or ""를 담아서 다오로 토스해준다.
		List<BoardVo> boardList = boardDao.boardList(keyword);
		System.out.println("서비스구간: " + boardList);
		
		//5. 다오에서 넘어온 리턴값 리턴!
		return boardList;
	}
	
	
	// 글쓰기
	public int write(BoardVo boardVo) {
		System.out.println("BoardService.write()");

		
		for(int i=0; i<127; i++) {
			boardVo.setTitle(i + " 번째 제목입니다.");
			boardVo.setContent(i + " 번째 내용입니다.");
			boardDao.boardWrite(boardVo);
		}
		
		return 1;
		
//		//2. 컨트롤러 > boardVo 담아서 다오로 토스!
//		int count = boardDao.boardWrite(boardVo);
//		
//		//5. 다오에서 리턴해온 count 또 리턴!
//		return count;
		
		

	}
	
	
	// 삭제
	public int delete(int no) {
		System.out.println("BoardService.delete()");
		
		//2. 다오로 토스!
		int count = boardDao.boardDelete(no);
		
		//5. 다오에서 리턴해온 count 또 리턴!
		return count;
	}
	
	
	// 수정폼을 위한 게시물1개 가져오기
	public BoardVo boardModify(int no) {
		System.out.println("BoardService.boardModify()");
		
		//2. 컨트롤러에서 넘어온 no 다오로 토스!
		//이미 한명읽기정보를 위한 메소드가 다오와 xml에 있기때문에
		//같이 사용하자
		BoardVo boardVo = boardDao.selectBoard(no);
		
		return boardVo;
	}
	
	
	// 게시물 수정
	public int modify(BoardVo boardVo) {
		System.out.println("BoardService.modify()");

		//2. 컨트롤러에서 넘어온 boardVo 토스
		int count = boardDao.boardModify(boardVo);
		
		//5.확인후 리턴!
		return count;
	}
}
