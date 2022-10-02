import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.xml.stream.events.StartDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Locale;
import java.util.Objects;

public class search extends JFrame {

    static class searchFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new search();
        }
    }
    
    JFrame searchBox;
    JPanel upper;
    JPanel buttons;
    JPanel lower;
    ButtonGroup order;
    static JRadioButton up;
    static JRadioButton down;
    static JCheckBox caseSensitive;
    static JCheckBox circle;
    static JTextField inputS;
    JButton searchButton;
    JButton exitButton;
    JLabel searchContent;

    //status
    static String target = "";
    static Boolean orderU = false;
    static Boolean orderD = true;
    static Boolean circleS = true;
    static Boolean caseS = false;


    public search(){
        searchBox = new JFrame();
        searchBox.setTitle("Search");
        searchBox.setSize(400, 200);
        searchBox.addWindowListener(new updateT());

        //upper part
        upper = new JPanel();
        searchContent = new JLabel("Search content:");

        inputS = new JTextField();
        inputS.setPreferredSize(new Dimension(150 ,30));
        inputS.setText(target);

        searchButton = new JButton("Search");
        searchButton.addActionListener(new searching());
        exitButton = new JButton("Cancel");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBox.dispose();
            }
        });


        buttons = new JPanel();
        BoxLayout boxLayout = new BoxLayout(buttons, BoxLayout.Y_AXIS);
        buttons.setLayout(boxLayout);
        buttons.add(searchButton);
        buttons.add(Box.createVerticalStrut(7));
        buttons.add(exitButton);

        upper.setLayout(new FlowLayout());
        upper.setBorder(new EmptyBorder(15, 10, 10, 10));
        upper.add(searchContent);
        upper.add(Box.createHorizontalStrut(5));
        upper.add(inputS);
        upper.add(Box.createHorizontalStrut(10));
        upper.add(buttons);

        //lower part
        caseSensitive = new JCheckBox("Case sensitive");
        caseSensitive.addActionListener(new updateCa());
        caseSensitive.setSelected(caseS);
        circle = new JCheckBox("Circle");
        circle.addActionListener(new updateCi());
        circle.setSelected(circleS);
        up = new JRadioButton("UP");
        up.setSelected(orderU);
        up.addActionListener(new updateUp());
        down = new JRadioButton("DOWN");
        down.setSelected(orderD);
        down.addActionListener(new updateDown());

        order = new ButtonGroup();

        order.add(up);
        order.add(down);

        lower = new JPanel();
        lower.setLayout(new FlowLayout());
        lower.setBorder(new EmptyBorder(15, 5, 10, 5));
        lower.add(caseSensitive);
        lower.add(circle);
        lower.add(Box.createHorizontalStrut(40));
        lower.add(up);
        lower.add(down);


        searchBox.add(upper, BorderLayout.NORTH);
        searchBox.add(lower);
        searchBox.setResizable(false);
        searchBox.setVisible(true);


        //screen parameter
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width/2;
        int screenHeight = screenSize.height/2;
        int height = searchBox.getHeight();
        int width = searchBox.getWidth();
        searchBox.setLocation(screenWidth-width/2, screenHeight-height/2);

    }


    static class updateT implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {

        }

        @Override
        public void windowClosed(WindowEvent e) {
            target = inputS.getText();
        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }

    static class updateUp implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            orderU = up.isSelected();
            orderD = down.isSelected();
        }
    }

    static class updateDown implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            orderD = down.isSelected();
            orderU = up.isSelected();
        }
    }
    static class updateCi implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            circleS = circle.isSelected();
        }
    }
    static class updateCa implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            caseS = caseSensitive.isSelected();
        }
    }



    static class searching implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(Objects.equals(target, "")){
                JOptionPane.showMessageDialog(null, "No searching target.", "Error",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            searchMethod();
        }
    }


    static class searchingDown implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                down.setSelected(orderD);
                searchMethod();
                if(Objects.equals(target, "")){
                    JOptionPane.showMessageDialog(null, "No searching target.", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }


            } catch (NullPointerException a){
                JOptionPane.showMessageDialog(null, "No searching target.", "Error",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    static class searchingUp implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                up.setSelected(orderU);
                searchMethod();
                if(Objects.equals(target, "")){
                    JOptionPane.showMessageDialog(null, "No searching target.", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NullPointerException a){
                JOptionPane.showMessageDialog(null, "No searching target.", "Error",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }



    public static void searchMethod(){


        target = inputS.getText();
        String text;
        String targetS;

        if (caseSensitive.isSelected()) {
            text = notepad.input.getText();
            targetS = target;
        } else {
            text = notepad.input.getText().toUpperCase();
            targetS = target.toUpperCase(Locale.ROOT);
        }
        int currentPosition = notepad.input.getCaretPosition();
        int nextPosition;
        if (up.isSelected()) {
            if (notepad.input.getSelectedText() == null) {
                nextPosition = text.lastIndexOf(targetS, currentPosition - 1);
            } else {
                nextPosition = text.lastIndexOf(targetS, currentPosition - inputS.getText().length() - 1);
            }
            if (nextPosition != -1) {
                notepad.input.setCaretPosition(nextPosition);
                notepad.input.select(nextPosition, nextPosition + inputS.getText().length());
            } else {
                if (circle.isSelected()) {
                    nextPosition = text.lastIndexOf(targetS);
                    if (nextPosition != -1) {
                        notepad.input.setCaretPosition(nextPosition);
                        notepad.input.select(nextPosition, nextPosition + inputS.getText().length());
                    } else {
                        JOptionPane.showMessageDialog(null, "Cannot find “" + inputS.getText() + "“", "Error",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cannot find “" + inputS.getText() + "“", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } else {
            nextPosition = text.indexOf(targetS, currentPosition);
            if (nextPosition != -1) {
                notepad.input.setCaretPosition(nextPosition + inputS.getText().length());
                notepad.input.select(nextPosition, nextPosition + inputS.getText().length());
            } else {
                if (circle.isSelected()) {
                    nextPosition = text.indexOf(targetS);
                    if (nextPosition != -1) {
                        notepad.input.setCaretPosition(nextPosition + inputS.getText().length());
                        notepad.input.select(nextPosition, nextPosition + inputS.getText().length());
                    } else {
                        JOptionPane.showMessageDialog(null, "Cannot find “" + inputS.getText() + "“", "Error",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cannot find “" + inputS.getText() + "“", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}
