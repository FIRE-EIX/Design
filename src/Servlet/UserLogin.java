package Servlet;

import Utils.Tools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 登录
 */
@WebServlet(name = "UserLogin", urlPatterns = {"/userLogin"})
public class UserLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        PrintWriter out;
        //获取id
        String id = request.getParameter("id");
        //获取密码
        String password = request.getParameter("password");
        try {
            Connection conn = Tools.getConnection();
            String sql = "select * from user_login where id=? and password=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            //将sql语句的问号值添加进去
            statement.setString(1, id);
            statement.setString(2, password);
            //4.执行sql语句
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("id", id);
                session.setAttribute("name", rs.getString("user_name"));
                session.setAttribute("password", password);

                //System.out.println("登录成功，欢迎你："+id);
                //request.setAttribute("remind", "登录成功");
//                    request.setAttribute("tempid", id);
//                    request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
                response.sendRedirect("/Design/pages/index.jsp");
            } else {
                request.setAttribute("remind", "登录失败");
                response.setCharacterEncoding("GBK");
                out = response.getWriter();
                out.println("<script language='javascript'>alert('用户名或密码错误,请重新输入！');window.location.href='/Design/login.jsp';</script>");
            }

            //6.释放连接
            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
