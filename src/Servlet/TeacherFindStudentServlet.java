package Servlet;

import Dao.TeacherUserDao;
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

@WebServlet(name = "TeacherFindStudentServlet",urlPatterns = "/teacherFindStudentServlet")
public class TeacherFindStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchCol = request.getParameter("searchCol");
        String searchValue = request.getParameter("searchValue");
        searchValue = "%"+searchValue+"%";
        TeacherUserDao teacherUserDao = new TeacherUserDao();
        HttpSession session = request.getSession();
        String id = session.getAttribute("id").toString();
        ArrayList<ScoreInfo> scoreInfos = null;
        if("name".equals(searchCol)){
            scoreInfos = teacherUserDao.teacherFindScoreByName(searchCol,searchValue,id);
        }else if("id".equals(searchCol)){
            scoreInfos = teacherUserDao.teacherFindScoreById(searchCol,searchValue,id);
        }
        request.setAttribute("scoreInfos", scoreInfos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/teacherListStudents.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
