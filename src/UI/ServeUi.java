package UI;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServeUi {
    public ServeUi(){
        dispaly();
    }

    //在文本域中增加监听内容

    public void AddAreaText(String string){
        textArea.append(string+'\n');
    }

    Frame frame = new Frame("服务端");
    TextArea textArea = new TextArea("服务端监听"+'\n',5,4,TextArea.SCROLLBARS_VERTICAL_ONLY);
    public void dispaly(){
        frame.setSize(750,500);
        frame.setLayout(null);
        frame.setBackground(Color.lightGray);
        frame.setVisible(true);
        textArea.setSize(375,250);
        textArea.setBackground(Color.lightGray);
        textArea.setBounds(10,50,700,400);
        frame.add(textArea);
        textArea.setVisible(true);
        frame.addWindowListener(new WinClose());
    }
    class WinClose extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            System.exit(0);
        }
    }
}
