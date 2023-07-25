package com.study.board.repository;


import com.study.board.entity.Board;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    //BoardRepository에 오늘 받은 편지를 일치하는 날짜 범위로 검색하고 개수를 반환하는 메소드를 추가
    Long countByDateBetween(LocalDateTime start, LocalDateTime end);

    List<Board> findAllByDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay, Sort date);
}
