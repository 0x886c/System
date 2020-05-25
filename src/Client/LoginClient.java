package Client;

import UI.UI1;
import UI.UI2;
import UI.UI4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class LoginClient extends Thread{
    static Socket socket = null;//静态socket对象可以在LoginClient整个类中使用
    public static void main(String[] args){
        new UI1();//客户端登陆界面显示
    }
    public void run(){//获取来自服务端的输入流
        try {
            String string = new String();
            InputStream inputStream = socket.getInputStream();//获得输入流
            int len = 0;
            byte[] buf = new byte[1024];
            //将字节流转化成字符串，获得服务端发送的信息
            if ((len=inputStream.read(buf))!=-1){
                string = new String(buf,0,len);
            }
            //System.out.println(string);
            if (string.equals("已成功签到")){//服务端确定学生输入信息与数据库中内容一致
                new UI4();//弹出签到成功界面
            }else {
                new UI2();//弹出信息输入错误界面
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static class LoginListener implements ActionListener{
        TextField textFieldId,textFieldName,textFieldClass;
        String Stuid,Stuname,Stuclass;
        Frame loginframe = null;

        //通过外部类的方法完成UI1中的参数传递
        //一下定义的方法分别获得监听的Id,Name,Class,Frame
        public void SetTextId(TextField textField){
            textFieldId = textField;
        }
        public void SetTextName(TextField textField){
            textFieldName = textField;
        }
        public void SetTextClass(TextField textField){
            textFieldClass = textField;
        }
        public void SetTextFrame(Frame frame){
            loginframe = frame ;
        }

        //监听界面输入并以字节流的形式将数据发送至服务端
        @Override
        public void actionPerformed(ActionEvent e) {
            //获得字符串类型的数据
            Stuid = textFieldId.getText();
            Stuname = textFieldName.getText();
            Stuclass = textFieldClass.getText();
            //测试是否获得数据
            System.out.println(Stuid+'\n'+Stuname+'\n'+Stuclass);
            try {
                //这里的host是我局域网中当作服务端的主机的静态IP，根据自己情况输入服务端IP
                socket = new Socket("192.168.1.100",7777);
                OutputStream outputStream = socket.getOutputStream();
                //这里字节流中加入了本机IP以边签到时间的录入
                outputStream.write((Stuid+" "+Stuname+" "+Stuclass+" "+socket.getInetAddress().getHostAddress()).getBytes());
            } catch (UnknownHostException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            //监听时启动线程
            LoginClient loginClient = new LoginClient();
            loginClient.start();
        }
    }
}
