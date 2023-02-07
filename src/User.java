public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    public static int id_gen=1;
    public User(){
        id=id_gen++;
    }
    public User(String username, String password, String name, String surname){
        this();
        setName(name);
        setSurname(surname);
    }
    public int getId(){
        return id;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getUsername(){
        return username;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setSurname(String surname){
        this.surname=surname;
    }
    public String getSurname(){
        return surname;
    }
}
