import javax.swing.*;



public class notepad{

    public static void main(String[] args) {
        notepad();
    }


    //The window
    public static JFrame notepad;

    //Input Area
    public static JTextArea input;

    //The menu bar at the top
    public static JMenuBar topMenu;

    //File
    public static JMenu file;
    public static JMenuItem New;
    public static JMenuItem open;
    public static JMenuItem save;

    //Search
    public static JMenu search;
    public static JMenuItem searchItem;
    public static JMenuItem previous;
    public static JMenuItem next;

    //Manage
    public static JMenu manage;
    public static JMenuItem copy;
    public static JMenuItem paste;
    public static JMenuItem cut;
    public static JMenuItem print;
    public static JMenuItem export;

    //View
    public static JMenu view;
    public static JMenuItem statusBarInvisibility;
    public static JMenuItem scaleUp;
    public static JMenuItem scaleDown;

    //Help
    public static JMenu help;
    public static JMenuItem about;

    //Status Bar
    public static JPanel statusBar;
    public static JLabel timeAndDate;



    public static void notepad() {
        notepad = new JFrame();
        notepad.setTitle("Notepad--");
        notepad.setSize(500,300);
        notepad.setVisible(true);
        notepad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        topMenu = new JMenuBar();
    }
}
