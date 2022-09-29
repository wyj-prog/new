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
        //Invoke the print dialog to print the document to the user
            try{
                //Build a print property set
                PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                //set print format
                DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
                //Find all available print services
                PrintService[] printService = PrintServiceLookup.lookupPrintServices(flavor, pras);
                PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
                //Display the print dialog
                PrintService service = null;
                service = ServiceUI.printDialog(null,150, 150, printService, defaultService, flavor, pras);
                if (service!=null)
                {
                    //Create a print job
                    DocPrintJob job = service.createPrintJob();
                    DocAttributeSet das = new HashDocAttributeSet();
                    //Create a print file format
                    Doc doc = new SimpleDoc(notepad.input.getText().getBytes(), flavor, das);
                    //print file
                    job.print(doc, pras);
                }
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(this,"Print operation did not finished successfully.");
            }
        }
}
