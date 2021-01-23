package Servlet;

import Dao.UserDao;
import JavaBean.ClassInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ListClassInfoServlet", urlPatterns = "/listClassInfoServlet")
public class ListClassInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        ArrayList<ClassInfo> classInfos = userDao.listCourseInfo();
        request.setAttribute("classInfos", classInfos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/listClassInfo.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
