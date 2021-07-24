package classes.DataBaseDir;

import classes.filters.Check;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Archiver {

    public static void archive(String user) {

        String usrl = user.replace(".", "_");
        String filenameH = Check.getRealPath() + "/history/" + usrl + ".his";
        String filenameS = Check.getRealPath() + "/history/" + usrl + ".ses";

        String outZip = Check.getRealPath() + "/archive/" + usrl + ".zip";
        int cnt = 0;
        while (new File(outZip).exists()) {
            outZip = Check.getRealPath() + "/archive/" + usrl + "_" + (cnt++) + ".zip";
        }

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(outZip));
             FileInputStream fiH = new FileInputStream(filenameH); FileInputStream fiS = new FileInputStream(filenameS);) {
            ZipEntry entryH = new ZipEntry(usrl + "_his.txt");
            ZipEntry entryS = new ZipEntry(usrl + "_ses.txt");

            zout.putNextEntry(entryH);
            // считываем содержимое файла в массив byte
            byte[] buffer = new byte[fiH.available()];
            fiH.read(buffer);
            // добавляем содержимое к архиву
            zout.write(buffer);
            // закрываем текущую запись для новой записи
            zout.closeEntry();


            zout.putNextEntry(entryS);
            // считываем содержимое файла в массив byte
            buffer = new byte[fiS.available()];
            fiS.read(buffer);
            // добавляем содержимое к архиву
            zout.write(buffer);
            // закрываем текущую запись для новой записи
            zout.closeEntry();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(" delete 1st : " + new File(filenameH).delete());
            System.out.println(" delete 2nd : " + new File(filenameS).delete());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
