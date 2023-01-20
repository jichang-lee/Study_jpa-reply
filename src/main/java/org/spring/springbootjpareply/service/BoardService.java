package org.spring.springbootjpareply.service;

import lombok.RequiredArgsConstructor;
import org.spring.springbootjpareply.dto.BoardDto;
import org.spring.springbootjpareply.entity.BoardEntity;
import org.spring.springbootjpareply.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //요즘 쓰는 di 주입 -> class에 required생성자 어노테션 선언
//    private final BoardRepository boardRepository;

//    게시글 작성
    @Transactional
    public void boardWrite(BoardDto boardDto){
        BoardEntity boardEntity = BoardEntity.togetboardEntity(boardDto);
        boardRepository.save(boardEntity);
    }

//    게시글 출력
        public List<BoardDto> boardList(){
            List<BoardDto> boardDtoList = new ArrayList<>();
            List<BoardEntity> boardEntityList = boardRepository.findAll();

            for(BoardEntity lists : boardEntityList){
                boardDtoList.add(BoardDto.togetboardDto(lists));
            }
            return boardDtoList;
        }

//     id로 게시글 하나 출력
        public BoardDto listDetail(Long id){
            Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);

            if(optionalBoardEntity.isPresent()){
               return BoardDto.togetboardDto(optionalBoardEntity.get());
            }else{
                System.out.println("null");
                return null;
            }
        }
    @Transactional
    public void upHit(Long id) {
        boardRepository.upHit(id);
    }

//    pageList
    public Page<BoardDto> listPage(Pageable pageable) {
        Page<BoardEntity> boardEntity = boardRepository.findAll(pageable);
        Page<BoardDto> dtoPage = boardEntity.map(BoardDto::togetboardDto);

        return dtoPage;
    }

    //update id select
    public BoardDto boardUpdate(Long id) {
    Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
    return BoardDto.togetboardDto(optionalBoardEntity.get());
    }


    public void updateOk(BoardDto boardDto) {
        BoardEntity boardEntity=BoardEntity.togetboardEntity(boardDto);
        boardRepository.save(boardEntity);
    }
}
