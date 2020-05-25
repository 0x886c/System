package UI;

import Filetransfer.FiletransferClient;

import java.awt.*;
public class UI3{     //提交文件退出界面
	Button b = new Button("提交并退出");
    Frame f;
    TextField Way;
    Label way,waring;
    public UI3(){
        display();
    }
    //外部类实现跨类监听
    FiletransferClient.SendFile sendFile = new FiletransferClient.SendFile();
    public void display(){
		f=new Frame("Java签到系统");
        f.setSize(700,500);
        f.setLayout(null);
        f.setBackground(Color.lightGray);
        f.setVisible(true); 
        b=new Button("提交并退出");
        Font fo = new Font("宋体",Font.BOLD,13);
        b.setFont(fo);
        way=new Label("文件路径：");
        Way=new TextField();
        way.setBounds((f.getWidth()-500)/2,(f.getHeight()-200-10)/2,200,50);
        Way.setBounds((f.getWidth()-380)/2,(f.getHeight()-200)/2,400,30);
        waring=new Label("例如：           D:\\\\Java\\\\workspace\\\\文件名");
        waring.setBounds((f.getWidth()-300)/2,(f.getHeight()+300)/2,2000,50);
		b.setBounds((f.getWidth()-100)/2,(f.getHeight()+100)/2,100,50);
		f.add(b);
		f.add(Way);
		f.add(way);
		f.add(waring);
        f.addWindowListener(new WinClose());

        //跨类传递参数
        sendFile.SetFileLocal(Way);
        sendFile.SetFrame(f);
        b.addActionListener(sendFile);
    }
}

