package servlet;

import dao.LichHen81DAO;
import model.LichHen81;
import model.ThanhVien81;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@WebServlet("/datlichhen")
public class gdDatlichhenServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/kh/gdDatlichhen.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Timestamp thoigian = new Timestamp(sdf.parse(req.getParameter("thoigian")).getTime());
            LichHen81 lichhen81 = new LichHen81();
            lichhen81.setKhachhang248id(((ThanhVien81) req.getSession().getAttribute("user")).getId());
            lichhen81.setTen(req.getParameter("ten"));
            lichhen81.setDt(req.getParameter("dt"));
            lichhen81.setThoigian(thoigian);
            lichhen81.setTrangthai(0);
            LichHen81DAO.instance().createOne(lichhen81);
            req.setAttribute("success", true);
            req.getRequestDispatcher("/view/kh/gdDatlichhen.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("success", false);
            req.getRequestDispatcher("/view/kh/gdDatlichhen.jsp").forward(req, resp);
        }
    }
}
