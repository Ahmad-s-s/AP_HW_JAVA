package clients;

import java.util.ArrayList;

public class Nurse extends Client {
    String ID;
    static Integer idCounter = 0;
    static ArrayList<Nurse> added = new ArrayList<>();
    String record;

    Nurse(String username, String password, String name, String lastName,
          String record, Sex sex){
        this.name = name;
        this.lastName = lastName;
        this.setUsername(username);
        this.setPassword(password);
        this.roll = clientRoll.NURSE;
        this.record = record;
        this.sex = sex;
        Nurse.idCounter += 1;
        if (idCounter > 100)
            this.ID = idCounter.toString();
        else if (idCounter > 10)
            this.ID = "0"+idCounter.toString();
        else
            this.ID = "00"+idCounter.toString();

    }

    @Override
    public void Menu() {

    }
}
