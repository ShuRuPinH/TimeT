package classes.DataBaseDir;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class NoteBuilder {

    static DataBase.Notes.Value note;
    static DateTimeFormatter dtf =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"); // 2021-05-05T20:05

    //public Value(String user,String number, String message, LocalDateTime start, int repeats, double interval)
    public static DataBase.Notes.Value build(String user, String number, String message, String start, String repeats, String interval) {
        Integer reps;
        Double interv;
        ZonedDateTime date;
        long startt = 0;
        try {
            date = ZonedDateTime.parse (start+":00.000+0300",dtf);
            startt = date.toInstant().toEpochMilli();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (repeats == "") repeats = "0";
        if (interval == "") interval = "0.5";

        try {
            reps = Integer.parseInt(repeats);
        } catch (Exception e) {
            reps = 0;
        }
        try {
            interv = Double.parseDouble(interval);
        } catch (Exception e) {
            interv = 0d;
        }

        if (reps > 10) reps = 10;

        note = new DataBase.Notes.Value(user, number.replace(" ", ""), message, startt, reps, interv);
        return note;
    }


    public static ArrayList sortNotes(List list) {
        ArrayList nt = new ArrayList(list);
        nt.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                int rez;
                long l1 = ((DataBase.Notes.Value) o1).getStart();
                long l2 = ((DataBase.Notes.Value) o2).getStart();
                if (l1 > l2) rez = 1;
                else if (l1 == l2) rez = 0;
                else rez = -1;

                return rez;
            }
        });
        return nt;
    }

}
