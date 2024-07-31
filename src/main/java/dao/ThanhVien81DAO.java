package dao;

import model.ThanhVien81;

import java.sql.*;

public class ThanhVien81DAO {
    static ThanhVien81DAO shared = null;

    public static ThanhVien81DAO instance() {
        if (shared == null) {
            shared = new ThanhVien81DAO();
        }
        return shared;
    }

    public int createOne(ThanhVien81 user) {
        String sql = "insert into ThanhVien81" + " (taikhoan, matkhau, ten, diachi, email, dt, ghichu) values " + "(?, ?, ?, ?, ?, ?, ?);";
        int result = 0;

        try {
            Connection conn = Database.instance().conn;
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getTaikhoan());
            preparedStatement.setString(2, user.getMatkhau());
            preparedStatement.setString(3, user.getTen());
            preparedStatement.setString(4, user.getDiachi());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getDt());
            preparedStatement.setString(7, user.getGhichu());
            result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new SQLException("Creating failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating failed, no ID obtained.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public ThanhVien81 findOne(String username) {
        String sql = "select * from ThanhVien81 where taikhoan = ?";
        ThanhVien81 user = null;

        try {
            Connection conn = Database.instance().conn;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new ThanhVien81();
                user.setId(rs.getLong("id"));
                user.setTaikhoan(rs.getString("taikhoan"));
                user.setMatkhau(rs.getString("matkhau"));
                user.setTen(rs.getString("ten"));
                user.setDiachi(rs.getString("diachi"));
                user.setEmail(rs.getString("email"));
                user.setDt(rs.getString("dt"));
                user.setGhichu(rs.getString("ghichu"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
