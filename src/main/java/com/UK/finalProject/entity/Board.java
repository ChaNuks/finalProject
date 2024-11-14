package com.UK.finalProject.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

    private Long id;
    private String title;
    private String content;
    private String boardImage;
    private Long memberId;
    private Long categoryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Status status;

    public void updateBoardInfo(String title, String content, long categoryId, String boardImage) {
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.boardImage = boardImage;
        this.updatedAt = LocalDateTime.now();
        this.status = Status.EDITED;
    }

    public enum Status {
        WRITTEN, EDITED, DELETED
    }

    public void createBoard(String title, String content, String boardImage, long categoryId) {
        this.title = title;
        this.content = content;
        this.boardImage = boardImage;
        this.categoryId = categoryId;
        this.status = Status.WRITTEN;
    }

}
