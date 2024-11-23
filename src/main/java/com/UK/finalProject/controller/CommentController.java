package com.UK.finalProject.controller;

import com.UK.finalProject.dto.CommentDTO;
import com.UK.finalProject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/write")
    public String write(@RequestBody CommentDTO commentDTO) {
        return commentService.createComment(commentDTO);
    }

    // 댓글 수정
    @PutMapping("/edit/{id}")
    public String updateComment(@PathVariable("id") long id, @RequestBody CommentDTO commentDTO) {
        return CommentService.updateComment(id, commentDTO);
    }

   // 댓글 삭제
   @DeleteMapping("/delete/{id}")
   public String deleteComment(@PathVariable("id") long id) {
        return CommentService.deleteComment(id);
   }
}
