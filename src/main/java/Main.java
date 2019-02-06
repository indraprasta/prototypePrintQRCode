import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));
        document.setMargins(8f, 10f, 10f, 283f);
        document.open();

        PdfPTable layout = new PdfPTable(5);
        layout.setWidthPercentage(100);

        int jml = 25;
        for (int i = 0; i < jml; i++) {
            //QR Code
            Image image = Image.getInstance("C:\\Users\\Ibnu\\IdeaProjects\\cobaitextdanzxing\\src\\main\\resources\\MyQRCode.png");
            PdfPCell imageCell = new PdfPCell(image, true);
            imageCell.setBorder(Rectangle.NO_BORDER);

            //Judul
            PdfPCell textCell = new PdfPCell(
                    new Phrase("Bahasa Indonesia Kelas 5 Semester 1",
                            new Font(Font.FontFamily.TIMES_ROMAN, 6)));
            textCell.setBorder(Rectangle.NO_BORDER);
            textCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

            //Label
            PdfPTable label = new PdfPTable(2);
            label.setWidthPercentage(100);
            label.addCell(imageCell);
            label.addCell(textCell);

            //Cell Label
            PdfPCell cellLabel = new PdfPCell(label);
            cellLabel.setBorder(Rectangle.NO_BORDER);
            cellLabel.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellLabel.setPadding(15);

            layout.addCell(cellLabel);
        }

        //Jika jumlah cell pada baris terakhir kurang dari 5 column
        jml = (jml % 5 == 0) ? 0 : 5 - (jml % 5);
        for (int i = 0; i < jml; i++) {
            PdfPCell emptyCell = new PdfPCell();
            emptyCell.setBorder(Rectangle.NO_BORDER);
            layout.addCell(emptyCell);
        }

        document.add(layout);
        document.close();


        File file = new File("test.pdf");

        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
    }
}
