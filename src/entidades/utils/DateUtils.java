package entidades.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
    public static Date localDate(){
        return new Date(LocalDate.now().atStartOfDay(ZoneId.of("America/Sao_Paulo")).toEpochSecond()*1000);
    }

    public static String formatDate(Date date, String pattern){
        return new SimpleDateFormat(pattern).format(date);
    }
}
