package com.UK.finalProject.dto;

import com.UK.finalProject.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Long id;
    private String content;
    private Long parentId;
    private Long memberId;
    private Long boardId;


    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.parentId = comment.getParentId();
        this.memberId = comment.getMemberId();
        this.boardId = comment.getBoardId();
    }
}
