package Filetransfer;

import Datebase.SysInsertSignoutTime;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//文件传输线程服务端
public class FiletransferServe extends Thread {
    static List<Socket> list = new ArrayList<>();
    static ServerSocket serverSocket = null;
    public FiletransferServe(){//服务端对象的创建和指定端口的设置
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        super.run();
        try {
            System.out.println("用户正在连接");
            while (true){
                Socket socket=serverSocket.accept();//利用accept方法阻塞线程，线程会等待socket的输入流
                System.out.println("成功连接");//服务器显示已上线，即线程成功运行
                list.add(socket);//增加线程数量并记录
                new ReadFile(socket).run();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public class ReadFile extends Thread{
        DataInputStream dataInputStream = null;
        FileOutputStream fileOutputStream = null;
        String ipstring = null;
        public ReadFile(Socket socket){
            try {
                ipstring = socket.getInetAddress().getHostAddress();
                dataInputStream = new DataInputStream(socket.getInputStream());
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        public void run(){
            try {
                String fileName = dataInputStream.readUTF();//获得数据输入流
                File directory = new File("D:\\FiletransferTest");//文件保存路径
                if(!directory.exists()) {
                    directory.mkdir();
                }
                //创建文件
                File file = new File(directory.getAbsolutePath() + File.separatorChar +fileName);
                fileOutputStream = new FileOutputStream(file);
                byte[] bytes = new byte[1024];
                int length = 0;
                //字节流获得文件信息
                while((length = dataInputStream.read(bytes, 0, bytes.length)) != -1) {
                    fileOutputStream.write(bytes, 0, length);//将收到的字节流写入文件
                    fileOutputStream.flush();
                }
                System.out.println("文件接收成功 [File Name：" + fileName + "]");
                System.out.println("ipstring:"+ipstring);//检测获得的客户端IP地址
                new SysInsertSignoutTime().main(ipstring);//根据IP地址更新签退时间
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //关闭流
                try {
                    if(fileOutputStream != null)
                        fileOutputStream.close();
                    if(dataInputStream != null)
                        dataInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }





    public static void main(String[] args) {

        try {

            FiletransferServe filetransferServe =new FiletransferServe(); // 启动客户端
            filetransferServe.run();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
