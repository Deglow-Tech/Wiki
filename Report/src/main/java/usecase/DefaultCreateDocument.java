package usecase;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static usecase.Functions.getDate;


public class DefaultCreateDocument implements CreateDocument {
    @Override
    public void createDocument() {
        try {
            PdfWriter writer = new PdfWriter("Reporte-" + getDate() + ".pdf");
            PdfDocument pdfDocument = new PdfDocument(writer);

            Document document = new Document(pdfDocument);
            document.add(new Paragraph("Reporte del dia " + getDate()));
            document.add(new Paragraph("Paciente: Maria Milagros"));
            document.add(new Paragraph("Edad: 30"));

            CreateGraphic createGraphic = new DefaultCreateGraphicHeartRate();

            PdfReader reader = new PdfReader(new ByteArrayInputStream(createGraphic.CreateAndSaveGraphic(new double[][] {{ 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017 }, { 25, 29.1, 32.1, 32.9, 31.9, 25.5, 20.1, 18.4, 15.3, 11.4, 9.5 }})));
            PdfDocument chartDoc = new PdfDocument(reader);
            PdfFormXObject chart = chartDoc.getFirstPage().copyAsFormXObject(pdfDocument);
            Image chartImage = new Image(chart);
            document.add(chartImage);

            document.close();


        }catch ( IOException e){

        }
    }
}
