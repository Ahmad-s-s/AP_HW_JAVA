package clients;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Patient extends Client {
    String ID;
    static Integer idCounter = 0;
    public static ArrayList<Patient> added = new ArrayList<>();
    Integer age;
    String disease;
    Mode mode;
    private Status status;
    private ArrayList<String> medicines;
    LocalDateTime admissionTime, treatmentTime, dischargedTime;
    private String history;
    Physician myDoc;

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    void addMedicine(String medicine) {
        medicines.add(medicine);
    }
    ArrayList<String> getMedicines() {
        return this.medicines;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Patient(String username, String password, String name, String lastName,
                   Integer age, Sex sex, String disease, Mode mode) {
        this.setUsername(username);
        this.setPassword(password);
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.disease = disease;
        this.mode = mode;
        this.roll = clientRoll.PATIENT;
        this.status = Status.ADMISSION;
        this.admissionTime = LocalDateTime.now();
        medicines = new ArrayList<>();
        myDoc = null;
        Patient.idCounter += 1;
        if (idCounter >= 100)
            this.ID = idCounter.toString();
        else if (idCounter >= 10)
            this.ID = "0" + idCounter;
        else
            this.ID = "00"+ idCounter;
    }

    @Override
    public void Menu() {

    }

    public void introToDoc() {
        System.out.println("name : " + this.name);
        System.out.println("lastName : " + this.lastName);
        System.out.println("Age & Sex : " + this.age + ", " + this.sex);
        System.out.println("Mode : " + this.mode);
        System.out.println("Disease : " + this.disease);
        System.out.println("ID : " + this.ID);
    }
}
