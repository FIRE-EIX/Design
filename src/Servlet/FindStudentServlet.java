package Servlet;

import Dao.UserDao;
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

@WebServlet(name = "FindStudentServlet",urlPatterns = "/findStudentServlet")
public class FindStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchCol = request.getParameter("searchCol");
        String searchValue = request.getParameter("searchValue");
        searchValue = "%"+searchValue+"%";
        UserDao userDao = new UserDao();
        HttpSession session = request.getSession();
        String id = session.getAttribute("id").toString();
        ArrayList<ScoreInfo> scoreInfo = null;
        scoreInfo = userDao.findScore(searchCol,searchValue,id);
        request.setAttribute("score", scoreInfo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/listScore.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
