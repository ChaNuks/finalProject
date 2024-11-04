package com.UK.finalProject.domain.comment.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@ToString
@Builder
public class Comment {

    private Long id;
    private String content;
    private Long parentId;
    private Long memberId;
    private Long boardId;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate deletedAt;

    @Builder
    public Comment(Long id, String content, Long parentId, Long memberId, Long boardId, LocalDate createdAt, LocalDate updatedAt, LocalDate deletedAt) {
        this.id = id;
        this.content = content;
        this.parentId = parentId;
        this.memberId = memberId;
        this.boardId = boardId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }
}
