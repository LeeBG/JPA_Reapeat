package com.itbank.simpleboard.controller;

import com.itbank.simpleboard.domain.Board;
import com.itbank.simpleboard.domain.BoardDto;
import com.itbank.simpleboard.domain.Member;
import com.itbank.simpleboard.service.BoardService;
import com.itbank.simpleboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private final BoardService boardService;

    @Autowired
    private final MemberService memberService;


    @GetMapping("/list")
    public ModelAndView findAll() {
        ModelAndView mav = new ModelAndView();
        List<BoardDto> boardDto = boardService.findAll();
        mav.addObject("list", boardDto);

        return mav;
    }

    @GetMapping("/write")
    public String write(HttpSession session) {
        Member member = (Member) session.getAttribute("login");
        if(member == null) {
            return "redirect:/member/login";
        }
        return "/board/write";
    }


    @PostMapping("/write")
    public String writePro(BoardDto dto) { // 게시글 작성하기.

        Member member = memberService.findById(dto.getMemberId());
        dto.setWriter(member);

        // 추가된 게시글
        Board savedBoard = boardService.save(dto);

        // 게시글 추가에 실패 했을 때
        if(savedBoard == null) {
            return "redirect:/board/write";
        }

        return "redirect:/board/list";
    }

    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable("id") Long id) {

        ModelAndView mav = new ModelAndView("/board/view");

        Board board  = boardService.findById(id);
        mav.addObject("dto", board);
        return mav;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.deleteById(id);
        return "redirect:/board/list";
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("/board/update");
        Board board = boardService.findById(id);
        mav.addObject("dto", board);

        return mav;
    }

    @PostMapping("/update/{id}")
    public String updatePro(@PathVariable("id") Long id, Board board) {
        board.setId(id);
        Board updatedBoard = boardService.update(board);

        if(updatedBoard == null) {
            System.out.println("수정 실패");
        }

        return "redirect:/board/view/"+id;
    }


}
