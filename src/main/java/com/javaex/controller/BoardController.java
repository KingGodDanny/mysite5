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

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	//하나의 게시글 읽기
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
	
	
	//전체리스트
	@RequestMapping(value = "/board/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model, @RequestParam(value="keyword", required = false, defaultValue = "") String keyword) {
		System.out.println("BoardController.list()");
		System.out.println(keyword);
		
		//1. 사용자가 요청한 list 목록을 서비스로 토스해준다
		 List<BoardVo> boardList = boardService.getList(keyword);
		    
		//6. jsp로 보내기위해서 리턴해서 넘어온 boardList를 어트리뷰트에 담아줘야한다
		 model.addAttribute("bList", boardList);
		
		return "board/list";
	}
	
	
	// 글쓰기 폼
	@RequestMapping(value = "/board/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String wirteForm() {
		System.out.println("BoardController.writeForm()");

		return "board/writeForm";
	}
	
   
	// 글쓰기
	@RequestMapping(value = "/board/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		System.out.println("BoardController.write()");

		// 1. 로그인한 사용자 구간에서 글쓰기폼을 통해 넘어온 정보들 담아서
		// 서비스구간으로 토스해준다.
		int no = ((UserVo) session.getAttribute("authUser")).getNo();

		// 로그인한 사용자의 no를 userNo에 넣어준다.
		boardVo.setUserNo(no);

		boardService.write(boardVo);

		return "redirect:/board/list";
	}
   
   
	// 삭제
	@RequestMapping(value = "/board/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no) {
		System.out.println("BoardController.delete()");

		//1. no파라미터 서비스로 토스!
		boardService.delete(no);

		
		return "redirect:/board/list";
	}
   
	
	//수정폼
	@RequestMapping(value = "/board/modifyForm", method = { RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(Model model, @RequestParam("no") int no) {
		System.out.println("BoardController.modifyForm()");
		
		//1. no 값 서비스로 토스!
		BoardVo boardVo = boardService.boardModify(no);
	
		
		
		//서비스구간에서 넘어온 한사람 정보 모델어트리뷰트에 저장
		model.addAttribute("boardVo", boardVo);
		
		return "board/modifyForm";
	}
	
	
	//수정
	@RequestMapping(value = "/board/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController.modify()");

		//1. 세가지의 파라미터 서비스로 토스!
		boardService.modify(boardVo);
		
		return "redirect:/board/list";
	}
	
}
