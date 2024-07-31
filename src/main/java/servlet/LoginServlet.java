package servlet;

import dao.KhachHang81DAO;
import dao.ThanhVien81DAO;
import model.KhachHang81;
import model.ThanhVien81;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("taikhoan");
        String password = req.getParameter("matkhau");
        ThanhVien81 user = ThanhVien81DAO.instance().findOne(username);

        System.out.println(user);

        if (user != null && user.getMatkhau().equals(password)) {
            req.getSession().setAttribute("user", user);
            KhachHang81 kh = KhachHang81DAO.instance().findOne(user);

            System.out.println(kh);

            if (kh != null) {
                resp.sendRedirect("/datlichhen");
            }
        } else {
            req.setAttribute("error", "Tài khoản hoặc mật khẩu sai");
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
        }
    }
}
