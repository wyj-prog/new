import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import java.awt.*;
import java.awt.print.*;

public class Print extends JFrame {
    public Print(){
//        //Initializing the window
//        this.setTitle("Print");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JPanel jPanel = new JPanel();
//        jPanel.setLayout(null);
//        JButton Print_Button = new JButton("Print");
//        JButton Cancel_Button = new JButton("Cancel");
//        JButton Apply_Button = new JButton("Apply");
//        Print_Button.setBounds(new Rectangle(100, 320, 80, 20));
//        Cancel_Button.setBounds(new Rectangle(200,320,80,20));
//        Apply_Button.setBounds(new Rectangle(300,320,80,20));
//        jPanel.add(Print_Button);
//        jPanel.add(Cancel_Button);
//        jPanel.add(Apply_Button);
//        this.add(jPanel);
//        this.setBounds(funtions.getScreenWidth()/2 - 210, funtions.getScreenHeight()/2 - 190, 420, 380);
//        this.setVisible(true);
        //Invoke the print dialog to print the document to the user
            try{
                //Build a print property set
                PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                //set print format
                DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
                //Find all available print services
                PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
                PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
                //Display the print dialog
                PrintService service = null;
                service = ServiceUI.printDialog(null,150, 150, printService, defaultService, flavor, pras);
                if (service!=null)//
                {
                    //Create a print job
                    DocPrintJob job = service.createPrintJob();
                    DocAttributeSet das = new HashDocAttributeSet();
                    //Create a print file format
                    Doc doc = new SimpleDoc(notepad.leftPart.getText().getBytes(), flavor, das);
                    //print file
                    job.print(doc, pras);
                }
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(this,"Print operation did not finished successfully.");
            }
        }
}
