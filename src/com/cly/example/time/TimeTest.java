package com.cly.example.time;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

public class TimeTest {

    //Clock 时钟
    @Test
    public void clock() {
        Clock clock = Clock.systemDefaultZone();
        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant); // legacy java.util.Date
        System.out.println(legacyDate.toLocaleString());
    }

    //时区
    @Test
    public void zone() {
        // prints all available timezone ids
        System.out.println(ZoneId.getAvailableZoneIds());

        ZoneId zone1 = ZoneId.of("Etc/GMT+9");
        ZoneId zone2 = ZoneId.of("Etc/GMT+8");

        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println(now1.isBefore(now2)); // true
        //        now1.minusNanos(nanosToSubtract)

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

        System.out.println(hoursBetween); // 
        System.out.println(minutesBetween); // 

        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late); // 23:59:59

        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)
                .withLocale(Locale.CHINESE);
        LocalTime leetTime = LocalTime.parse("13:37:12", germanFormatter);
        System.out.println(leetTime); // 13:37:12
    }

    @Test
    public void string2Date() {
        //日期+时间
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse("2016-05-25 15:53:55", df);
        System.out.println(dateTime);

        //日期
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2016-05-25", df2);
        System.out.println(date);

        //时间
        DateTimeFormatter df3 = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time = LocalTime.parse("19:34:24", df3);
        System.out.println(time);

        //加10分钟
        LocalTime plus = time.plus(10, ChronoUnit.MINUTES);
        System.out.println(plus);
    }

    @Test
    public void date2String() {
        //        ZonedDateTime time = ZonedDateTime.now();
        ZonedDateTime time = ZonedDateTime.of(2017, 5, 3, 14, 26, 53, 134, ZoneId.systemDefault());
        //        ZonedDateTime time = ZonedDateTime.now(Clock.systemDefaultZone());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(time.format(df));
    }

    //long值转换成日期类型
    @Test
    public void long2Str() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String s = df.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(1471337924226L), ZoneId.of("Asia/Shanghai")));
        System.out.println(s);
    }

}
