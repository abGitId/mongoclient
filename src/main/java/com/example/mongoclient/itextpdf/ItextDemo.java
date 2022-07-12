package com.example.mongoclient.itextpdf;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class ItextDemo {
    public final static String dest = "C:/test/sample.pdf";
    /*public static void main(String[] args) throws Exception {
        // Creating a PdfWriter
        String dest = "C:/test/sample.pdf";
        PdfWriter writer = new PdfWriter(dest);

        // Creating a PdfDocument
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.addNewPage();
        // Creating a Document
        Document document = new Document(pdfDoc, PageSize.A3);

        document.close();
    }*/

    public static void main(String[] args) throws Exception {
        List<Integer> integers = Arrays.asList(10, 10, 10, 70);

        int[] objects = new int[3];
        objects[0]=1;
        objects[1]=5;
        objects[2]=10;

        Arrays.stream(objects).boxed().forEach(System.out::println);

    }

    public double calculatePercentage(double obtained, double total) {
        return obtained * 100 / total;
    }

    /*public static void main(String[] args) throws Exception {
        File file = new File(dest);
        file.getParentFile().mkdirs();

        new ItextDemo().manipulatePdf(dest);
    }*/

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc, PageSize.A4);
        doc.setMargins(36, 36, 72, 36);

        Table table = new Table(1).useAllAvailableWidth();

        Cell cell = new Cell().add(new Paragraph("This is a test doc"));
        cell.setBackgroundColor(ColorConstants.ORANGE);
        table.addCell(cell);

        cell = new Cell().add(new Paragraph("This is a copyright notice"));
        cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(cell);
        TableFooterEventHandler tableFooterEventHandler = new TableFooterEventHandler(table);
        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, tableFooterEventHandler);
        Paragraph paragraph = new Paragraph("Hello World!");
        for (int i = 0; i < 20; i++) {
            doc.add(paragraph);
        }

        doc.add(new AreaBreak());
        doc.add(paragraph);
        doc.add(new AreaBreak());
        pdfDoc.removeEventHandler(PdfDocumentEvent.END_PAGE, tableFooterEventHandler);
        doc.add(paragraph);
        pdfDoc.addNewPage();
        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, tableFooterEventHandler);
        doc.add(paragraph);
        doc.close();
    }

    private static class TableFooterEventHandler implements IEventHandler {
        private Table table;

        public TableFooterEventHandler(Table table) {
            this.table = table;
        }

        @Override
        public void handleEvent(Event currentEvent) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) currentEvent;
            PdfDocument pdfDoc = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

            new Canvas(canvas, new Rectangle(36, 20, page.getPageSize().getWidth() - 72, 50))
                    .add(table)
                    .close();
        }
    }
}
