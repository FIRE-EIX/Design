package Utils;

import java.sql.*;

public class Tools {
    public Tools() {
    }

    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection("jdbc:mysql://localhost:3306/root1?characterEncoding=utf-8", "root", "123456");
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");  //注册数据库驱动
            String url = "jdbc:mysql://localhost:3306/student_manage?serverTimezone=UTC";  //定义连接数据库的url
            String password = "123456";
            String username = "root";
            con = DriverManager.getConnection(url,username,password);  //获取数据库连接
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;  //返回一个连接
    }
}
