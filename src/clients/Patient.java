package clients;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Scanner;

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

    public void checkOut() {
        Scanner scanner = new Scanner(System.in);
        LocalDateTime checkoutTime = LocalDateTime.now();
        Integer days = ((checkoutTime.getYear() - admissionTime.getYear()) * 365) +
                ((checkoutTime.getMonthValue() - admissionTime.getMonthValue()) * 30) +
                ((checkoutTime.getDayOfMonth() - admissionTime.getDayOfMonth()));
        Integer res =0;
        if(mode.equals(Mode.VIP)) {
            res = days*120;
        } else if (mode.equals(Mode.NORMAL)) {
            res = days*70;
        } else if (mode.equals(Mode.INSURANCE)) {
            res = days*35;
        }
        while (true){
            System.out.println("You have to pay $" + res + " as cost of your stay time in HIS\n" +
                    "Do you pay it now ?\nY/N : ");
            String choice = scanner.next();
            if (choice.toLowerCase().equals("y")){
                System.out.println("The payment accepted, you are now free to go!");
                this.status = Status.CheckedOut;
                break;
            }else if (choice.toLowerCase().equals("n")){
                System.out.println("Ok fine, sounds delicious to stay more here :)");
                break;
            }else{
                System.out.println("Invalid input, try again !");
            }
        }
    }
}
