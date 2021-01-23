package Servlet;

import Dao.AdminUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EnterInformation", urlPatterns = "/enterInformation")
public class EnterInformation extends HttpServlet {
    PrintWriter out;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id70");
        String cname = request.getParameter("cid70");
        String tname = request.getParameter("tid70");
        String term = request.getParameter("addTerm70");
        AdminUserDao adminUserDao = new AdminUserDao();
        int count = adminUserDao.addInfo(id,cname,tname,term);
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
