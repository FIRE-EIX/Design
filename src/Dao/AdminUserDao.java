package Dao;

import JavaBean.CourseInfo;
import JavaBean.ScoreInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminUserDao extends BaseDao{
    /**
     * 显示课程列表
     * @return
     */
    public ArrayList<CourseInfo> listScore() {
        ArrayList<CourseInfo> courseInfos = new ArrayList<CourseInfo>();
        try {
            Connection conn = getConnection();  //连接状态
            if (conn != null) {
                PreparedStatement stmt;
                ResultSet rs;
                String sql = "SELECT * FROM courseinfo\n";
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    CourseInfo courseInfo = new CourseInfo();
                    courseInfo.setCourseId(rs.getString(1));
                    courseInfo.setCourseName(rs.getString(2));
                    courseInfos.add(courseInfo);
                }
                close(rs);
                close(stmt);
            }
            close(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return courseInfos;
    }

    public int editCourse(String id, String cname) {
        int count=0;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            String sql = "UPDATE courseinfo\n" +
                    "SET course_name = ?\n" +
                    "WHERE course_id=?;";
            statement = conn.prepareStatement(sql);
            statement.setString(1, cname);
            statement.setString(2, id);
            count = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close(statement);
        close(conn);
        return count;
    }

    public ArrayList<ScoreInfo> adminListScore() {
        ArrayList<ScoreInfo> scoreInfo = new ArrayList<ScoreInfo>();
        try {
            Connection conn = getConnection();  //连接状态
            if (conn != null) {
                PreparedStatement stmt;
                ResultSet rs;
                String sql = "SELECT scoreinfo.id,student_name,course_number,scoreinfo.teacher_id,teacher.teacher_name,scoreinfo.term,score,confirm\n" +
                        "FROM scoreinfo,teacher,student\n" +
                        "where scoreinfo.id = student.id and scoreinfo.teacher_id = teacher.id and scoreinfo.id in\n" +
                        "(\n" +
                        "\tSELECT student.id\n" +
                        "\tFROM teacher,student\n" +
                        "\tWHERE teacher.class_number = student.class_number\n" +
                        ")";
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    ScoreInfo s1 = new ScoreInfo();
                    s1.setId(rs.getString(1));
                    s1.setStudentName(rs.getString(2));
                    s1.setCourseNumber(rs.getString(3));
                    s1.setTeacherId(rs.getString(4));
                    s1.setTeacherName(rs.getString(5));
                    s1.setTerm(rs.getString(6));
                    s1.setScore(rs.getInt(7));
                    if ("no".equals(rs.getString(8))) {
                        s1.setConfirm("未审核");
                    } else if ("ing".equals(rs.getString(8))) {
                        s1.setConfirm("审核中");
                    } else {
                        s1.setConfirm("审核通过");
                    }
                    scoreInfo.add(s1);
                }
                close(rs);
                close(stmt);
            }
            close(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return scoreInfo;
    }

    /**
     * 通过审核
     * @param id
     * @param cno
     * @return
     */
    public int passConfirmScore(String id, String cno){
        int count=0;
        Connection conn = null;  //创建用于连接数据库的Connection对象
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            String sql = "UPDATE scoreinfo\n" +
                    "SET confirm = 'yes'\n" +
                    "WHERE id=? and course_number = ?;";
            statement = conn.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, cno);
            count = statement.executeUpdate();
            close(conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    /**
     * 审核不通过
     * @param id
     * @param cno
     * @return
     */
    public int backConfirmScore(String id, String cno){
        int count=0;
        Connection conn = null;  //创建用于连接数据库的Connection对象
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            String sql = "UPDATE scoreinfo\n" +
                    "SET confirm = 'no'\n" +
                    "WHERE id=? and course_number = ?;";
            statement = conn.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, cno);
            count = statement.executeUpdate();
            close(conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    /**
     * 新增老师
     * @param id
     * @param name
     * @param sex
     * @param cno
     * @return
     */
    public int addTeacher(String id, String name, String sex,String cno){
        int count=0;
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        PreparedStatement stmt1 = null;
        try {
            conn.setAutoCommit(false);
            if (conn != null) {
                String sql = "INSERT INTO teacher(id,teacher_name,sex,class_number)\n" +
                        "VALUES(?,?,?,?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                stmt.setString(2, name);
                stmt.setString(3, sex);
                stmt.setString(4, cno);
                stmt.executeUpdate();
                String sql1 = "INSERT INTO user_login(id,user_name,password)\n" +
                        "VALUES(?,?,?)";
                stmt1 = conn.prepareStatement(sql1);
                stmt1.setString(1, id);
                stmt1.setString(2, name);
                stmt1.setString(3, "123");
                count = stmt1.executeUpdate();
                conn.commit();
                conn.setAutoCommit(true);
            }
        } catch (SQLException throwables) {
            try {
                if(conn != null){
                    conn.rollback();
                    return 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            if(stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(stmt1!=null){
                try {
                    stmt1.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return count;
    }

    /**
     * 删除老师
     * @param id
     * @return
     */
    public int deleteTeacher(String id){
        int count=0;
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        PreparedStatement stmt1 = null;
        try {
            conn.setAutoCommit(false);
            if (conn != null) {
                String sql = "DELETE FROM teacher\n" +
                        "WHERE id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                stmt.executeUpdate();
                String sql1 = "DELETE FROM user_login\n" +
                        "WHERE id = ?";
                stmt1 = conn.prepareStatement(sql1);
                stmt1.setString(1, id);
                count = stmt1.executeUpdate();
                conn.commit();
                conn.setAutoCommit(true);
            }
        } catch (SQLException throwables) {
            try {
                if(conn != null){
                    conn.rollback();
                    return 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            if(stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(stmt1!=null){
                try {
                    stmt1.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return count;
    }

    /**
     * 添加学生
     * @param id
     * @param name
     * @param sex
     * @param year
     * @param cno
     * @return
     */
    public int addStudent(String id, String name, String sex,String year, String cno){
        int count=0;
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        PreparedStatement stmt1 = null;
        try {
            conn.setAutoCommit(false);
            if (conn != null) {
                String sql = "INSERT INTO student(id,student_name,sex,entrance_year,class_number)\n" +
                        "VALUES(?,?,?,?,?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                stmt.setString(2, name);
                stmt.setString(3, sex);
                stmt.setString(4, year);
                stmt.setString(5, cno);
                stmt.executeUpdate();
                String sql1 = "INSERT INTO user_login(id,user_name,password)\n" +
                        "VALUES(?,?,?)";
                stmt1 = conn.prepareStatement(sql1);
                stmt1.setString(1, id);
                stmt1.setString(2, name);
                stmt1.setString(3, "123");
                count = stmt1.executeUpdate();
                conn.commit();
                conn.setAutoCommit(true);
            }
        } catch (SQLException throwables) {
            try {
                if(conn != null){
                    conn.rollback();
                    return 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            if(stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(stmt1!=null){
                try {
                    stmt1.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return count;
    }

    /**
     * 删除学生
     * @param id
     * @return
     */
    public int deleteStudent(String id){
        int count=0;
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        try {
            conn.setAutoCommit(false);
            if (conn != null) {
                String sql = "DELETE FROM Student\n" +
                        "WHERE id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                stmt.executeUpdate();
                String sql1 = "DELETE FROM user_login\n" +
                        "WHERE id = ?";
                stmt1 = conn.prepareStatement(sql1);
                stmt1.setString(1, id);
                count = stmt1.executeUpdate();
                String sql2 = "DELETE FROM scoreinfo\n" +
                        "WHERE id = ?";
                stmt2 = conn.prepareStatement(sql2);
                stmt2.setString(1, id);
                count = stmt2.executeUpdate();
                conn.commit();
                conn.setAutoCommit(true);
            }
        } catch (SQLException throwables) {
            try {
                if(conn != null){
                    conn.rollback();
                    return 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            if(stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(stmt1!=null){
                try {
                    stmt1.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(stmt2!=null){
                try {
                    stmt2.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return count;
    }

    /**
     * 添加课程
     * @param id
     * @param name
     * @return
     */
    public int addCourse(String id, String name){
        int count=0;
        try {
            Connection conn = getConnection();
            if (conn != null) {
                PreparedStatement stmt = null;
                int rs = 0;
                String sql = "INSERT INTO courseinfo(course_id,course_name)\n" +
                        "VALUES(?,?)";  //查询语句
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                stmt.setString(2, name);
                count = stmt.executeUpdate();
                close(stmt);
            }
            close(conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    /**
     * 删除课程
     * @param id
     * @return
     */
    public int deleteCourse(String id){
        int count=0;
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        try {
            conn.setAutoCommit(false);
            if (conn != null) {
                String sql = "DELETE FROM course_info\n" +
                        "WHERE course_id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                stmt.executeUpdate();
                conn.commit();
                conn.setAutoCommit(true);
            }
        } catch (SQLException throwables) {
            try {
                if(conn != null){
                    conn.rollback();
                    return 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            if(stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return count;
    }

    public int addInfo(String id, String courseNumber, String teacherId,String term){
        int count=0;
        try {
            Connection conn = getConnection();
            if (conn != null) {
                PreparedStatement stmt = null;
                String sql = "INSERT INTO scoreinfo(id,course_number,teacher_id,score,confirm,term)\n" +
                        "VALUES(?,?,?,?,?,?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                stmt.setString(2, courseNumber);
                stmt.setString(3, teacherId);
                stmt.setInt(4,-1);
                stmt.setString(5, "no");
                stmt.setString(6, term);
                count = stmt.executeUpdate();
                if(stmt!=null){
                    close(stmt);
                }
            }
            if(conn!=null){
                close(conn);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
}
