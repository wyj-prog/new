import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;


public class notepad extends JFrame{

    public static void main(String[] args) {
        notepad notepad = new notepad();
        notepad.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        new notepad();
    }





    //Input Area
    public static JTextArea input;

    //The menu bar at the top
    public static JMenuBar topMenu;

    //File
    public static JMenu file;
    public static JMenuItem New;
    public static JMenuItem open;
    public static JMenuItem save;
    public static JMenuItem exit;

    //Search
    public static JMenu search;
    public static JMenuItem searchItem;
    public static JMenuItem previous;
    public static JMenuItem next;

    //Manage
    public static JMenu manage;
    public static JMenuItem selectAll;
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
    public static JTextField timeAndDate;
    public static JTextField leftPart;

    //time&date


    public notepad() {

        super("Notepad--");

        this.setSize(600,600);


        //Top menubar
        topMenu = new JMenuBar();


        //File Menu
        file = new JMenu("File");

        New = new JMenuItem("New            ");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");

        file.add(New);
        file.add(open);
        file.add(save);
        file.add(exit);

        topMenu.add(file);

        //Search Menu
        search = new JMenu("Search");

        searchItem = new JMenuItem("Search          ");
        previous = new JMenuItem("Previous");
        next = new JMenuItem("Next");

        search.add(searchItem);
        search.add(previous);
        search.add(next);

        topMenu.add(search);

        //View Menu

        view = new JMenu("View");

        statusBarInvisibility = new JMenuItem("Status Bar         √");
        statusBarInvisibility.addActionListener(new funtions.statusBarInvisibility());
        scaleUp = new JMenuItem("Scale up");
        scaleDown = new JMenuItem("Scale down");

        view.add(statusBarInvisibility);
        view.add(scaleUp);
        view.add(scaleDown);

        topMenu.add(view);

        //Manage Menu
        manage = new JMenu("Manage");

        selectAll = new JMenuItem("Select all");
        copy = new JMenuItem("Copy          ");
        paste = new JMenuItem("Paste");
        cut = new JMenuItem("Cut");
        print = new JMenuItem("Print");
        export = new JMenuItem("Export");

        manage.add(selectAll);
        manage.add(copy);
        manage.add(paste);
        manage.add(cut);
        manage.add(print);
        manage.add(export);

        topMenu.add(manage);


        //Help Menu

        help= new JMenu("Help");

        about = new JMenuItem("About            ");
        about.addActionListener(new funtions.aboutFrame());

        help.add(about);

        topMenu.add(help);



        //topMenu construct

        this.add(topMenu, BorderLayout.NORTH);


        //Input area
        input = new JTextArea();

        JScrollPane scroller = new JScrollPane();
        scroller.setBounds(20,20,100,50);
        scroller.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroller.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setViewportView(input);

        this.add(scroller);

        //Status Bar
        statusBar = new JPanel();


        //left part of status bar
        leftPart = new JTextField("Line: 1, Column: 1");
        leftPart.setLayout(null);
        leftPart.setPreferredSize(new Dimension(270,30));
        leftPart.setEditable(false);
        leftPart.setBackground(new Color(238,238,238));
        leftPart.setBorder(new MatteBorder(0, 0, 0, 0, new Color(238, 238,
                238)));


        //cursor position
        input.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                try {

                    int offset = e.getDot() ;
                    int row = input.getLineOfOffset(offset);
                    int column = e.getDot() - input.getLineStartOffset(row);
                    leftPart.setText("Line: " + (row + 1) + ", Column: " + (column+1));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        statusBar.add(leftPart);

        //time&date
        //Real-time display
        timeAndDate = new JTextField();
        timeAndDate.addActionListener(new funtions.TimeActionListener());
        timeAndDate.setPreferredSize(new Dimension(300,30));
        timeAndDate.setEditable(false);
        timeAndDate.setBackground(new Color(238,238,238));

        timeAndDate.setHorizontalAlignment(JTextField.RIGHT);

        timeAndDate.setBorder(new MatteBorder(0, 0, 0, 0, new Color(238, 238,
                238)));

        statusBar.add(timeAndDate);

        this.add(statusBar, BorderLayout.SOUTH);


        //screen parameter
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width/2;
        int screenHeight = screenSize.height/2;
        int height = this.getHeight();
        int width = this.getWidth();
        this.setLocation(screenWidth-width/2, screenHeight-height/2);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }





}
