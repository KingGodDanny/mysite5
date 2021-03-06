package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession; 
	
	//	전체 게시물 갯수 구하기
	public int selectTotalCnt(String keyword) {
		System.out.println("BoardDao.selectTotalCnt()");
		
		
		return sqlSession.selectOne("board.selectTotalCnt", keyword);
	}
	
	
	//	게시판 페이징 연습용 리스트
	public List<BoardVo> selectList2(int startRnum, int endRnum, String keyword) {
		System.out.println("BoardDao.selectList2()");
		System.out.println(startRnum);
		System.out.println(endRnum);
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("startRnum", startRnum);
		pMap.put("endRnum", endRnum);
		pMap.put("keyword", keyword);
		System.out.println("다오의맵이다 :" + pMap);
		
		
//		List<BoardVo> boardList = sqlSession.selectList("board.selectList2");
//		System.out.println(boardList);
		
		return sqlSession.selectList("board.selectList2" , pMap);
	}
	
	
	
	
	//	3. 서비스구간에서 토스받아 넘어온 업데이트와 1개정보셀렉트를 각각 다른
	//   메소드로 xml에 요청해야한다. public 다음에 써야하는 자료형과 리턴값 공부하자
	
	//조회수 올리기
	public int updateHit(int no) {
		System.out.println("BoardDao.updateHit()");
		
		int count = sqlSession.update("board.updateHit", no);
		
		return count;
		
		//return sqlSession.update("board.updateHit", no); 이렇게 쓰는방법 익숙해지기
	}
	
	
	//	게시판1개 정보 가져오기
	public BoardVo selectBoard(int no) {
		System.out.println("BoardDao.selectBoard()");
		System.out.println(no);
		
		BoardVo boardVo = sqlSession.selectOne("board.selectBoard", no);
		System.out.println(boardVo);
		
		//4. 그다음 xml 쿼리문에서 넘어오는 정보들을 각각의 메소드에서 return 해준다.
		return boardVo;
	}
	
	
	//	리스트 가져오기
	public List<BoardVo> boardList(String keyword) {
		System.out.println("BoardDao.List<BoardVo>");
		System.out.println(keyword);
		
		//3. 서비스에서 토스된 리스트 리시브하고 sqlSession
		List<BoardVo> boardList = sqlSession.selectList("board.boardList", keyword);
		
		System.out.println("다오구간: " + boardList);
		
		//4. xml 에서 넘어온 정보 return!
		return boardList;
	}
	
	
	// 게시판 글쓰기
	public int boardWrite(BoardVo boardVo) {
		System.out.println("BoardDao.boardWrite()");

		//3. xml로 전달!
		int count = sqlSession.insert("board.boardWrite", boardVo);
		
		System.out.println("다오구간: " + boardVo);
		
		//4. 인서트된 정보 count로 리턴
		return count;
		
		//이렇게 간편하게 쓰는방법도 쓸줄알아야함
		//return sqlSession.insert("board.boardWrite", boardVo);

	}
	
	
	// 삭제
	public int boardDelete(int no) {
		System.out.println("BoardDao.boardDelete()");
		
		//3. sql세션 요청후 xml 전달
		int count = sqlSession.delete("board.boardDelete", no);
		
		
		//4. boardDelete 정보 count로 리턴
		return count;
	   }
	
	
	//게시물 수정하기
	public int boardModify(BoardVo boardVo) {
		System.out.println("BoardDao.boardModify()");

		//3. sql세션 요청후 xml 전달
		int count = sqlSession.update("board.boardModify" , boardVo);
		
		System.out.println(boardVo);
		
		//4. 위에 넘어오는거 확인후 리턴!
		return count;
	}
	
}
