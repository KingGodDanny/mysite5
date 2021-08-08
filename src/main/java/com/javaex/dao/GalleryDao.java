package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	//이미지파일 업로드
	public int galleryUpload(GalleryVo galleryVo) {
		System.out.println("GalleryDao.galleryUpload()");
		
		System.out.println("여기는 다오다: " + galleryVo);
		
		int count = sqlSession.insert("gallery.galleryUpload" , galleryVo);
		
		System.out.println("xml갔다온 보다: " + galleryVo);
		
		return count;
	}
	
	
	//갤러리 리스트 가져오기
	public List<GalleryVo> gallerySelect() {
		System.out.println("GalleryDao.gallerySelect()");
		
		//3. 세션요청해 xml 까지!
		List<GalleryVo> galleryList = sqlSession.selectList("gallery.gallerySelect");
		
		System.out.println(galleryList);
		
		return galleryList;
	}
	
	
	//하나의 갤러리 이미지 가져오기
	public GalleryVo oneGallery(int no) {
		System.out.println("GalleryDao.oneGallery()");
		System.out.println("원갤러리 다오 no: " + no);
		
		GalleryVo galleryVo = sqlSession.selectOne("gallery.oneGallery", no);
		
		System.out.println("xml갔다온 보다: " + galleryVo);
		
		return galleryVo;
	}
	
	
	//한개의 이미지 삭제
	public int imgDelete(int no) {
		System.out.println("GalleryDao.imgDelete()");
		
		int count = sqlSession.delete("gallery.imgDelete", no);
		
		return count;
		
	}
	
	
	
}
