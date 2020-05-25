package Datebase;

import java.sql.*;
import java.util.Date;

public class SysInsertSignoutTime {
    public void main(String ipstring) throws SQLException {
        Connection con = null;//声明Connection对象
        String url = "jdbc:mysql://localhost:3306/test";//URL指向要访问的数据库名login
        String user = "root";//MySQL配置时的用户名
        String password = "123456";//MySQL配置时的密码
        String driver = "com.mysql.jdbc.Driver";
        try {
            //加载驱动
            Class.forName(driver);
            //连接MySql
            con = DriverManager.getConnection(url,user,password);
            if (!con.isClosed()){
                System.out.println("已成功连接至本机数据库");
            }
            Statement statement = con.createStatement();//创建statement类对象，用来执行SQL语句

            //获取当前时间
            Date date=new Date();
            Timestamp timeStamp = new Timestamp(date.getTime());
            System.out.println(timeStamp);

            //转换SQL语句
            String sql = "update studenttable set signouttime = '"+timeStamp+"' where ip = '"+ipstring+"'";
            //sql = "update studenttable set signouttime = '"+timeStamp+"' where ip = '100.64.15.44'";
            //String sql = "update studenttable set signouttime = '"+timeStamp+"' where ip = "+string+"";
            statement.executeUpdate(sql);//执行SQL
        }  catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("数据库已完成签退时间的更新操作");
        }
    }
}
