package usecase;

import com.orsonpdf.PDFDocument;
import com.orsonpdf.PDFGraphics2D;
import com.orsonpdf.Page;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import java.awt.*;
import java.io.DataInput;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static usecase.Functions.getDate;

public class DefaultCreateGraphicHeartRate implements CreateGraphic {

    @Override
    public byte[] CreateAndSaveGraphic( double[][] point) {
        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("Heart Rate", point);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.ORANGE);

        renderer.setSeriesStroke(0, new BasicStroke(2));

        JFreeChart chart = ChartFactory.createXYLineChart("Pulsaciones Cardiacas", "Tiempo", "Pulsaciones", dataset);
        chart.getXYPlot().getRangeAxis().setRange(0, 100);
        ((NumberAxis) chart.getXYPlot().getRangeAxis()).setNumberFormatOverride(new DecimalFormat("#'%'"));
        chart.getXYPlot().setRenderer(renderer);

        PDFDocument pdfDocument = new PDFDocument();
        pdfDocument.setTitle("Reporte");
        pdfDocument.setAuthor("Deglow");

        Page page = pdfDocument.createPage(new Rectangle(612, 468));
        PDFGraphics2D g2 = page.getGraphics2D();

        chart.draw(g2, new Rectangle(0,0,612,468));
        //pdfDocument.writeToFile(new File("Reporte-" + getDate() + ".pdf"));
        return pdfDocument.getPDFBytes();
    }


}
