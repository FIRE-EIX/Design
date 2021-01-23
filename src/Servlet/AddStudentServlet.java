package Servlet;

import Dao.AdminUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddStudentServlet", urlPatterns = "/addStudentServlet")
public class AddStudentServlet extends HttpServlet {
    PrintWriter out;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id55");
        String name = request.getParameter("name55");
        String sex = request.getParameter("sex55");
        String year = request.getParameter("year55");
        String cno = request.getParameter("class55");
        AdminUserDao adminUserDao = new AdminUserDao();
        int count = adminUserDao.addStudent(id,name,sex,year,cno);
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
