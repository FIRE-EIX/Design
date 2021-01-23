package Servlet;

import Dao.AdminUserDao;
import JavaBean.ScoreInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdminListStudentServlet",urlPatterns = "/adminListStudentServlet")
public class AdminListStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String id = session.getAttribute("id").toString();
        AdminUserDao adminUserDao = new AdminUserDao();
        ArrayList<ScoreInfo> scoreInfos = adminUserDao.adminListScore();
        request.setAttribute("scoreInfos", scoreInfos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/scoreConfirm.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
