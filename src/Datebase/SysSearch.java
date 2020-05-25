package Datebase;

import java.nio.charset.StandardCharsets;
import java.sql.*;

public class SysSearch {

    public boolean main(byte[] bytes) {
        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名login
        String url = "jdbc:mysql://localhost:3306/test";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "123456";
        try
        {
            //加载驱动程序
            Class.forName(driver);
            //连接MySQL数据库
            con = DriverManager.getConnection(url,user,password);
            //判断是否成功连接至数据库
            if(!con.isClosed())
                System.out.println("已成功连接至本机数据库");
            //创建statement类对象，用来执行SQL语句
            Statement statement = con.createStatement();
            //截取学号为有效信息，作为查询依据
            String s = new String(bytes);
            //防止有人乱输学号姓名班级
            if (s.length()<=13){
                return false;
            }
            //用空格分开学号姓名班级
            String[] strings = s.split(" ");
            //测试语句
            //System.out.println(strings[2]);
            String sql;
            //sql = "select * from studenttable";
            sql = "select * from studenttable where id ='"+strings[0]+"'";
            //ResultSet类，用来存放获取的结果集
            ResultSet rs = statement.executeQuery(sql);
            String Person_id = null;
            String Person_name = null;
            String Person_class = null;
            while (rs.next()) {
                //获取id这列数据
                Person_id = rs.getString("id").trim();
                //获取name这列数据
                Person_name = rs.getString("name").trim();
                //获取class这列数据
                Person_class = rs.getString("class").trim();
            }
            //判断是否学生姓名与学号一一对应，返回方法的布尔型值
            if (Person_name.equals(strings[1])){//若信息正确则在服务端弹出签到成功信息
                System.out.println("学号："+Person_id+'\n'
                        +"姓名："+Person_name+'\n'
                        +"班级："+Person_class+'\n'+"已签到");
                return true ;
            } else {
                return false;
            }
            //待解决问题，此处class属性的比较返回值总是false，大概率是因为字符串转化为字节后再转化为字符串导致数据不一致
            /*if (Person_class.equals(strings[2])) {
                System.out.println("2");
            }*/
        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        finally {
            System.out.println("数据库已完成查找操作");
        }
        return false;
    }
}
