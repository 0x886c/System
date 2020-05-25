package Serve;


import Datebase.SysInsertSignoutTime;
import Datebase.SysInsertTime;
import Datebase.SysSearch;
import Filetransfer.FiletransferClient;
import UI.ServeUi;
import UI.UI1;
import UI.UI2;
import UI.UI4;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SysServe  extends Thread{
    static ServerSocket serverSocket;
    //服务端会收到多个客户端的请求，所以这里用了socket类型集合
    static List<Socket> list = new ArrayList<>();
    ServeUi serveUi = new ServeUi();//跨类传值
    public static void main(String[] args) {
        SysServe sysServe = new SysServe();
        sysServe.run();
    }
    public void run(){
        try {
            System.out.println("正在等待用户连接");
            //交互程序间端口要一致
            serverSocket = new ServerSocket(7777);
            while (true){
                Socket socket = serverSocket.accept();//等待服务端对象连接
                System.out.println("成功连接");
                list.add(socket);
                //启动线程
                new ReadThreadFromClient(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public class ReadThreadFromClient extends Thread{
        InputStream inputStream = null;
        public ReadThreadFromClient(Socket socket){
            try {
                //获取服务端的IP地址
                String ipstring = socket.getInetAddress().getHostAddress();
                inputStream = socket.getInputStream();
                byte[] buf = new byte[1024];
                int len = 0;
                //获得字节流
                if ((len=inputStream.read(buf))!=-1){
                    System.out.println("服务器监听："+'\t'+new String(buf,0,len));
                }
                if (new SysSearch().main(buf)){//判断监听的信息是否与数据库中的数据一致
                    //插入签到时间
                    new SysInsertTime().main(ipstring,buf);
                    //将获得的学生签到信息传到服务端UI中的AreaText中
                    serveUi.AddAreaText(new String(buf,0,len)+"已签到");
                    //向客户端发送成功签到信息
                    SendMessClient("已成功签到",socket);
                } else {
                    //向客户端发送输入信息有误
                    SendMessClient("Login端信息输入有误",socket);
                    new UI2();//登录信息输入错误提示页面弹出
                }
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void SendMessClient(String message,Socket socket){//向其他客户发送数据
        if(socket!=null&&socket.isConnected()){//确保客户端没有掉线
            try {
                OutputStream outputStream=socket.getOutputStream();//输入与输出均要以流的方式进行
                outputStream.write(message.getBytes());//输出字节流
                outputStream.flush();//刷新
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
