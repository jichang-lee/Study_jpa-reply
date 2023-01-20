package org.spring.springbootjpareply.service;

import lombok.RequiredArgsConstructor;
import org.spring.springbootjpareply.dto.BoardDto;
import org.spring.springbootjpareply.dto.ReplyDto;
import org.spring.springbootjpareply.entity.BoardEntity;
import org.spring.springbootjpareply.entity.ReplyEntity;
import org.spring.springbootjpareply.repository.BoardRepository;
import org.spring.springbootjpareply.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {


    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private BoardRepository boardRepository;




    public Long insertReply(ReplyDto replyDto) {

        //board 테이블에 게시글 id가 있는지
        Optional<BoardEntity> optionalreplyEntity =
                boardRepository.findById(replyDto.getBoardId());

        if(optionalreplyEntity.isPresent()){
            //boardEntity -> boardDto
            BoardEntity boardEntity=optionalreplyEntity.get();
            //replyDto -> replyEntity
            ReplyEntity replyEntity =
            ReplyEntity.toInsertEntity(replyDto,boardEntity);
            //작성자,내용,글번호에 해당하는 게시글
            return replyRepository.save(replyEntity).getId();
        }else{
            return  null;
        }
    }


    public List<ReplyDto> replyList(Long boardId) {

        BoardEntity boardEntity = boardRepository.findById(boardId).get();

        List<ReplyEntity> replyEntities =
                replyRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);

        List<ReplyDto> replyDtoList= new ArrayList<>();

        for(ReplyEntity replyEntity : replyEntities){
            ReplyDto replyDto = ReplyDto.toReplyDto(replyEntity,boardId);
            replyDtoList.add(replyDto);
            }

    return replyDtoList;
    }

}
