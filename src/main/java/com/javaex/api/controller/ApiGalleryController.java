package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping(value = "/api/gallery/")
public class ApiGalleryController {

	@Autowired
	private GalleryService galleryService;
	
	
	//하나의 이미지 가져오기
	@ResponseBody
	@RequestMapping(value = "imgOne", method = {RequestMethod.GET, RequestMethod.POST})
	public GalleryVo imgOne(@RequestParam("no") int no) {
		System.out.println("api컨트롤러 no: " + no);
		
		GalleryVo galleryVo = galleryService.oneGallery(no);
		
		
		return galleryVo;
	}
	
	
	//하나의 갤러리 삭제하기
	@ResponseBody
	@RequestMapping(value = "imgDelete", method = {RequestMethod.GET, RequestMethod.POST})
	public int imgDelete(@RequestParam("no") int no) {
		System.out.println("ApiGalleryController.imgDelete()");
		System.out.println("삭제받는 no:" + no);
		
		int count = galleryService.imgDelete(no);
		System.out.println("API카운트: " + count);
		
		return count;
		
	}
}
