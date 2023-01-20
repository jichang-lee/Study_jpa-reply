package org.spring.springbootjpareply.controller;

import lombok.Getter;
import org.spring.springbootjpareply.dto.BoardDto;
import org.spring.springbootjpareply.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    //메인 페이지 html ~>
    @GetMapping("/")
    public String boardMain(){
        return "index";
    }

    //게시글 작성 html ~>
    @GetMapping("/write")
    public String writeView(){
        return "boardWrite";
    }

    //게시글 작성
    @PostMapping("/write")
    public String writeOk(@ModelAttribute BoardDto boardDto){
        boardService.boardWrite(boardDto);
        return "redirect:boardList";
    }

    //list view ~>
    @GetMapping("/boardList")
    public String boardList(Model model){
        List<BoardDto> boardList = boardService.boardList();
        //DB 데이터를 갖고 -> View ( boardList.html )
        model.addAttribute("boardList",boardList);

        return "boardList";
    }

    //상세조회 view ~>
    @GetMapping("detail/{id}")
    public String boardDetail(@PathVariable Long id ,Model model){

        boardService.upHit(id);

        BoardDto boardDetail = boardService.listDetail(id);

        model.addAttribute("list",boardDetail);
        return "boardDetail";
    }

    //모델 객체 가지고 수정 view ~>
    @GetMapping("boardUpdate/{id}")
    public String updateView(@PathVariable Long id, Model model){
        BoardDto boardDto = boardService.boardUpdate(id);
        model.addAttribute("list",boardDto);

        return "boardUpdate";
    }

    //Update Query
    @PostMapping("boardUpdate")
    public String updateOk(@ModelAttribute BoardDto boardDto){
        boardService.updateOk(boardDto);
            return "redirect:boardList";

    }
    //페이징
    @GetMapping("pagingList")
    public String pagingList(Model model , @PageableDefault
            (page = 0,size = 2,sort = "id",direction = Sort.Direction.DESC)
                             Pageable pageable){
        Page<BoardDto> dtoPage = boardService.listPage(pageable);
        int blockNum = 4;
        int nowPage = dtoPage.getNumber()+1;
        int startPage = Math.max(1,dtoPage.getNumber()-blockNum);
        int endPage = dtoPage.getTotalPages();

        model.addAttribute("dtoPage",dtoPage);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        return "boardListPage";
    }



}
