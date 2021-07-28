package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession; 
	
	//3. 서비스구간에서 토스받아 넘어온 업데이트와 1개정보셀렉트를 각각 다른
	//   메소드로 xml에 요청해야한다. public 다음에 써야하는 자료형과 리턴값 공부하자
	
	//조회수 올리기
	public int updateHit(int no) {
		System.out.println("BoardDao.updateHit()");
		
		int count = sqlSession.update("board.updateHit", no);
		
		return count;
		
		//return sqlSession.update("board.updateHit", no); 이렇게 쓰는방법 익숙해지기
	}
	
	
	//게시판1개 정보 가져오기
	public BoardVo selectBoard(int no) {
		System.out.println("BoardDao.selectBoard()");
		System.out.println(no);
		
		BoardVo boardVo = sqlSession.selectOne("board.selectBoard", no);
		System.out.println(boardVo);
		
		return boardVo;
	}
	
	//4. 그다음 xml 쿼리문에서 넘어오는 정보들을 각각의 메소드에서 return 해준다.
}
