package DataBase;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.*;

public enum DataBase {
    INSTANCE;

    public final static String DB_PATH = "c:/consult_db/";

    public Users users = new Users();
    public Settings settings = new Settings();

    // таблица пользователи
    public static class Users extends AbstractDbTable<String, Users.User> {
        public static class User {
            public final String login;
            public final String password;
            public  List <String> sesions;
            public  List <String> notes;
            public  boolean admin;
            public  boolean block;

            public boolean getBlock() {
                return block;
            }

            public void setBlock(boolean block) {
                this.block = block;
            }

            public  Map  somethingMatter;


            public User(String login, String password) {
                this.login = login;
                this.password = password;
                sesions = new ArrayList<>();
                notes =new ArrayList<>();
                somethingMatter = new HashMap();
                admin = false;
            }


            public void addSesions(String sesion) {
               sesions.add(sesion);
            }

            public void setNotes(String note) {
                notes.add(note);
            }

            public boolean addSomethingMatter(String key, String value) {
               if (!somethingMatter.containsKey(key)){
                   somethingMatter.put(key,value);
                   return true;
               } else return false;
            }

            public String getLogin() {
                return login;
            }



            public List<String> getSesions() {
               Collections.sort(sesions);
                return sesions;
            }

            public String getLastSesion() {
                if (sesions!=null && !sesions.isEmpty())

                {Collections.sort(sesions);
                return sesions.get(sesions.size()-1);}
                else return "... уже и не вспомнить...";
            }

            public List<String> getNotes() {
                return notes;
            }

            public boolean getAdmin() {
                return admin;
            }

            public void setAdmin(boolean tf){
                admin=tf;
            }

            public Map getSomethingMatter() {
                return somethingMatter;
            }



            @Override
            public String toString() { return login; }

            public String getPassword(){
                return password;
            }
        }

        private Users() {
            super(new TypeToken<ArrayList<User>>() {}.getType());
        }

        @Override
        public String getTableName() { return "users.json"; }

        @Override
        public String getKey(User elem) { return elem.login; }
    }



    // таблица настройки
    public static class Settings extends AbstractDbTable<String, Settings.Record> {
        public static class Record {
            public final String name;
            public final String description;
            public final int value;



            public Record(String name, String description, int value) {
                this.name = name;
                this.description = description;
                this.value = value;
            }
            public int getValue() {
                return value;
            }
            public String getName() {
                return name;
            }

            public String getDescription() {
                return description;
            }
        }

        private Settings() {
            super(new TypeToken<ArrayList<Record>>() {}.getType());
        }

        @Override
        public String getTableName() { return "settings.json"; }

        @Override
        public String getKey(Record elem) { return elem.name; }
    }


    public static void main(String[] args) throws IOException {




      // INTERESTING LAMBDA
        //  List<Users.User> list2 = INSTANCE.users.select(e -> e.is_mentor);

    }
}
