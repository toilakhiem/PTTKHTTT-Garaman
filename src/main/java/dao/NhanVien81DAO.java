package dao;

import model.NhanVien81;
import model.ThanhVien81;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NhanVien81DAO {
    static NhanVien81DAO shared = null;

    public static NhanVien81DAO instance() {
        if (shared == null) {
            shared = new NhanVien81DAO();
        }
        return shared;
    }

    public int createOne(NhanVien81 user) {
        String sql = "insert into NhanVien81" + " (ThanhVien81id) values " + "(?);";
        int result = 0;

        try {
            ThanhVien81DAO.instance().createOne(user);
            Connection conn = Database.instance().conn;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, user.getId());
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public NhanVien81 findOne(ThanhVien81 thanhvien) {
        String sql = "select * from NhanVien81 where ThanhVien81id = ?";
        NhanVien81 user = null;

        try {
            Connection conn = Database.instance().conn;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, thanhvien.getId());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new NhanVien81();
                user.setId(thanhvien.getId());
                user.setTaikhoan(thanhvien.getTaikhoan());
                user.setMatkhau(thanhvien.getMatkhau());
                user.setTen(thanhvien.getTen());
                user.setDiachi(thanhvien.getDiachi());
                user.setEmail(thanhvien.getEmail());
                user.setDt(thanhvien.getDt());
                user.setGhichu(thanhvien.getGhichu());
                user.setThanhvien248id(thanhvien.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
