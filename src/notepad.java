import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class notepad extends JFrame{

    public static void main(String[] args) {
        notepad notepad = new notepad();
        notepad.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

    //popMenu
    public static JPopupMenu rClick;
    public static JMenuItem selectAllR;
    public static JMenuItem copyR;
    public static JMenuItem pasteR;
    public static JMenuItem cutR;

    //Status Bar
    public static JPanel statusBar;
    public static JTextField timeAndDate;
    public static JTextField leftPart;




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
        searchItem.addActionListener(new search.searchFrame());
        previous = new JMenuItem("Previous");
        previous.addActionListener(new search.searchFrameP());
        next = new JMenuItem("Next");
        next.addActionListener(new search.searchFrameN());

        search.add(searchItem);
        search.add(previous);
        search.add(next);

        topMenu.add(search);

        //View Menu

        view = new JMenu("View");

        statusBarInvisibility = new JMenuItem("Status Bar         √");
        statusBarInvisibility.addActionListener(new miniFunctions.statusBarInvisibility());
        scaleUp = new JMenuItem("Scale up");
        scaleDown = new JMenuItem("Scale down");

        view.add(statusBarInvisibility);
        view.add(scaleUp);
        view.add(scaleDown);

        topMenu.add(view);

        //Manage Menu
        manage = new JMenu("Manage");

        selectAll = new JMenuItem("Select all           ");
        selectAll.addActionListener(new miniFunctions.SelectAllButton());
        copy = new JMenuItem("Copy");
        copy.addActionListener(new miniFunctions.CopyButton());
        paste = new JMenuItem("Paste");
        paste.addActionListener(new miniFunctions.PasteButton());
        cut = new JMenuItem("Cut");
        cut.addActionListener(new miniFunctions.CutButton());
        print = new JMenuItem("Print");
        print.addActionListener(new miniFunctions.PrintButton());
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
        about.addActionListener(new about.aboutFrame());

        help.add(about);

        topMenu.add(help);



        //topMenu construct

        this.add(topMenu, BorderLayout.NORTH);


        //Input area
        input = new JTextArea();

        input.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON3){
                    rClick.show(notepad.this,e.getX(), e.getY()+30);
                }
            }
        });

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
        timeAndDate.addActionListener(new miniFunctions.TimeActionListener());
        timeAndDate.setPreferredSize(new Dimension(300,30));
        timeAndDate.setEditable(false);
        timeAndDate.setBackground(new Color(238,238,238));

        timeAndDate.setHorizontalAlignment(JTextField.RIGHT);

        timeAndDate.setBorder(new MatteBorder(0, 0, 0, 0, new Color(238, 238,
                238)));

        statusBar.add(timeAndDate);

        this.add(statusBar, BorderLayout.SOUTH);

        //pop menu
        rClick = new JPopupMenu();

        selectAllR = new JMenuItem("Select all           ");
        copyR = new JMenuItem("Copy");
        pasteR = new JMenuItem("Paste");
        cutR = new JMenuItem("Cut");

        rClick.add(selectAllR);
        rClick.add(copyR);
        rClick.add(pasteR);
        rClick.add(cutR);

        //screen parameter
        int height = this.getHeight();
        int width = this.getWidth();
        this.setLocation(miniFunctions.getScreenWidth()/2-width/2, miniFunctions.getScreenHeight()/2-height/2);
        this.setVisible(true);


    }





}
