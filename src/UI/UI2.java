package UI;

import java.awt.*;
import java.awt.event.*;

public class UI2 implements ActionListener {      //登录信息不准确问题弹出界面
	Button b;
	Frame f;
	Label waring;
	public UI2(){
		display();
	}
	public void display(){
		f=new Frame("error");
        f.setSize(750,400);
        f.setLayout(null);
        f.setBackground(Color.lightGray);
        f.setVisible(true);
        b=new Button("确认并返回");
        Font fo = new Font("宋体",Font.BOLD,13);
		b.setFont(fo);
		waring=new Label("请输入正确的，13位学号，姓名，专业加班级号（不加汉字班）形式输入");
		waring.setBounds((f.getWidth()-670)/2,(f.getHeight()-200)/2,2000,50);
		Font fon = new Font("宋体",Font.BOLD,20);
		waring.setFont(fon);
		b.setBounds((f.getWidth()-100)/2,(f.getHeight()+100)/2,100,50);
		b.addActionListener(this);
        f.add(b);
        f.add(waring);
        f.addWindowListener(new WinClose());
    }
	public void actionPerformed(ActionEvent e) {
		f.dispose();//界面关闭但是其他窗口保留
	}
}
