package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDao {
    // ���Connection��������飬���鱻�������ӳ�
    static ArrayList<Connection> list = new ArrayList<Connection>();

    /**
     * @discription 获得链接
     */
    public synchronized static Connection getConnection() {
        Connection con = null;
        // 如果连接池中有对象
        if (list.size() > 0) {
            return list.remove(0);
        }
        // 连接池没有对象，创建连接放到连接池中
        else {
            for (int i = 0; i < 5; i++) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/student_manage?serverTimezone=UTC", "root", "123456");
                    list.add(con);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        return list.remove(0);
    }

    /**
     * @discription 关闭结果集
     */
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * @discription 关闭预处理
     */
    public static void close(PreparedStatement pst) {
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * @discription 关闭连接
     */
    public synchronized static void close(Connection con) {
        if (con != null)
            list.add(con);
    }

    /**
     * @discription 关闭所有连接有关的对象
     */
    public static void close(ResultSet rs, PreparedStatement ps, Connection con) {
        close(rs);
        close(ps);
        close(con);
    }

    /**
     * @param sql
     * @param param
     * @return
     */
    public boolean upadateByParams(String sql, Object param[]) {

        boolean flag = false;
        Connection con = getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            if (param != null) {
                for (int i = 1; i <= param.length; i++) {
                    ps.setObject(i, param[i - 1]);
                }
            }
            int n = ps.executeUpdate();

            if (n > 0)
                flag = true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            close(null, ps, con);
        }
        return flag;
    }

    /**
     * @param sql
     * @param param
     * @return
     */
    public boolean BatchUpadateByParams(String sql, Object param[][]) {
        Connection con = getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {//������¼
                    for (int j = 1; j <= param[i].length; j++) {//ÿ����¼��Ĳ����滻
                        ps.setObject(j, param[i][j - 1]);
                    }
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            close(null, ps, con);
        }
    }

    /**
     * @param sql
     * @param param
     * @return
     */
    public static List<Map<String, Object>> select(String sql, Object[] param) {
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            ps = con.prepareStatement(sql);
            if (param != null) {
                for (int i = 1; i <= param.length; i++) {
                    ps.setObject(i, param[i - 1]);
                }
            }
            rs = ps.executeQuery();
            ResultSetMetaData rm = rs.getMetaData();
            //����
            int count = rm.getColumnCount();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= count; i++) {
                    //keyΪ������valueΪ��ֵ
                    map.put(rm.getColumnName(i).toLowerCase(), rs.getObject(rm.getColumnName(i)));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            close(rs, ps, con);
        }
        return list;
    }

    /**
     * ������һ��id
     */
    public int getLastId(String sql, String sql1, Object[] param) {
        Connection con = getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        int id = 0;
        try {
            ps = con.prepareStatement(sql);
            if (param != null) {
                for (int i = 1; i <= param.length; i++) {
                    ps.setObject(i, param[i - 1]);
                }
            }
            ps.executeUpdate();
            ps1 = con.prepareStatement(sql1);
            rs = ps1.executeQuery();
            if (rs.next())
                id = rs.getInt(1);
            close(ps1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, con);
        }
        return id;
    }

    /**
     * @discription ��ȡһ��ʱ�䴮��ID
     */
    public static String getStringID() {
        String id = null;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        id = sdf.format(date);
        return id;
    }
}
