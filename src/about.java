import javax.swing.*;
import java.awt.*;

public class about extends JFrame {



    JLabel mainText;

    public about(){
        mainText = new JLabel("<html><body>"+"Beneath this mask there is more than flesh."+"<br>"+
                "Beneath this mask there is an idea, "+ "<br>" +
                "Mr. Creedy, and ideas are bulletproof."+"<body></html>", JLabel.CENTER);


        this.setTitle("About");
        this.setSize(300, 150);
        this.add(mainText, BorderLayout.CENTER);
        this.setVisible(true);

        //screen parameter
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width/2;
        int screenHeight = screenSize.height/2;
        int height = this.getHeight();
        int width = this.getWidth();
        this.setLocation(screenWidth-width/2, screenHeight-height/2);
    }
}
