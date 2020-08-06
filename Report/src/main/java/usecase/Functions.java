package usecase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Functions {
    public static String getDate(){
        Date date = new Date();
        SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormato = formatFecha.format(date);

        return fechaFormato;
    }
}
