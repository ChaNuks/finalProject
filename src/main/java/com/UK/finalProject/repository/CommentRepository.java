package com.UK.finalProject.repository;

import com.UK.finalProject.entity.Comment;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
@Builder
public class CommentRepository {

    private final DataSource dataSource;

    public String createComment(Comment toSaveComment) {

        LocalDateTime localDateTime = LocalDateTime.now();
        String sql = "INSERT INTO COMMENT (content) VALUES (?)";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int affected = 0;

        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, toSaveComment.getContent());

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
        return affected > 0 ? "댓글 작성 완료" : "댓글 작성 실패";
    }
}
