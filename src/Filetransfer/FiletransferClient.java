package Filetransfer;

import UI.UI3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class FiletransferClient extends Thread{
    static Socket socket = null;
    public static void main(String[] args){
        new UI3();
    }
    public static class SendFile implements ActionListener {
        TextField textFileName;
        Frame frame;
        String filelocal = new String();
        String string = new String();
        DataOutputStream dataOutputStream = null;//从本机获得文件-文件输入流
        FileInputStream fileInputStream = null;//将数据传送至客户端-数据输出流

        public void SetFileLocal(TextField textField){
            textFileName = textField;
        }
        public void SetFrame(Frame f){
            frame = f;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            filelocal = textFileName.getText();
            //System.out.println(filelocal);
            try
            {
                socket = new Socket("192.168.1.111",8888);
                string = filelocal.replaceAll("\\\\","\\\\\\\\");
                System.out.println(string);
                File file = new File(string);//本机文件路径**注意转义符号**
                if(file.exists()){
                    //先读取传入数据的文件流再以数据流的形式发送
                    fileInputStream= new FileInputStream(file);
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataOutputStream.writeUTF(file.getName());
                    dataOutputStream.flush();
                    dataOutputStream.writeLong(file.length());
                    dataOutputStream.flush();
                    byte[] bytes = new byte[1024];
                    int length = 0;
                    long progress = 0;
                    while ((length = fileInputStream.read(bytes,0,bytes.length))!=-1){
                        dataOutputStream.write(bytes,0,length);
                        dataOutputStream.flush();
                        progress+=length;
                        System.out.println("| " + (100*progress/file.length()) + "% |");
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (fileInputStream!=null){
                    try {
                        fileInputStream.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                if (dataOutputStream!=null){
                    try {
                        dataOutputStream.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            FiletransferClient filetransferClient1 = new FiletransferClient();
            filetransferClient1.start();
        }
    }

}
