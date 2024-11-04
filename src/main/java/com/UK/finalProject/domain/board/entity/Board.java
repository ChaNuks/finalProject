package com.UK.finalProject.domain.board.entity;

import com.UK.finalProject.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@Builder
public class Board {

    private Long id;
    private String title;
    private String content;
    private String boardImage;
    private Long memberId;
    private Long categoryId;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate deletedAt;
    private Status status;

    public enum Status {
        written, editing, deleted
    }

    @Builder
    public Board(Long id, String title, String content, String boardImage, Long memberId, Long categoryId, LocalDate createdAt, LocalDate updatedAt, LocalDate deletedAt, Status status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.boardImage = boardImage;
        this.memberId = memberId;
        this.categoryId = categoryId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.status = status;
    }
}
