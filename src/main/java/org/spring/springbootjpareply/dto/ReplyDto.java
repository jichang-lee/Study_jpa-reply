package org.spring.springbootjpareply.dto;

import lombok.*;
import org.spring.springbootjpareply.entity.BoardEntity;
import org.spring.springbootjpareply.entity.ReplyEntity;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReplyDto {

    private Long id;
    private String replyWriter;
    private String replyContent;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long boardId; //boardEntity board_id 에 참조하는 FK

    public static ReplyDto toReplyDto(ReplyEntity replyEntity, Long boardId) {
        ReplyDto replyDto = new ReplyDto();
        replyDto.setId(replyEntity.getId());
        replyDto.setReplyWriter(replyEntity.getReplyWriter());
        replyDto.setReplyContent(replyEntity.getReplyContent());
        replyDto.setCreateTime(replyEntity.getCreateTime());
        replyDto.setUpdateTime(replyEntity.getUpdateTime());
        replyDto.setBoardId(boardId);
        return replyDto;
    }
}
