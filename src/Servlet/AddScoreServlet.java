package Servlet;

import Dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddScoreServlet", urlPatterns = "/addScoreServlet")
public class AddScoreServlet extends HttpServlet {
    PrintWriter out;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("input1");
        String cno = request.getParameter("input2");
        String score1 = request.getParameter("input3");
        int score = Integer.parseInt(score1);
        String term = request.getParameter("addTerm");
        UserDao userDao = new UserDao();
        int count = userDao.addScore(id, cno, score, term);
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
