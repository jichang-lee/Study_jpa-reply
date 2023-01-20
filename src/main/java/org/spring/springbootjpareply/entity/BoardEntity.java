package org.spring.springbootjpareply.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.springbootjpareply.dto.BoardDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jpa_board_tb")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false,length = 500)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(columnDefinition = "integer default 0",nullable = false)
    private int hit;

    @Column(nullable = false)
    private String boardPw;

    @CreationTimestamp //처음 글 작성시 자동으로 시간 설정
    @Column(updatable = false) //업데이트시 적용 안되게
    private LocalDateTime createTime;

    @UpdateTimestamp // 처음 글 수정 시 자동으로 시간이 설정
    @Column(insertable = false) //작성 시 적용 안되게
    private LocalDateTime updateTime;

    //게시글 조회 시 댓글 목록도 보이게 join
    //mappedBy=replyEntity에 참조키 , 연관관계의 주인 (외래키 설정 테이블)
    //1:N -> 1의 entity 작성
    @OneToMany(mappedBy ="boardEntity",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<ReplyEntity> referReplyList=new ArrayList<>();


    //Dto -> Entity
    public static BoardEntity togetboardEntity(BoardDto boardDto){
        BoardEntity boardEntity = new BoardEntity();
//        boardEntity.setId(boardDto.getId());
        boardEntity.setTitle(boardDto.getTitle());
        boardEntity.setContent(boardDto.getContent());
        boardEntity.setWriter(boardDto.getWriter());
        boardEntity.setHit(boardDto.getHit());
        boardEntity.setBoardPw(boardDto.getBoardPw());

        return boardEntity;
    }
}
