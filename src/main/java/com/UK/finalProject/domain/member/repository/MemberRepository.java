package com.UK.finalProject.domain.member.repository;

import com.UK.finalProject.domain.member.entity.Member;
import com.UK.finalProject.domain.member.service.MemberService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
@RequiredArgsConstructor
@Builder
public class MemberRepository {

    private final DataSource dataSource;

    // 멤버 조회
    public Member findMemberById(long id) {
        String sql = "SELECT * FROM MEMBER WHERE ID = ?";
        Connection connection = null;
        PreparedStatement pstnt = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
//          Statement statement = connection.createStatement(); // SQL Injection 공격에 취약하므로 안 씀
            pstnt = connection.prepareStatement(sql);
            pstnt.setLong(1, id);
            rs = pstnt.executeQuery();    // 쿼리 결과 담는 곳

            if (rs.next()) {
                return Member.builder()
                        .id(rs.getLong(1))
                        .email(rs.getString(2))
                        .password(rs.getString(3))
                        .nickname(rs.getString(4))
                        .name(rs.getString(5))
                        .image(rs.getString(6))
                        .createdAt(rs.getTimestamp(7).toLocalDateTime())
                        .updatedAt(rs.getTimestamp(8).toLocalDateTime())
                        .status(Member.Status.valueOf(rs.getString(9)))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                pstnt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
