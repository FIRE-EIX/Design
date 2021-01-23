package Servlet;

import Dao.UserDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EditPassword", urlPatterns = "/editPassword")
public class EditPassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        {
            // TODO Auto-generated method stub
            //设置utf-8，防止出现乱码情况
            request.setCharacterEncoding("UTF-8");
            PrintWriter out;
            HttpSession session = request.getSession();
            String id = session.getAttribute("id").toString();
            String password = request.getParameter("input1");
            UserDao userDao = new UserDao();
            int count = userDao.editPassword(id, password);
            if (count == 1) {
                response.setCharacterEncoding("GBK");
                out = response.getWriter();
                out.println("<script language='javascript'>alert('修改成功');window.location.href='/Design/pages/index.jsp';</script>");
            } else {
                response.setCharacterEncoding("GBK");
                out = response.getWriter();
                out.println("<script language='javascript'>alert('修改失败');window.location.href='/Design/pages/index.jsp';</script>");
            }
        }
    }
}
