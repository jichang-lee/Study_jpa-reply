package org.spring.springbootjpareply.controller;

import org.spring.springbootjpareply.dto.ReplyDto;
import org.spring.springbootjpareply.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reply")

public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping("/write")
    public String replyWrite(@ModelAttribute ReplyDto replyDto, Model model){
        //boardId 확인 -> 댓글저장
        Long rs = replyService.insertReply(replyDto);
        //댓글 리스트 -> 게시글 id (댓글을 단 게시글의 id) 의 리스트만 get
        List<ReplyDto> replyDtoList = replyService.replyList(replyDto.getBoardId());
        //댓글작성->게시글이 존재하는지 확인-> dto를 entity로 -> 저장

        //상세페이지에 글번호와 갯글리스트를 가지고 같이 보낸다
        model.addAttribute("replyList",replyDtoList);

        return "redirect:/board/detail/"+replyDto.getBoardId();
    }


}
