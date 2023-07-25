package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository; //원래 객체 처리를 위해서 new를 사용해야하지만 Autowired를 통해서 자동으로 지정 가능하다.

    // 편지 작성 처리
    public void write(Board board)
    {
        boardRepository.save(board);
    }

    //편지 리스트
    public List<Board> findAll() {
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    //특정 편지 불러오기
    public Board boardView(Integer id){

        return boardRepository.findById(id).get();
    }

    public long countLettersOnDate(LocalDate date){
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return boardRepository.countByDateBetween(startOfDay, endOfDay);
    }

    public List<Board> findAllOnDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return boardRepository.findAllByDateBetween(startOfDay, endOfDay, Sort.by(Sort.Direction.DESC, "date"));
    }
}

