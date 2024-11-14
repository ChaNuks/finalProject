package com.UK.finalProject.service;

import com.UK.finalProject.dto.BoardDTO;
import com.UK.finalProject.entity.Board;
import com.UK.finalProject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 작성
    public String write(BoardDTO boardDTO) {

        Board toSaveBoard = new Board();
        toSaveBoard.createBoard(boardDTO.getTitle(), boardDTO.getContent(), boardDTO.getBoardImage(), boardDTO.getCategoryId());
        return boardRepository.writeBoard(toSaveBoard);
    }

    // 게시물 수정
    public String updateBoard(long id, BoardDTO boardDTO) {

        Board editBoard = boardRepository.findBoardById(id);

        editBoard.updateBoardInfo(boardDTO.getTitle(), boardDTO.getContent(), boardDTO.getCategoryId(), boardDTO.getBoardImage());
        return boardRepository.updateBoard(editBoard);
    }

    // 게시글 삭제
    public String delete(long id) {

        return boardRepository.deleteBoard(id);
    }

    // 게시글 조회(제목)
    public List<BoardDTO> findBoardByTitle(String title) {
        List<Board> boards = boardRepository.findBoardByTitle(title);
        List<BoardDTO> boardDTOS = new ArrayList<>();

        for (Board board : boards) {
            BoardDTO boardDTO = BoardDTO.builder()
                    .title(board.getTitle())
                    .build();
            boardDTOS.add(boardDTO);
        }
        return boardDTOS;
    }

    // 게시글 조회(내용)
    public List<BoardDTO> findBoardsByContent(String content) {
        List<Board> boards = boardRepository.findBoardByContent(content);
        return null;
    }

    // 게시글 조회(태그)
    public List<BoardDTO> findBoardsByTag(String tagId) {
        List<Board> boards = boardRepository.findBoardByTag(tagId);
        return null;
    }


}
