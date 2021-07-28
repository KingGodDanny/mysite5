package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	//필드
	@Autowired
	private SqlSession sqlSession;
	//생성자
	//메소드(게터세터)
	//메소드(일반)
	
	
	public UserVo selectUser(UserVo userVo) {
		System.out.println("[UserDao.selectUser()]");
	
		//이렇게 심플하게 쓸수도있다.
		//return sqlSession.selectOne("user.selectUser", userVo);
	
		UserVo authUser = sqlSession.selectOne("user.selectUser", userVo);
		return authUser;
	}
	
	
	//회원가입
	public int userInsert(UserVo userVo) {
		System.out.println("[UserDao.userInsert()]");
		
		int count = sqlSession.insert("user.userInsert", userVo);
		
		return count;
	}
	
	
	//회원정보수정
	public UserVo userInfo(int no) {
		System.out.println("[UserDao.userInfo()]");
		System.out.println("서비스에서 토스된 회원번호: " + no);
		
		// 4. sql세션으로 요청하여 xml 전달!
		UserVo userVo = sqlSession.selectOne("user.userInfo", no);
		
		
		// 5. (sqlSession) 통해 xml에서 넘어온 한명유저정보 확인한후에
		//return해준다!
		
		System.out.println("다오구간: " + userVo);
		
		return userVo;
	}
	
	
	//정보수정
	public int modify(UserVo userVo) {
		System.out.println("[UserDao.modify()]");
		
		// 3. sql세션으로 요청하여 xml 전달!
		int count = sqlSession.update("user.modify", userVo);
		
		
		// 4. xml에서 업뎃된 정보 count로 넘기기
		return count;
	}
	
}
