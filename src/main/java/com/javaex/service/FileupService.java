package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileupService {

	
	//파일 업로드 처리
	public String restore(MultipartFile file) {
		System.out.println("FileupController.restore()");
		
//		System.out.println(file.getOriginalFilename());
//		System.out.println(file.getSize());
		
		String saveDir = "C:\\javaStudy\\upload\\";
		
		
		//원파일이름
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println(exName);
		
		
		//저장파일이름(관리때문에 겹치지 않는 새이름 부여)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println(saveName);

		
		//파일패스
		String filePath = saveDir + saveName;
		System.out.println(filePath);
		
		//파일사이즈
		long fileSize = file.getSize();  	//getSize()가 long 형이기때문에 앞에 int말고 long으로 해야함
		System.out.println("fileSize: " + fileSize);
		
		
		//파일을 서버의 하드디스크에 저장
		try {
			byte[] fileDate = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);		//위치와 이름까지 저장해줘야하기때문에 파일패스꺼 써야한다.
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileDate);
			bout.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2.파일정보를 db에 저장
		
		return saveName;
		
	}
	
	
}
