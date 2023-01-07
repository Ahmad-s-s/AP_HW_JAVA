package clients;

import java.util.Objects;

public abstract class Client {
    //attributes
    protected String name;
    protected String lastName;
    private String username;
    private String password;
    private String code;
    static Integer codeCounter = 0;
    public clientRoll roll;
    public Sex sex;

    //methods


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    abstract public void Menu();

    public boolean amI(String username, String password) {
        return Objects.equals(this.username, username) && Objects.equals(this.password, password);
    }

    public boolean wrongPass(String username, String password) {
        return Objects.equals(this.username, username) && !Objects.equals(this.password, password);
    }

    public void whoAmI() { // for admin
        System.out.println("name : " + this.name);
        System.out.println("last name : " + this.lastName);
        System.out.println("Sex : " + this.sex);
        System.out.println("Username : " + this.username);
        System.out.println("Password : ***");
        System.out.println("Roll : " + this.roll);
        System.out.println("User code : " + this.code);
    }

}
