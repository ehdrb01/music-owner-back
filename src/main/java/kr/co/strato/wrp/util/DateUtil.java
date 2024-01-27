package kr.co.strato.wrp.util;
import kr.co.strato.wrp.exception.ErrorCode;
import kr.co.strato.wrp.exception.GeneralException;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
public class DateUtil {

    public static Date StringToDate(String dateStr){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
           log.error("[StringToDate] ParseException error", e);
           throw new GeneralException(ErrorCode.UNKNOWN_ERROR);
        }
    }

    public static String DateTimeToCron(LocalDateTime time){
        return String.format("%s %s %s %s %s %s %s", time.getSecond(), time.getMinute(), time.getHour(),
                time.getDayOfMonth(), time.getMonthValue(), "?", time.getYear());
    }
}
