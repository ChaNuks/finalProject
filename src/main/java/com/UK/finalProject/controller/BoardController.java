package com.UK.finalProject.controller;

import com.UK.finalProject.dto.BoardDTO;
import com.UK.finalProject.entity.Board;
import com.UK.finalProject.exception.CustomException;
import com.UK.finalProject.exception.ErrorCode;
import com.UK.finalProject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    // 게시물 작성
    @PostMapping("/write")
    public ResponseEntity<String> write(@RequestBody BoardDTO boardDTO) {

        if (boardDTO.getTitle().isEmpty()) {
            throw new CustomException(ErrorCode.EMPTY_TITLE);
        } else if (boardDTO.getContent().isEmpty()) {
            throw new CustomException(ErrorCode.EMPTY_CONTENT);
        } else if (boardDTO.getCategoryId() == null) {
            throw new CustomException(ErrorCode.EMPTY_CATEGORY);
        } else if (boardDTO.getBoardImage().length() > 100) {
            throw new CustomException(ErrorCode.IMAGE_UPLOAD_FAILED);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.write(boardDTO));
    }

    // 게시물 수정
    @PutMapping("/edit/{id}")
    public String updateBoard(@PathVariable("id") long id, @RequestBody BoardDTO boardDTO) {
        return boardService.updateBoard(id, boardDTO);
    }

    // 게시물 삭제
    @DeleteMapping("/board/{id}")
    public String deleteBoard(@PathVariable("id") long id) {
        return boardService.delete(id);
    }

    // 게시글 조회(제목)
    @GetMapping("/search/title")
    public List<BoardDTO> searchTitle(@RequestParam("title") String title) {
        return boardService.findBoardByTitle(title);
    }

    // 게시글 조회(내용)
    @GetMapping("/search/content")
    public List<BoardDTO> findBoardsByContent(@RequestParam("content") String content) {
        return boardService.findBoardsByContent(content);
    }

    // 게시글 조회(태그)
    @GetMapping("/search/tag")
    public List<BoardDTO> findBoardsByTag(@RequestParam("tag") String tag) {
        return boardService.findBoardsByTag(tag);
    }
}
