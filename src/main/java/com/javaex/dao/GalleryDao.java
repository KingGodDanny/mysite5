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
	
}
