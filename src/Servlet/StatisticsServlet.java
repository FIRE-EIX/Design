package Servlet;

import Dao.TeacherUserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "StatisticsServlet",urlPatterns = "/statisticsServlet")
public class StatisticsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String id = session.getAttribute("id").toString();
        String cno = request.getParameter("cno60");
        TeacherUserDao teacherUserDao = new TeacherUserDao();
        double[] array = new double[4];
        array = teacherUserDao.statistics(id,cno);
        request.setAttribute("array", array);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/Statistics.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
