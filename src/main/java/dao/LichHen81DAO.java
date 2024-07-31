package dao;

import model.LichHen81;

import java.sql.*;

public class LichHen81DAO {
    static LichHen81DAO shared = null;

    public static LichHen81DAO instance() {
        if (shared == null) {
            shared = new LichHen81DAO();
        }
        return shared;
    }

    public int createOne(LichHen81 lichhen81) {
        String sql = "insert into LichHen81" + " (KhachHang81id, ten, dt, thoigian, trangthai) values " + "(?, ?, ?, ?, ?);";
        int result = 0;

        try {
            Connection conn = Database.instance().conn;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, lichhen81.getKhachhang248id());
            preparedStatement.setString(2, lichhen81.getTen());
            preparedStatement.setString(3, lichhen81.getDt());
            preparedStatement.setTimestamp(4, lichhen81.getThoigian());
            preparedStatement.setInt(5, lichhen81.getTrangthai());
            result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new SQLException("Creating failed, no rows affected.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
