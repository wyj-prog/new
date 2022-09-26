import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class funtions {
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

    static class aboutFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            about about = new about();
        }
    }


}
