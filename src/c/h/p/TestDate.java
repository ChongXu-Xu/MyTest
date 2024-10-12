package c.h.p;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestDate {

    public static void main(String[] args) {
        test2();
    }

    public static void test1() {
        Calendar date = Calendar.getInstance();
        System.out.println(date.get(Calendar.YEAR));
        System.out.println(date.get(Calendar.MONTH));
        System.out.println(date.get(Calendar.DAY_OF_MONTH));

        List<Map> arrayList = new ArrayList<>();
        Map<String, Map> collect = arrayList.stream().collect(Collectors.toMap(m -> "1", m -> m));
        System.out.println(collect);
    }

    public static void test2() {
        Date date = new Date();
        Instant instant = date.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        String format = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(format);
        
        // LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime localDateTime = LocalDateTime.now();
        String format1 = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHH"));
        
        System.out.println(format1);
    }
}
