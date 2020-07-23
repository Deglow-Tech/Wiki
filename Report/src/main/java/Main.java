import usecase.CreateDocument;
import usecase.CreateGraphic;
import usecase.DefaultCreateDocument;
import usecase.DefaultCreateGraphicHeartRate;

public class Main {
    public static void main(String[] args) {

        CreateDocument createDocument = new DefaultCreateDocument();
        createDocument.createDocument();


    }
}

/*
https://picodotdev.github.io/blog-bitix/2017/10/visualizar-datos-y-generar-graficas-en-java-con-jfreechart/
http://acodigo.blogspot.com/2017/04/convertir-graficas-jfreechart-pdf.html
https://jfree.github.io/orsonpdf/
 */