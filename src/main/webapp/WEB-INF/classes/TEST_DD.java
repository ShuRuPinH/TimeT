import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static DataBaseDir.DataBase.INSTANCE;


public class TEST_DD {

    public static void main(String[] args) {

        try {
            Files.createDirectory(Path.of("/consult_db"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(INSTANCE.settings.getAll());

    }
    }

