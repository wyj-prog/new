import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class search extends JFrame {

    static class searchFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            search search = new search();
        }
    }

    static class searchFrameP implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            search search = new search();

        }
    }

    static class searchFrameN implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            search search = new search();
        }
    }


    JPanel upper;
    static JTextField input;
    static JButton searchButton;
    JLabel searchContent;


    public search(){
        this.setTitle("Search");
        this.setSize(500, 250);

        upper = new JPanel();
        searchContent = new JLabel("Search content:");

        input = new JTextField();
        input.setPreferredSize(new Dimension(200 ,30));

        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100 ,30));

        upper.setLayout(new FlowLayout());
        upper.setBorder(new EmptyBorder(10, 10, 10, 10));
        upper.add(searchContent);
        upper.add(input);
        upper.add(searchButton);

        this.add(upper, BorderLayout.NORTH);
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
