package classes.DataBaseDir;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.*;

public enum DataBase {
    INSTANCE;

    public final static String DB_PATH = "/consult_db/";

    public Users users = new Users();
    public Settings settings = new Settings();
    public Notes notes =new Notes();

    // таблица пользователи
    public static class Users extends AbstractDbTable<String, Users.User> {
        public static class User {
            public final String login;
            public  String password;
            public  List <String> sesions;
            public  List <String> notes;
            public  boolean admin;
            public  boolean block;
            public int limit;
            public  Map  somethingMatter;


            public boolean getBlock() {
                return block;
            }

            public void setBlock(boolean block) {
                this.block = block;
            }

            public User(String login, String password) {
                this.login = login;
                this.password = password;
                sesions = new ArrayList<>();
                notes =new ArrayList<>();
                somethingMatter = new HashMap();
                admin = false;
                block=false;
                limit = 1000;
            }

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
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

            public void setPassword(String password) {
                this.password = password;
            }
        }

        private Users() {
            super(new TypeToken<ArrayList<User>>() {}.getType());
        }

        @Override
        public String getTableName() { return "users.json"; }

        @Override
        public String getKey(User elem) { return elem.login; }

        public User getUser (String login){
           for(User u:  INSTANCE.users.getAll()){
               if (u.getLogin().equals(login))
                   return u;
           }
           return null;
        }
    }



    // таблица настройки
    public static class Settings extends AbstractDbTable<String, Settings.Record> {
        public static class Record {
            public final String name;
            public final String description;
            public final int value;
            public final boolean system;



            public Record(String name, String description, int value,boolean system) {
                this.name = name;
                this.description = description;
                this.value = value;
               this.system=system;
            }
            public int getValue() {
                return value;
            }
            public String getName() {
                return name;
            }

            public boolean getSystem() {
                return system;
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

        public Record getRecord(String name){
            for(Record r: INSTANCE.settings.getAll() ){
                if (r.getName().equals(name))
                    return r;

            }
            return null;
        }
    }


    //// NOTES
    public static class Notes extends AbstractDbTable<Notes.Key, Notes.Value> {
        public static class Value {

            /*
              LocalDateTime create;
    LocalDateTime start;
    String telNumber;
    String message;
    int repeats;
    double interval;
    String ip;
             */
            public final String user;
            public final String number;
            public final String message;
            public final long start;
            public final long create;

            public final int repeats;
            public final double interval;


            public Value(String user,String number, String message, long start, int repeats, double interval) {
                this.user=user;
                this.number = number;
                this.message = message;
                this.start = start;
                this.repeats = repeats;
                this.interval = interval;
                create = new Date().getTime();
            }

            public String getUser() {
                return user;
            }

            public String getNumber() {
                return number;
            }

            public String getMessage() {
                return message;
            }

            public long getStart() {
                return start;
            }

            public long getCreate() {
                return create;
            }

            public int getRepeats() {
                return repeats;
            }

            public double getInterval() {
                return interval;
            }

        }

        public static class Key {
            public final String user;
            public final long create;
            public final long start;

            public Key(String user, long create, long start) {
                this.user = user;
                this.create = create;
                this.start = start;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Key key = (Key) o;
                return create == key.create &&
                        start == key.start &&
                        user.equals(key.user);
            }


            @Override
            public int hashCode() {
                return Objects.hash(user, create, start);
            }
        }

        private Notes() {
            super(new TypeToken<ArrayList<Value>>() {
            }.getType());
        }

        @Override
        public String getTableName() {
            return "notes.json";
        }

        @Override
        public Key getKey(Value elem) {
            return new Key(elem.user, elem.create, elem.start);
        }


        public static void main(String[] args) throws IOException {


            // INTERESTING LAMBDA
          //   List list2 = INSTANCE.notes.select(e -> e.user.equals());

        }
    }}
