package model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class HoaDon81 {
    private long id;
    private long Khachhang248id;
    private long NVBanhang248id;
    private Timestamp thoigian;
    private long tongtien;
    private int trangthai;

    // Getters and setters
}