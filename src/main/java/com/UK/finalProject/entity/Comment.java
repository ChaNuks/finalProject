package com.UK.finalProject.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Long id;
    private String content;
    private Long parentId;
    private Long memberId;
    private Long boardId;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate deletedAt;

    public void createComment(String content) {
        this.content = content;
    }
}
