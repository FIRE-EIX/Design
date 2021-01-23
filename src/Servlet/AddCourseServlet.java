package Servlet;

import Dao.AdminUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddCourseServlet", urlPatterns = "/addCourseServlet")
public class AddCourseServlet extends HttpServlet {
    PrintWriter out;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("input1");
        String cname = request.getParameter("input2");
        AdminUserDao adminUserDao = new AdminUserDao();
        int count = adminUserDao.addCourse(id,cname);
        if (count == 1) {
            response.setCharacterEncoding("GBK");
            out = response.getWriter();
            out.println("<script language='javascript'>alert('添加成功');window.location.href='/Design/pages/index.jsp';</script>");
        } else {
            out.println("<script language='javascript'>alert('添加失败');window.location.href='/Design/pages/index.jsp';</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
