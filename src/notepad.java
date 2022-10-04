import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;


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
    public static JMenu timeAndDate;
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
        previous.addActionListener(new search.searchingUp());
        next = new JMenuItem("Next");
        next.addActionListener(new search.searchingDown());

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

        manage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1){
                    if (miniFunctions.getClipboardString() == null){
                        paste.setEnabled(false);
                    }
                }
            }
        });

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
        export.addActionListener(new miniFunctions.ExportButton());

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
                if (e.getButton() == MouseEvent.BUTTON3) {
                    rClick.show(notepad.this, e.getX() + 10, e.getY() + 55);
                    if (miniFunctions.getClipboardString() == null){
                        pasteR.setEnabled(false);
                    }
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
        leftPart = new JTextField("Line: 1          Column: 1           Word Count: 0");
        leftPart.setLayout(null);
        leftPart.setPreferredSize(new Dimension(250,25));
        leftPart.setEditable(false);
        leftPart.setBackground(new Color(238,238,238));
        leftPart.setBorder(new MatteBorder(0, 0, 0, 0, new Color(238, 238,
                238)));


        //cursor position
        input.addCaretListener(e -> {
            try {
                String text = input.getText();
                String trimmedLine = text.trim();
                int count = trimmedLine.isEmpty() ? 0 : trimmedLine.split("\\s+").length;


                int offset = e.getDot() ;
                int row = input.getLineOfOffset(offset);
                int column = e.getDot() - input.getLineStartOffset(row);
                leftPart.setText("Line: " + (row + 1) + "           Column: " + (column+1) + "          Word Count: " + count);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        statusBar.setLayout(new FlowLayout());
        statusBar.add(leftPart);

        //time&date
        //Real-time display
        timeAndDate = new JMenu();
        timeAndDate.addActionListener(new miniFunctions.TimeActionListener());
        timeAndDate.setPreferredSize(new Dimension(180,30));
        timeAndDate.setEnabled(false);


        topMenu.add(Box.createHorizontalGlue());
        topMenu.add(timeAndDate);

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
