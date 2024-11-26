package com.UK.finalProject.dto;

import com.UK.finalProject.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {

    private long id;
    private String title;
    private String content;
    private String boardImage;
    private Long memberId;
    private Long categoryId;


    public BoardDTO(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
