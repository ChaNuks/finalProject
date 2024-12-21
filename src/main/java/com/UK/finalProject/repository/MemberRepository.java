package com.UK.finalProject.repository;

import com.UK.finalProject.entity.Member;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
@Builder
@Transactional
public class MemberRepository {

    private final DataSource dataSource;

    // 멤버 조회
    @Transactional(readOnly = true)
    public Member findMemberById(long id) {
        String sql = "SELECT * FROM MEMBER WHERE ID = ?";
        Connection connection = null;
        PreparedStatement pstnt = null;
        ResultSet rs = null;
//        Statement statement = connection.createStatement(); // SQL Injection 공격에 취약하므로 안 씀

        try (connection = dataSource.getConnection();
            pstnt = connection.prepareStatement(sql);
            pstnt.setLong(1, id);
            rs = pstnt.executeQuery();) {

            if (rs.next()) {
                return Member.builder()
                        .id(rs.getLong(1))
                        .email(rs.getString(2))
                        .password(rs.getString(3))
                        .nickname(rs.getString(4))
                        .name(rs.getString(5))
                        .image(rs.getString(6))
                        .createdAt(rs.getTimestamp(7).toLocalDateTime())
                        .updatedAt(rs.getTimestamp(8) != null ? rs.getTimestamp(8).toLocalDateTime() : null)
                        .status(Member.Status.valueOf(rs.getString(9)))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return null;
    }

    // 회원가입
    public String signupMember(Member member) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String sql = "INSERT INTO MEMBER (email, password, name, nickname, image) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement pstnt = null;
        int affected = 0;

        try {
            connection = dataSource.getConnection();
//            connection.setAutoCommit(false);
            pstnt = connection.prepareStatement(sql);

            pstnt.setString(1, member.getEmail());
            pstnt.setString(2, member.getPassword());
            pstnt.setString(3, member.getName());
            pstnt.setString(4, member.getNickname());
            pstnt.setString(5, member.getImage());

            affected = pstnt.executeUpdate();
//            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                pstnt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return member.getEmail();
//        return affected > 0 ? member.getEmail() : "회원가입 실패";
    }

    // 로그인
    public Member findMemberByEmail(String email) {
        String sql = "SELECT email, password FROM MEMBER WHERE email = ?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return Member.builder()
                        .email(rs.getString(1))
                        .password(rs.getString(2))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } finally {
           try {
               connection.close();
               pstmt.close();
               rs.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
        }
        return null;
    }

    // 회원정보 수정
    public String updateMember(Member member) {

        String sql = "UPDATE MEMBER SET password = ?, name = ?, nickname = ?, image = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int affected = 0;

        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, member.getPassword());
            pstmt.setString(2, member.getNickname());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getImage());
            pstmt.setLong(5, member.getId());
            affected = pstmt.executeUpdate();   // 영향 받은 행 반환

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affected > 0 ? "수정 완료" : "수정 실패";
    }

    // 회원 탈퇴
    public String deleteMember(long id) {

        String sql = "DELETE FROM MEMBER WHERE id = ?";
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
        return affected > 0 ? "탈퇴 완료" : "탈퇴 실패";
    }
}
