//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package UI;

import Filetransfer.FiletransferClient;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.PrintStream;

public class UI5 {
    FileDialog fd;
    Frame window;
    FiletransferClient.SendFile sendFile = new FiletransferClient.SendFile();

    public static void main(String[] args) {
        new UI5().display();
    }

    public void display() {
        try {
            this.fd = new FileDialog(this.window, "Open File", 0);
            this.fd.setVisible(true);
            String string = fd.getDirectory()+fd.getFile();
            System.out.println(string);
/*            sendFile.SetFileLocal(string);
            sendFile.SetFrame(window);*/
        } catch (Exception var3) {
        }

    }

}
