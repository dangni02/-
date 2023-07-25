package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    //게시글 작성
    @GetMapping("/board/write") //localhost:8090/board/write
    public String boardWriteForm() {
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board) {

        boardService.write(board);

        return "redirect:/board/list";
    }

    //게시글 리스트
    @GetMapping("/board/list")
    public String boardList(Model model){
        model.addAttribute("list", boardService.findAll());
        return "boardlist";
    }

    //해당 게시글 보여주기
    @GetMapping("/board/view")//localhost:8090/board/view?id=1
        public String boardView(Model model,@RequestParam("id") Integer id){

        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    //오늘 받은 편지 보여주기
    @GetMapping("/board/today")
    public String boardToday(Model model) {
        LocalDate today = LocalDate.now();
        long receivedLettersCount = boardService.countLettersOnDate(today);
        boolean showMessage = receivedLettersCount < 5;

        model.addAttribute("count", receivedLettersCount);
        model.addAttribute("showMessage", showMessage);
        model.addAttribute("list", boardService.findAllOnDate(today));

        return "boardtoday";
    }

}