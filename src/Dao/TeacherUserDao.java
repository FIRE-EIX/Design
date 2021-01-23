package Dao;

import JavaBean.ScoreInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherUserDao extends BaseDao {
    /**
     * 列出所教班级学生成绩
     *
     * @param id
     * @return
     */
    public ArrayList<ScoreInfo> listScore(String id) {
        ArrayList<ScoreInfo> scoreInfo = new ArrayList<ScoreInfo>();
        try {
            Connection conn = getConnection();  //连接状态
            if (conn != null) {
                PreparedStatement stmt;
                ResultSet rs;
                String sql = "SELECT scoreinfo.id,student_name,course_number,scoreinfo.teacher_id,course_name,scoreinfo.term,score,confirm\n" +
                        "FROM scoreinfo,teacher,student,courseinfo\n" +
                        "where scoreinfo.id = student.id and course_id = course_number and scoreinfo.teacher_id = teacher.id and scoreinfo.teacher_id = ? and scoreinfo.id in\n" +
                        "(\n" +
                        "\tSELECT student.id\n" +
                        "\tFROM teacher,student\n" +
                        "\tWHERE teacher.class_number = student.class_number and teacher.id = ?\n" +
                        ")";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                stmt.setString(2, id);
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
     * 提交审核信息
     *
     * @param id
     * @param cno
     * @return
     */
    public int confirmScore(String id, String cno) {
        int count = 0;
        Connection conn = null;  //创建用于连接数据库的Connection对象
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            String sql = "UPDATE scoreinfo\n" +
                    "SET confirm = 'ing'\n" +
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
     * 按姓名模糊查询分数
     *
     * @param searchCol
     * @param searchValue
     * @param id
     * @return
     */
    public ArrayList<ScoreInfo> teacherFindScoreByName(String searchCol, String searchValue, String id) {
        ArrayList<ScoreInfo> scoreInf = new ArrayList<ScoreInfo>();
        try {
            Connection conn = getConnection();  //连接状态
            if (conn != null) {
                PreparedStatement stmt;
                ResultSet rs;
                String sql = "SELECT scoreinfo.id,student_name,course_number,scoreinfo.teacher_id,teacher.teacher_name,scoreinfo.term,score,confirm\n" +
                        "FROM scoreinfo,teacher,student\n" +
                        "where scoreinfo.id = student.id and scoreinfo.teacher_id = teacher.id and student.student_name LIKE ? and scoreinfo.teacher_id = ? and scoreinfo.id in\n" +
                        "(\n" +
                        "\tSELECT student.id\n" +
                        "\tFROM teacher,student\n" +
                        "\tWHERE teacher.class_number = student.class_number and teacher.id = ?\n" +
                        ")";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, searchValue);
                stmt.setString(2, id);
                stmt.setString(3, id);
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
                    scoreInf.add(s1);
                }
                close(rs);
                close(stmt);
            }
            close(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return scoreInf;
    }

    /**
     * 按ID模糊查询分数
     *
     * @param searchCol
     * @param searchValue
     * @param id
     * @return
     */
    public ArrayList<ScoreInfo> teacherFindScoreById(String searchCol, String searchValue, String id) {
        ArrayList<ScoreInfo> scoreInf = new ArrayList<ScoreInfo>();
        try {
            Connection conn = getConnection();  //连接状态
            if (conn != null) {
                PreparedStatement stmt;
                ResultSet rs;
                String sql = "SELECT scoreinfo.id,student_name,course_number,scoreinfo.teacher_id,teacher.teacher_name,scoreinfo.term,score,confirm\n" +
                        "FROM scoreinfo,teacher,student\n" +
                        "where scoreinfo.id = student.id and scoreinfo.teacher_id = teacher.id and scoreinfo.id LIKE ? and scoreinfo.teacher_id = ? and scoreinfo.id in\n" +
                        "(\n" +
                        "\tSELECT student.id\n" +
                        "\tFROM teacher,student\n" +
                        "\tWHERE teacher.class_number = student.class_number and teacher.id = ?\n" +
                        ")";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, searchValue);
                stmt.setString(2, id);
                stmt.setString(3, id);
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
                    scoreInf.add(s1);
                }
                close(rs);
                close(stmt);
            }
            close(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return scoreInf;
    }

    /**
     * 列出不及格学生
     *
     * @param id
     * @return
     */
    public ArrayList<ScoreInfo> listFailures(String id) {
        ArrayList<ScoreInfo> scoreInf = new ArrayList<ScoreInfo>();
        try {
            Connection conn = getConnection();  //连接状态
            if (conn != null) {
                PreparedStatement stmt;
                ResultSet rs;
                String sql = "SELECT scoreinfo.id,student_name,course_number,scoreinfo.teacher_id,teacher.teacher_name,scoreinfo.term,score,confirm\n" +
                        "FROM scoreinfo,teacher,student\n" +
                        "where scoreinfo.id = student.id and scoreinfo.teacher_id = teacher.id and scoreinfo.score < 60 and scoreinfo.teacher_id = ? and scoreinfo.id in\n" +
                        "(\n" +
                        "\tSELECT student.id\n" +
                        "\tFROM teacher,student\n" +
                        "\tWHERE teacher.class_number = student.class_number and teacher.id = ?\n" +
                        ")";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                stmt.setString(2, id);
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
                    scoreInf.add(s1);
                }
                close(rs);
                close(stmt);
            }
            close(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return scoreInf;
    }

    public double[] statistics(String id, String cid) {
        double[] array = new double[4];
        double max = 0, min = 0, avg = 0, fail = 0, all = 0;
        try {
            Connection conn = getConnection();  //连接状态
            if (conn != null) {
                PreparedStatement stmt;
                PreparedStatement stmt1;
                ResultSet rs;
                ResultSet rs1;
                String sql = "SELECT MAX(score),MIN(score),AVG(score),COUNT(score)\n" +
                        "FROM scoreinfo,teacher,student,courseinfo\n" +
                        "where courseinfo.course_id = scoreinfo.course_number and scoreinfo.id = student.id and scoreinfo.teacher_id = teacher.id and scoreinfo.course_number = ? and scoreinfo.teacher_id = ? and scoreinfo.id in\n" +
                        "(\n" +
                        "\tSELECT student.id\n" +
                        "\tFROM teacher,student\n" +
                        "\tWHERE teacher.class_number = student.class_number and teacher.id = ?\n" +
                        ")";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, cid);
                stmt.setString(2, id);
                stmt.setString(3, id);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    max = rs.getInt(1);
                    min = rs.getInt(2);
                    avg = rs.getInt(3);
                    all = rs.getInt(4);
                }
                String sql1 = "SELECT COUNT(score)\n" +
                        "FROM scoreinfo,teacher,student,courseinfo\n" +
                        "where courseinfo.course_id = scoreinfo.course_number and scoreinfo.id = student.id and scoreinfo.teacher_id = teacher.id and score<60 and scoreinfo.course_number = ? and scoreinfo.teacher_id = ? and scoreinfo.id in\n" +
                        "(\n" +
                        "\tSELECT student.id\n" +
                        "\tFROM teacher,student\n" +
                        "\tWHERE teacher.class_number = student.class_number and teacher.id = ?\n" +
                        ")";
                stmt1 = conn.prepareStatement(sql1);
                stmt1.setString(1, cid);
                stmt1.setString(2, id);
                stmt1.setString(3, id);
                rs1 = stmt1.executeQuery();
                while (rs1.next()) {
                    fail = rs1.getInt(1);
                }
                array[0] = max;
                array[1] = min;
                array[2] = avg;
                array[3] = ((all - fail) / all)*100.0;
                close(rs);
                close(rs1);
                close(stmt);
                close(stmt1);
            }
            close(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
        return array;
    }
}
