package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	//갤러리리스트 전체출력
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("[GalleryController.list]");

		//1. 서비스 리스트 요청
		List<GalleryVo> galleryList = galleryService.galleryList();
		
		model.addAttribute("galleryList", galleryList);
		
		
		return "/gallery/list";
	}
	
	
	//이미지 업로드하기
	@RequestMapping(value = "upload", method = {RequestMethod.GET, RequestMethod.POST})
	public String upload(@RequestParam("file") MultipartFile file,
						@ModelAttribute GalleryVo galleryVo,
						HttpSession session
						) {
		
		System.out.println("[GalleryController.upload]");
		
		int no = ((UserVo) session.getAttribute("authUser")).getNo();
		galleryVo.setUser_no(no);
		
		galleryService.restore(file, galleryVo);
		
		return "redirect:/gallery/list";
	}
	
	
}
