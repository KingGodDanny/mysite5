package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/board/read", method = {RequestMethod.GET, RequestMethod.POST})
	public String read(Model model, @RequestParam("no") int no) {
		System.out.println("BoardController.read()");
		
		//1. 사용자가 요청한 제목의 no를 받는다.
		//그리고 서비스로 토스한다.
		
		BoardVo boardVo = boardService.getBoard(no);
		System.out.println(boardVo);
		
		//6.서비스구간에서 리턴해서 넘어온 boardVo를 어트리뷰트 구간에
		//넣어준후에
		model.addAttribute("boardVo", boardVo);
		
		//7. read.jsp로 리턴해주고 사용자에게 보여준다.
		return "board/read";
	}
	
}
