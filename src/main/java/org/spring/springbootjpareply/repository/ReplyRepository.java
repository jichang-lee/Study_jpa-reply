package org.spring.springbootjpareply.repository;

import org.spring.springbootjpareply.entity.BoardEntity;
import org.spring.springbootjpareply.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity,Long> {


    List<ReplyEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);


}
