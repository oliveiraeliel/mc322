package utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public final class DateUtils {
    public static Date localDate() {
        return new Date(LocalDate.now().atStartOfDay(ZoneId.of("America/Sao_Paulo")).toEpochSecond() * 1000);
    }

    public static String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static int calcularIdade(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int yyyy = cal.get(Calendar.YEAR);
        int dd = cal.get(Calendar.DAY_OF_MONTH);
        int mm = cal.get(Calendar.MONTH);

        cal.setTime(localDate());
        int yHoje = cal.get(Calendar.YEAR);
        int dHoje = cal.get(Calendar.DAY_OF_MONTH);
        int mHoje = cal.get(Calendar.MONTH);

        int idade = yHoje - yyyy;
        int diffMonth = mHoje - mm + 1;
        int diffDay = dHoje - dd;

        if (diffMonth < 0) {
            return idade - 1;
        } else if (diffMonth == 0 && diffDay < 0) {
            return idade - 1;
        }
        return idade;
    }
}
