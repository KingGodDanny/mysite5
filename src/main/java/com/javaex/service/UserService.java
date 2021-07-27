package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	
	//로그인 사용자 정보 가져오기
	public UserVo getUser(UserVo userVo) {
		System.out.println("[UserService.getUser()]");

		UserVo authUser = userDao.selectUser(userVo);
		
		return authUser;
	}
	
	
	//회원가입하기
	public int join(UserVo userVo) {
		System.out.println("[UserService.joinUser()]");
		
		System.out.println(userVo);
		
		int count = userDao.userInsert(userVo);
		
		return count;
	}
	
}
