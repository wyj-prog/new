import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.swing.*;

public class Export extends JFrame {

        public Export() throws IOException{
            PDDocument doc = null;
            try
            {
                doc = new PDDocument();
                PDPage page = new PDPage();
                doc.addPage(page);
                PDPageContentStream contentStream = new PDPageContentStream(doc, page);

                PDFont pdfFont = PDType1Font.TIMES_ROMAN;
                float fontSize = 10;
                float leading = 1.5f * fontSize;

                PDRectangle mediabox = page.getMediaBox();
                float margin = 72;
                float width = mediabox.getWidth() - 2*margin;
                float startX = mediabox.getLowerLeftX() + margin;
                float startY = mediabox.getUpperRightY() - margin;

                String text = notepad.input.getText();
                List<String> lines = new ArrayList<String>();
                String temp = "";
                for (int i = 0; i < text.length(); i++){
                    if (text.charAt(i) == '\n' || text.charAt(i) == '\r'){
                        // If this is the end of the document
                        if(i + 1 == text.length()){ // last one
                            lines.add(temp);
                            break;
                        }else
                        // If this is not the end of the document
                        {
                            int count = -1;
                            while(text.charAt(i)== '\n' || text.charAt(i) == '\r'){
                                if (i + 1 < text.length()){
                                    i = i + 1;
                                    count ++;
                                }else break;
                            }
                            lines.add(temp);
                            for (int newline = 1; newline <= (int)count; newline++){
                                temp = "";
                                lines.add(temp);
                            }
                            if (i + 1 == text.length()) break;
                            else {
                                //if (count > 0) i++;
                                i = i - 1;
                                temp = "";
                            }
                        }
                    }else temp += text.charAt(i);
                }

                contentStream.beginText();
                contentStream.setFont(pdfFont, fontSize);
                contentStream.newLineAtOffset(startX, startY);
                int count_lines = 0;
                int need_new_page = 0;
                int current_line = 0;
                for (int i = 1; i <= lines.size() / 47 + 1 ; i ++){
                    while (current_line < lines.size())
                    {
                        //Judging whether the line is too long
                        if (lines.get(current_line).length() > 130){
                            int plus_one;
                            plus_one = lines.get(current_line).length() % 130 == 0 ? 0 : 1;
                            int rows = lines.get(current_line).length() / 130 + plus_one;
                            int current_position = 0;
                            for (int count = 1; count < rows; count++){
                                contentStream.showText(lines.get(current_line).substring(current_position, current_position + 130));
                                contentStream.newLine();
                                contentStream.newLineAtOffset(0, -leading);
                                current_position += 131;
                            }
                            contentStream.showText(lines.get(current_line).substring(current_position, lines.get(current_line).length()));
                            contentStream.newLine();
                            contentStream.newLineAtOffset(0, -leading);
                        }else {
                            contentStream.showText(lines.get(current_line));
                            contentStream.newLine();
                            contentStream.newLineAtOffset(0, -leading);
                        }
                        current_line++;
                        if ((current_line % 47) == 0) break;
                    }
                    contentStream.endText();
                    contentStream.close();
                    if (current_line != lines.size()) {
                        PDPage newpage = new PDPage();
                        doc.addPage(newpage);
                        contentStream = new PDPageContentStream(doc, newpage);
                        contentStream.beginText();
                        contentStream.setFont(pdfFont, fontSize);
                        contentStream.newLineAtOffset(startX, startY);
                        count_lines = 0;
                    }
                }
                doc.save(new File("Print.pdf"));
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (doc != null) {
                    doc.close();
                    JOptionPane.showMessageDialog(null, "Successfully exported the text file to Print.pdf", "Success", JOptionPane.INFORMATION_MESSAGE);
                }else
                JOptionPane.showMessageDialog(null, "Error occured", "alert", JOptionPane.ERROR_MESSAGE);
            }
        }
}
