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
	
	
	public int userInsert(UserVo userVo) {
		System.out.println("[UserDao.userInsert()]");
		
		int count = sqlSession.insert("user.userInsert", userVo);
		
		return count;
	}
}
