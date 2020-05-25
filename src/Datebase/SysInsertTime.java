package Datebase;

import java.sql.*;
import java.util.Date;

public class SysInsertTime {
    public void main(String ipstring,byte[] byets) throws SQLException {
        Connection con = null;//声明Connection对象
        String url = "jdbc:mysql://localhost:3306/test";//URL指向要访问的数据库名test
        String user = "root";//MySQL配置时的用户名
        String password = "123456";//MySQL配置时的密码
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            //判断是否连接至数据库
            if (!con.isClosed()){
                System.out.println("已成功连接至本机数据库");
            }
            Statement statement = con.createStatement();//创建statement类对象，用来执行SQL语句
            //获取当前时间
            Date date=new Date();
            Timestamp timeStamp = new Timestamp(date.getTime());
            System.out.println(timeStamp);

            //捕捉字节数组并转化为字符串组，有助于数据处理
            String[] strings = new String(byets).split(" ");
            //转换SQL语句
            //String sql = "update studenttable set "+string+'='+timeStamp+" where id = "+strings[0]+' ';
            //更新登入学生的签到时间
            String sql = "update studenttable set signintime ='"+timeStamp+"' where id ="+strings[0]+"";
            //执行SQL
            statement.executeUpdate(sql);
            //更新登入学生的IP
            sql = "update studenttable set ip = '"+ipstring+"' where id = "+strings[0]+"";
            statement.executeUpdate(sql);
        }  catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally { System.out.println("数据库已完成签到时间的更新操作");
        }
    }
}
