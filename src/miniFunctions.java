import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class miniFunctions {
    //Dynamic time display
    static class TimeActionListener implements ActionListener {
        public TimeActionListener(){
            Timer t=new Timer(1000, this);
            t.start();
        }
        @Override
        public void actionPerformed(ActionEvent ae){
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            notepad.timeAndDate.setText(formatter.format(new Date()));
        }
    }

    //Status bar invisibility
    static class statusBarInvisibility implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(notepad.statusBarInvisibility.getText().equals("Status Bar         √")){
                notepad.statusBar.setVisible(false);
                notepad.statusBarInvisibility.setText("Status Bar           ");
            }else{
                notepad.statusBar.setVisible(true);
                notepad.statusBarInvisibility.setText("Status Bar         √");
            }
        }
    }


    static class PrintButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Print();
        }
    }

    public static int getScreenWidth(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        return screenWidth;
    }

    public static int getScreenHeight(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        return screenHeight;
    }
}
