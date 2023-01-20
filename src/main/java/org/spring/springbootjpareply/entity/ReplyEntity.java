package org.spring.springbootjpareply.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.springbootjpareply.dto.ReplyDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "jpa_reply_tb")
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @Column(nullable = false)
    private String replyWriter;

    @Column(nullable = false)
    private String replyContent;

    //별도로 공통 entity 만들어서 extends
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateTime;

    //외래키 있는 테이블이 many
    @ManyToOne
    //Entity에 PK명을 참조한다
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    public static ReplyEntity toInsertEntity(ReplyDto replyDto,BoardEntity boardEntity) {
    ReplyEntity replyEntity = new ReplyEntity();

    replyEntity.setReplyWriter(replyDto.getReplyWriter());
    replyEntity.setReplyContent(replyDto.getReplyContent());
    replyEntity.setBoardEntity(boardEntity);
    return replyEntity;

    }
}
