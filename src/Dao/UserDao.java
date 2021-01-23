package Dao;

import JavaBean.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao extends BaseDao {
    /**
     * 列出成绩
     *
     * @return 返回查询到的成绩信息数组
     */
    public ArrayList<ScoreInfo> listScore(String id) {
        ArrayList<ScoreInfo> scoreInfo = new ArrayList<ScoreInfo>();
        try {
            Connection conn = getConnection();  //连接状态
            if (conn != null) {
                PreparedStatement stmt;
                ResultSet rs;
                String sql = "SELECT id,course_name,teacher_id,score,confirm,term\n" +
                        "FROM scoreinfo,courseinfo\n" +
                        "WHERE id = ? AND course_id = course_number AND confirm='yes'\n";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    ScoreInfo s1 = new ScoreInfo();
                    s1.setId(rs.getString(1));
                    s1.setCourseNumber(rs.getString(2));
                    s1.setTeacherId(rs.getString(3));
                    s1.setScore(rs.getInt(4));
                    s1.setConfirm(rs.getString(5));
                    s1.setTerm(rs.getString(6));
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

    public ArrayList<ClassInfo> listCourseInfo() {
        ArrayList<ClassInfo> classInfos = new ArrayList<ClassInfo>();
        try {
            Connection conn = getConnection();  //连接状态
            if (conn != null) {
                PreparedStatement stmt;
                ResultSet rs;
                String sql = "SELECT *\n" +
                        "FROM classinfo\n";
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    ClassInfo c1 = new ClassInfo();
                    c1.setClassNumber(rs.getString(1));
                    c1.setClassName(rs.getString(2));
                    classInfos.add(c1);
                }
                close(rs);
                close(stmt);
            }
            close(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return classInfos;
    }

    /**
     * 修改相应id的password
     *
     * @param id
     * @param password
     * @return
     */
    public int editPassword(String id, String password) {
        int count = 0;
        Connection conn = null;  //创建用于连接数据库的Connection对象
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            //3.获取数据库操作对象
            String sql = "UPDATE user_login\n" +
                    "SET password = ?\n" +
                    "WHERE id=?;";
            statement = conn.prepareStatement(sql);
            //将sql语句的问号值添加进去
            statement.setString(1, password);
            statement.setString(2, id);
            //4.执行sql语句
            count = statement.executeUpdate();
            close(conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    /**
     * 录入学生成绩
     *
     * @param id
     * @param courseNumber
     * @param score
     * @param term
     * @return
     */
    public int addScore(String id, String courseNumber, int score, String term) {
        int count = 0;
        try {
            Connection conn = getConnection();
            if (conn != null) {
                PreparedStatement stmt = null;
                int rs = 0;
                String sql = "INSERT INTO scoreinfo(id,course_number,score,term)\n" +
                        "VALUES(?,?,?,?)";  //查询语句
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                stmt.setString(2, courseNumber);
                stmt.setInt(3, score);
                stmt.setString(4, term);
                count = stmt.executeUpdate();
                close(stmt);
            }
            close(conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public int addClassInfo(String classId, String className) {
        int count = 0;
        try {
            Connection conn = getConnection();
            if (conn != null) {
                PreparedStatement stmt = null;
                int rs = 0;
                String sql = "INSERT INTO classinfo(class_number,class_name)\n" +
                        "VALUES(?,?)";  //查询语句
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, classId);
                stmt.setString(2, className);
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
     * 显示个人信息
     *
     * @param id
     * @return
     */
    public Student listMyInfo(String id) {
        Student student = new Student();
        try {
            Connection conn = getConnection();  //连接状态
            if (conn != null) {
                PreparedStatement stmt;
                ResultSet rs;
                String sql = "SELECT *\n" +
                        "FROM student\n" +
                        "WHERE id = ?";  //查询语句
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    student.setId(rs.getString(1));
                    student.setStudentName(rs.getString(2));
                    student.setSex(rs.getString(3));
                    student.setEntranceYear(rs.getInt(4));
                    student.setClassNumber(rs.getString(5));
                }
                close(rs);
                close(stmt);
            }
            close(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return student;
    }

    /**
     * 模糊查询分数信息
     *
     * @param searchCol
     * @param searchValue
     * @return
     */
    public ArrayList<ScoreInfo> findScore(String searchCol, String searchValue, String id) {
        ArrayList<ScoreInfo> scoreInf = new ArrayList<ScoreInfo>();
        try {
            Connection conn = getConnection();  //连接状态
            if (conn != null) {
                PreparedStatement stmt;
                ResultSet rs1;
                String sql = "SELECT id,course_name,teacher_id,score,confirm,term\n" +
                        "FROM scoreinfo,courseinfo\n" +
                        "WHERE " + searchCol + " like ? AND id = ? AND course_id = course_number";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, searchValue);
                stmt.setString(2, id);
                rs1 = stmt.executeQuery();
                while (rs1.next()) {
                    ScoreInfo s1 = new ScoreInfo();
                    s1.setId(rs1.getString(1));
                    s1.setCourseNumber(rs1.getString(2));
                    s1.setTeacherId(rs1.getString(3));
                    s1.setScore(rs1.getInt(4));
                    s1.setConfirm(rs1.getString(5));
                    s1.setTerm(rs1.getString(6));
                    scoreInf.add(s1);
                }
                close(rs1);
                close(stmt);
            }
            close(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return scoreInf;
    }

    /**
     * 教师模糊查询分数信息
     *
     * @param searchCol
     * @param searchValue
     * @param id
     * @return
     */
    public ArrayList<ScoreInfo> teacherFindScore(String searchCol, String searchValue, String id) {
        ArrayList<ScoreInfo> scoreInf = new ArrayList<ScoreInfo>();
        try {
            Connection conn = getConnection();  //连接状态
            if (conn != null) {
                PreparedStatement stmt;
                ResultSet rs1;
                String sql = "SELECT * FROM scoreinfo\n" +
                        "WHERE " + searchCol + " LIKE ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, searchValue);
                rs1 = stmt.executeQuery();
                while (rs1.next()) {
                    ScoreInfo s1 = new ScoreInfo();
                    s1.setId(rs1.getString(1));
                    s1.setCourseNumber(rs1.getString(2));
                    s1.setTeacherId(rs1.getString(3));
                    s1.setScore(rs1.getInt(4));
                    s1.setConfirm(rs1.getString(5));
                    s1.setTerm(rs1.getString(6));
                    scoreInf.add(s1);
                }
                close(rs1);
                close(stmt);
            }
            close(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return scoreInf;
    }

    /**
     * 修改分数信息
     *
     * @param id
     * @param cno
     * @param score
     * @param term
     * @return
     */
    public int editScore(String id, String cno, int score, String term) {
        int count = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            String sql = "UPDATE scoreinfo\n" +
                    "SET score = ?, term = ?\n" +
                    "WHERE id=? and course_number = ?;";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, score);
            statement.setString(2, term);
            statement.setString(3, id);
            statement.setString(4, cno);
            count = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close(statement);
        close(conn);
        return count;
    }

    /**
     * 列出所有老师
     *
     * @return
     */
    public ArrayList<Teacher> listTeacher() {
        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        try {
            Connection conn = getConnection();  //连接状态
            if (conn != null) {
                PreparedStatement stmt;
                ResultSet rs;
                String sql = "SELECT * FROM teacher\n";
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getString(1));
                    teacher.setTeacherName(rs.getString(2));
                    teacher.setSex(rs.getString(3));
                    teacher.setClassNumber(rs.getString(4));
                    teachers.add(teacher);
                }
                close(rs);
                close(stmt);
            }
            close(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return teachers;
    }

    /**
     * 列出所有学生
     *
     * @return
     */
    public ArrayList<Student> listStudent() {
        ArrayList<Student> students = new ArrayList<Student>();
        try {
            Connection conn = getConnection();  //连接状态
            if (conn != null) {
                PreparedStatement stmt;
                ResultSet rs;
                String sql = "SELECT * FROM student\n";
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getString(1));
                    student.setStudentName(rs.getString(2));
                    student.setSex(rs.getString(3));
                    student.setEntranceYear(rs.getInt(4));
                    student.setClassNumber(rs.getString(5));
                    students.add(student);
                }
                close(rs);
                close(stmt);
            }
            close(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return students;
    }
}