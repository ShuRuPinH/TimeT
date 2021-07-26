package classes.core;

import classes.DataBaseDir.DataBase;
import classes.DataBaseDir.NoteBuilder;
import classes.filters.Check;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static classes.DataBaseDir.DataBase.INSTANCE;

public enum SMScore {
    INSTANCE_CORE;
    static TreeMap<Long, String[]> schedule = new TreeMap<>();
    static TreeMap<String, Integer[]> limit = new TreeMap<>();
    static HttpURLConnection con;
    static ZonedDateTime zdt;
    static DateTimeFormatter dateF = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss 'MSK'");
    static String realPath;
    static int count = 0;

    static {
        Timer timer = new Timer("smscore");
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                checkANDsend();
            }
        };
        timer.schedule(task, 1000, 10_000);
    }

    private static void checkANDsend() {
        if ((count++) % 6 == 0) getSchedule();
        long now = Instant.now().atZone(ZoneId.of("Europe/Moscow")).toInstant().toEpochMilli();
        List<Long> clearList = new ArrayList<>();
        System.out.print("ss=" + schedule.size()+" ");
        for (Map.Entry<Long, String[]> nt : schedule.entrySet()) {
            if ((now + 5_000) > nt.getKey()) {
                String[] utm = nt.getValue();
                try {
                    String report;

                    if (checkLimit(utm[0])) {
                        report = sendSMS(utm[1], utm[2]);
                    } else if (checkLimit(utm[0])==null){
                        report = Instant.now().atZone(ZoneId.of("Europe/Moscow")).format(dateF) + ": исчерпан лимит сообщений сервиса";
                    } else {
                        report = Instant.now().atZone(ZoneId.of("Europe/Moscow")).format(dateF) + ": исчерпан лимит сообщений в день";
                    }

                    clearList.add(nt.getKey());
                    System.out.println("claerList.size after ADD = " + clearList.size());
                    Files.writeString(Path.of(realPath + "/history/" + utm[0].replace(".", "_") + ".his"),
                            report + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);

                } catch (IOException e) {
                    e.printStackTrace(); //todo write to log
                }
            }
            break;
        }

        if (!clearList.isEmpty()) {
            clearList.forEach(x -> schedule.remove(x));
            System.out.println("schedule.size after remove = " + schedule.size());
        }

    }

    private static Boolean checkLimit(String s) {
        int today = Instant.now().atZone(ZoneId.of("Europe/Moscow")).getDayOfYear();
        if  (limit.containsKey("max_sms_day") && limit.get("max_sms_day")[1] == today){
            if (limit.get("max_sms_day")[0] >= 1) {
                limit.put("max_sms_day", new Integer[]{limit.get("max_sms_day")[0] - 1, today});
            } else if (limit.get("max_sms_day")[0] <= 0){
                return null;
            }
        } else limit.put("max_sms_day", new Integer[]{INSTANCE.settings.getRecord("max_sms_day").getValue() - 1, today});


        if (limit.containsKey(s) && limit.get(s)[1] == today) {
            if (limit.get(s)[0] >= 1) {
                limit.put(s, new Integer[]{limit.get(s)[0] - 1, today});
                return true;
            } else if (limit.get(s)[0] <= 0) {

                return false;
            }
        } else {
            limit.put(s, new Integer[]{INSTANCE.users.getUser(s).getLimit() - 1, today});

        }

        return true;

    }


    public static void getSchedule() {
        realPath = Check.getRealPath();

        schedule.clear();

        long now = Instant.now().atZone(ZoneId.of("Europe/Moscow")).toInstant().toEpochMilli();
        List<DataBase.Notes.Value> allNotes = NoteBuilder.sortNotes(INSTANCE.notes.getAll());
        List<DataBase.Notes.Key> delList = new ArrayList<>();
        for (DataBase.Notes.Value v : allNotes) {
            boolean actual = false;
            long nextFreeSlot = nextFreeSlot(schedule, v.start);

            if (nextFreeSlot > now) {
                schedule.put(nextFreeSlot, new String[]{v.user, v.number, v.message});
                System.out.println("schedule.put(nextFreeSlotR, new String[]{v.user,v.number,v.message});\n"
                        + nextFreeSlot + "  " + v.user + "   " + v.number + "    " + v.message);
                actual = true;
            }

            for (int i = 1; i <= v.repeats; i++) {

                long nextTime = v.start + (long) (60_000 * (v.interval * i));
                long nextFreeSlotR = nextFreeSlot(schedule, nextTime);

                if (nextFreeSlotR > now) {
                    schedule.put(nextFreeSlotR, new String[]{v.user, v.number, v.message});
                    System.out.println("schedule.put(nextFreeSlotR, new String[]{v.user,v.number,v.message});\n"
                            + nextFreeSlotR + "  " + v.user + "   " + v.number + "    " + v.message);
                    actual = true;
                }
            }

            if (!actual) delList.add(new DataBase.Notes.Key(v.user, v.create, v.start));
        }
        if (!delList.isEmpty()) {

            for (DataBase.Notes.Key key : delList) {
                try {
                    System.out.println(" remove : "+key);
                    INSTANCE.notes.remove(key);
                } catch (IOException e) {
                    e.printStackTrace();  // todo log IT
                }
            }
        }
    }

    static Long nextFreeSlot(Map map, long check) {

        if (map.containsKey(check)) {
            for (int i = 1; i <= 100; i++) {

                if (map.containsKey(check + 500)) {
                    continue;
                } else return (check + 500);
            }
        }
        return check;

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    static String sendSMS(String tel, String mess) {
        // System.out.println(mess);
        String rez = "error sendSMS";
        var url = "http://77.51.193.189:7077/";
        var urlParameters = "tel=" + tel + "&mess=" + mess;
        byte[] postData = Base64.getEncoder().encode(urlParameters.getBytes(StandardCharsets.UTF_8));

        try {
            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded utf-8");

            try (var wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData, 0, postData.length);
            }

            StringBuilder content;

            try (var br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String line;
                content = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
                rez = content.toString().substring(0, 19) + " MSK\t// Успешно отправленно.\t\t ";
            }


        } catch (Exception e) {
            try {
                zdt = Instant.now().atZone(ZoneId.of("Europe/Moscow"));
                rez = zdt.format(dateF) + "\t// Не отправлено:\"" + con.getResponseMessage() + "\". ";
            } catch (IOException ioException) {
                ioException.printStackTrace();
                rez = zdt.format(dateF) + "\t// Не отправлено!!!\t\t. ";
            }


            e.printStackTrace();
        } finally {
            con.disconnect();
        }
        return rez + " ♫:\"" + (mess.length() < 10 ? mess : mess.substring(0, 7) + "...") + "\" по #:" + tel;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            System.out.println(sendSMS("8909917147", "тест #" + (i + 1)));
            System.out.println(sendSMS("89099197147", "тест #" + (i + 1)));

        }
        System.out.println("Send time : " + (System.currentTimeMillis() - start));
    }
}
