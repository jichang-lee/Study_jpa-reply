package org.spring.springbootjpareply.repository;

import org.spring.springbootjpareply.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Long> {

    @Modifying//삭제 , 추가 ,수정
    @Query(value = "update BoardEntity b set b.hit=b.hit+1  where b.id=:id ")
    void upHit(Long id);

}
