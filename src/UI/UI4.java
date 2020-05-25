package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//签到成功界面
public class UI4{
    Button button = new Button("确定");
    Frame frame = new Frame("签到成功");
    Label label = new Label("签到成功");
    Font fo = new Font("宋体",Font.BOLD,26);
    public UI4(){
        display();
    }
    public void display(){
        frame.setSize(600,400);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(button);
        frame.add(label);
        frame.setBackground(Color.lightGray);
        button.setBounds(210,300,150,50);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        label.setBounds(200,50,600,200);
        label.setBackground(Color.lightGray);
        label.setFont(fo);
    }
}