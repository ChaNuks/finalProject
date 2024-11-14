package com.UK.finalProject.repository;

import com.UK.finalProject.dto.BoardDTO;
import com.UK.finalProject.entity.Board;
import com.UK.finalProject.entity.Member;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Builder
@Transactional
public class BoardRepository {

    private final DataSource dataSource;

    // 게시글 작성
    public String writeBoard(Board board) {

        LocalDateTime localDateTime = LocalDateTime.now();
        String sql = "INSERT INTO BOARD (title, content, board_image, category_id, status) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int affected = 0;

        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setString(3, board.getBoardImage());
            pstmt.setLong(4, board.getCategoryId());
            pstmt.setString(5, board.getStatus().name());

            affected = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affected > 0 ? "게시물 작성 완료" : "게시물 작성 실패";
    }

    // 게시글 수정
    public String updateBoard(Board board) {

        String sql = "UPDATE BOARD SET title = ?, content = ?, category_id = ?, board_image = ?, updated_at = CURRENT_TIMESTAMP, status = ?, member_id = ?,  WHERE id = ?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int affected = 0;

        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setLong(3, board.getCategoryId());
            pstmt.setString(4, board.getBoardImage());
            pstmt.setString(5, board.getStatus().name());
            pstmt.setLong(6, board.getMemberId());
            pstmt.setLong(7, board.getId());

            affected = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return affected > 0 ? "수정 완료" : "수정 실패";
    }

    // 게시글 삭제
    public String deleteBoard(long id) {

        String sql = "DELETE FROM BOARD WHERE id = ?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int affected = 0;

        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, id);
            affected = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affected > 0 ? "삭제 완료" : "삭제 실패";
    }


    // BOARD 조회
    @Transactional(readOnly = true)
    public Board findBoardById(long id) {

        String sql = "SELECT * FROM BOARD WHERE id = ?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return Board.builder()
                        .id(rs.getLong(1))
                        .title(rs.getString(2))
                        .content(rs.getString(3))
                        .boardImage(rs.getString(4))
                        .memberId(rs.getLong(5))
                        .categoryId(rs.getLong(6))
                        .createdAt(rs.getTimestamp(7).toLocalDateTime())
                        .updatedAt(rs.getTimestamp(8) != null ? rs.getTimestamp(8).toLocalDateTime() : null)
                        .status(Board.Status.valueOf(rs.getString(10)))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 게시글 조회(제목)
    public List<Board> findBoardByTitle(String title) {
        String sql = "SELECT * FROM BOARD WHERE title LIKE ?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Board> boards = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, title);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Board board = Board.builder()
                        .title(rs.getString(1))
                        .build();
                boards.add(board);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                pstmt.close();
//                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return boards;
    }

    // 게시글 조회(내용)
    public List<Board> findBoardByContent(String content) {
        String sql = "SELECT content FROM BOARD = ?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Board> boards = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, content);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                return boards;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                pstmt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return boards;
    }

    // 게시글 조회(태그)
    public List<Board> findBoardByTag(String tagId) {
        String sql = "SELECT tag_id FROM BOARD = ?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Board> boards = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, tagId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                return boards;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                pstmt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return boards;
    }
}
