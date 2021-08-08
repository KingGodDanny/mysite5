package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;
	
	//파일 업로드 처리
	public int restore(MultipartFile file,  GalleryVo galleryVo) {
		System.out.println("GalleryService.restore()");
		
		//파일 업로드 처리
		String saveDir = "C:\\javaStudy\\upload\\";
		
		//원래 파일 이름
		String orgName = file.getOriginalFilename();
		galleryVo.setOrgName(orgName);
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		
		//저장파일이름(관리 때문에 겹치지 않는 새이름을 부여해야한다.)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		galleryVo.setSaveName(saveName);
		
		//파일패스(경로)
		String filePath = saveDir + saveName;
		galleryVo.setFilePath(filePath);

		//파일사이즈
		long fileSize = file.getSize();
		galleryVo.setFileSize(fileSize);
		
		//파일을 서버의 하드디스크에 저장
		try {
			byte[] fileDate = file.getBytes();
			OutputStream out = new FileOutputStream(filePath); //위치와 파일이름이 함께있는 filePath를써줘야한다.
			BufferedOutputStream bout = new BufferedOutputStream(out);
		
			bout.write(fileDate);
			bout.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//DB저장위해 다오로 보내기
		int count = galleryDao.galleryUpload(galleryVo);
		
		System.out.println("여긴 서비스 xml갔다온 보다: " + galleryVo);
		
		return count;
	}
	
	
	//갤러리 리스트 가져오기
	public List<GalleryVo> galleryList() {
		System.out.println("GalleryService.List<GalleryVo>");
		
		//2.다오로 요청
		List<GalleryVo> galleryList = galleryDao.gallerySelect();
		
		
		return galleryList;
	}
	
	
	//하나의 갤러리 이미지 가져오기
	public GalleryVo oneGallery(int no) {
		System.out.println("GalleryService.oneGallery");
		
		//다오로 전송
		GalleryVo galleryVo = galleryDao.oneGallery(no);
		
		return galleryVo;
	}
	
	
	//한개 갤러리 삭제
	public int imgDelete(int no) {
		System.out.println("GalleryService.imgDelete");
		System.out.println("삭제서비스 no: " + no);
		
		int count = galleryDao.imgDelete(no);
		
		return count;
	}
	
	
}
