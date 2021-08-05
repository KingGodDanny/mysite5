package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileupService;

@Controller
@RequestMapping(value = "/fileup")
public class FileupController {
	
	@Autowired
	private FileupService fileupService;
	

	//파일폼 업로드 화면
	@RequestMapping(value = "/form", method = {RequestMethod.GET, RequestMethod.POST})
	public String form() {
		System.out.println("FileupController.form()");
		
		return "/fileup/form";
	}
	
	
	//파일 업로드
	@RequestMapping(value = "/upload", method = {RequestMethod.GET, RequestMethod.POST})
	public String upload(Model model, @RequestParam("file") MultipartFile file) {
		System.out.println("FileupController.upload()");
		
		System.out.println(file.getOriginalFilename());		//실제파일명
		System.out.println(file.getSize());					//파일사이즈
		
		String saveName = fileupService.restore(file);
		
		model.addAttribute("saveName", saveName);
		
		
		return "/fileup/result";
	}
	
	
	
	
}
