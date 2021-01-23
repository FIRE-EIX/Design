package Servlet;

import Dao.AdminUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteTeacherServlet", urlPatterns = "/deleteTeacherServlet")
public class DeleteTeacherServlet extends HttpServlet {
    PrintWriter out;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id678");
        AdminUserDao adminUserDao = new AdminUserDao();
        int count = adminUserDao.deleteTeacher(id);
        if (count == 1) {
            response.setCharacterEncoding("GBK");
            out = response.getWriter();
            out.println("<script language='javascript'>alert('操作成功');window.location.href='/Design/pages/index.jsp';</script>");
        } else {
            out.println("<script language='javascript'>alert('操作失败');window.location.href='/Design/pages/index.jsp';</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
