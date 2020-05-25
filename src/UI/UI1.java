package UI;

import Client.LoginClient;

import java.awt.*;
import java.awt.event.*;
public class UI1{
    public UI1(){
        dispaly();
    }//学生登录界面
	Button b = new Button();
    Frame f = new Frame();
    TextField ID,Name,Class = new TextField();
    Label id,name,cl,waring = new Label();
    LoginClient.LoginListener loginlistener = new LoginClient.LoginListener();//创建类进行参数监听
    public void dispaly(){
		f=new Frame("Java签到系统");
        f.setSize(700,500);
        f.setLayout(null);
        f.setBackground(Color.lightGray);
        f.setVisible(true); 
        b=new Button("登录");
        Font fo = new Font("宋体",Font.BOLD,13);
		b.setFont(fo);
        ID=new TextField();
        Name=new TextField();
        Class=new TextField();
        id=new Label("学号：");
        waring=new Label("请按照：201XXXXXXXXXX，张三，计科XXXX  形式填写");
        name=new Label("姓名：");
        cl=new Label("班级：");
        b.setBounds((f.getWidth()+300)/2,(f.getHeight()-100)/2,100,50);
        b.addActionListener(loginlistener);
        waring.setBounds((f.getWidth()-300)/2,(f.getHeight()+300)/2,2000,50);
        ID.setBounds((f.getWidth()-200)/2,(f.getHeight()-200)/2,200,30);
        id.setBounds((f.getWidth()-300)/2,(f.getHeight()-200-10)/2,200,50);
        Name.setBounds((f.getWidth()-200)/2,(f.getHeight()-100)/2,200,30);
        name.setBounds((f.getWidth()-300)/2,(f.getHeight()-120)/2,200,50);
        Class.setBounds((f.getWidth()-200)/2,(f.getHeight()-0)/2,200,30);
        cl.setBounds((f.getWidth()-300)/2,(f.getHeight()-0)/2,200,30);
        f.add(ID);
        f.add(Name);
        f.add(Class);
        f.add(id);
        f.add(name);
        f.add(cl);
        f.add(b);
        f.add(waring);
        f.addWindowListener(new WinClose());
        //跨类传值
        loginlistener.SetTextId(ID);
        loginlistener.SetTextName(Name);
        loginlistener.SetTextClass(Class);
        loginlistener.SetTextFrame(f);
    }
}
class WinClose extends WindowAdapter{
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
