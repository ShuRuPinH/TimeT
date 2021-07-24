package classes.DataBaseDir;

import classes.filters.Check;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public enum Loger {
    INSTANCE_LOG;
    static ZonedDateTime zdt;
    static DateTimeFormatter dateF = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss 'MSK'");


    static public void logWrite(String event) {
        try {
            Files.writeString(Path.of(Check.getRealPath() + "/dir_log/log.txt"),
                    Instant.now().atZone(ZoneId.of("Europe/Moscow")).format(dateF) + "    : " +
                            event + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static public void logAutWrite(String event) {
        try {
            Files.writeString(Path.of(Check.getRealPath() + "/dir_log/logAut.txt"),
                    Instant.now().atZone(ZoneId.of("Europe/Moscow")).format(dateF) + "    : " +
                            event + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
