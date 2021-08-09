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
	
	
	//회원정보수정폼
	public UserVo getUser(int no) {
		System.out.println("[UserService.modifyGetUser()]");
		System.out.println(no);
		
		// 3.컨트롤러에서 넘어온 userVo(no)값을 다오구간으로 토스!
		UserVo userVo = userDao.userInfo(no);
		
		
		// 6.다오에서 리턴해준 userVo 확인후 리턴해준다!
		System.out.println("서비스구간: " + userVo);
		
		return userVo;
	}
	
	
	//회원정보 수정
	public int userModify(UserVo userVo) {
		System.out.println("[UserService.modify()]");
		
		//2. 컨트롤러에서 넘어온 userVo 값 다오구간으로 토스!
		int count = userDao.modify(userVo);
		
		
		//5. 다오에서 리턴해온 count 또 리턴!
		return count;
	}
	
	
	//회원가입폼 id 중복체크
	public boolean getUser(String id) {
		System.out.println("[UserService.getUser()]");
		
		UserVo userVo = userDao.selectUser(id);

		if(userVo == null) {	//db에 없는 경우 --> 사용가능한 아이디
			return true;
		} else {				//db에 있는 경우 --> 이미 사용중인 아이디입니다.
			return false;
		}
		
	}
}
