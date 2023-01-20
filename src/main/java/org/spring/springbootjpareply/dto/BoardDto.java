package org.spring.springbootjpareply.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.springbootjpareply.entity.BoardEntity;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private int hit=0;
    private String boardPw;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


    //Entity -> Dto
    public static BoardDto togetboardDto(BoardEntity boardEntity){
        BoardDto boardDto = new BoardDto();

        boardDto.setId(boardEntity.getId());
        boardDto.setTitle(boardEntity.getTitle());
        boardDto.setContent(boardEntity.getContent());
        boardDto.setWriter(boardEntity.getWriter());
        boardDto.setHit(boardEntity.getHit());
        boardDto.setBoardPw(boardEntity.getBoardPw());
        boardDto.setCreateTime(boardEntity.getCreateTime());
        boardDto.setUpdateTime(boardEntity.getUpdateTime());

        return boardDto;
    }

}
