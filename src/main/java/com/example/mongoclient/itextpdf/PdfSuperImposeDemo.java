package com.example.mongoclient.itextpdf;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.TextAlignment;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PdfSuperImposeDemo {
    public final static String dest = "C:/test/super-impose1.pdf";
    public final static String src = "C:/test/sample.pdf";
    public final static String img = "C:/test/logo.png";

    public static void main(String[] args) throws Exception {

        manipulatePdf(src, new String[]{src, src}, dest);
    }

    public static void manipulatePdf(String src, String[] extra, String dest) throws Exception {

        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
        Header header = new Header("Sample PDF");
        pdfDoc.addEventHandler(PdfDocumentEvent.START_PAGE, header);
        byte[] imgbytes = Files.readAllBytes(Paths.get(img));
        Image image = new Image(ImageDataFactory.create(imgbytes)).setFixedPosition(750f, 20f);
        ImageEventHandler imageEventHandler = new ImageEventHandler(image);
        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, imageEventHandler);
        PdfDocument srcDoc;
        PdfFormXObject page;

       /* PdfCanvas canvas = new PdfCanvas(pdfDoc.getLastPage().newContentStreamBefore(),
                pdfDoc.getLastPage().getResources(), pdfDoc);*/

        for (String path : extra) {
            srcDoc = new PdfDocument(new PdfReader(path));
            pdfDoc.addNewPage(PageSize.A3);
            PdfCanvas canvas = new PdfCanvas(pdfDoc.getLastPage().newContentStreamBefore(),
                    pdfDoc.getLastPage().getResources(), pdfDoc);
            page = srcDoc.getFirstPage().copyAsFormXObject(pdfDoc);

            canvas.addXObjectAt(page, 25, 25);
            srcDoc.close();
        }
        pdfDoc.close();
    }

}


class Header implements IEventHandler {

    private String header;

    public Header(String header) {
        this.header = header;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDocument = documentEvent.getDocument();

        PdfPage page = documentEvent.getPage();
        PdfCanvas aboveCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDocument);
        Rectangle pageSize = page.getPageSize();
        Canvas canvas = new Canvas(aboveCanvas, pageSize);
        canvas.setFontSize(12f);
        canvas.setFontColor(ColorConstants.BLACK);

        canvas.showTextAligned(header, pageSize.getWidth() / 25, pageSize.getTop() - 30, TextAlignment.LEFT);
        canvas.close();


    }
}

class ImageEventHandler implements IEventHandler {

    private Image image;

    public ImageEventHandler(Image image) {
        this.image = image;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDocument = documentEvent.getDocument();

        PdfPage page = documentEvent.getPage();
        PdfCanvas aboveCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDocument);
        Rectangle pageSize = page.getPageSize();
        Canvas canvas = new Canvas(aboveCanvas, pageSize);
        canvas.add(image);
        canvas.close();

    }
}