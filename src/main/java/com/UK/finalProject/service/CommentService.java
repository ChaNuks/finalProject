package com.UK.finalProject.service;

import com.UK.finalProject.dto.CommentDTO;
import com.UK.finalProject.entity.Comment;
import com.UK.finalProject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    // 댓글 작성
    public String createComment(CommentDTO commentDTO) {

        Comment toSaveComment = new Comment();
        toSaveComment.createComment(commentDTO.getContent());
        return commentRepository.createComment(toSaveComment);
    }

    // 댓글 수정
    public static String updateComment(long id, CommentDTO commentDTO) {
        return null;
    }

    // 댓글 삭제
    public static String deleteComment(long id) {
        return null;
    }

}
