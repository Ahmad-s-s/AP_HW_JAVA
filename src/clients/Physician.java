package clients;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Physician extends Client {
    String ID;
    static Integer idCounter = 0;
    public static ArrayList<Physician> added = new ArrayList<>();
    public String field;
    String[] medicines = new String[3];
    String record;
    ArrayList<Patient> acceptedPatient;
    static String repeat = new String(new char[40]).replace("\0", "-");

    public Physician(String username, String password, String name, String lastName,
                     String field, String record, Sex sex) {
        this.setUsername(username);
        this.setPassword(password);
        this.name = name;
        this.lastName = lastName;
        this.field = field;
        this.medicines[0] = field + " pill";
        this.medicines[1] = field + " syrup";
        this.medicines[2] = field + " ampoule";
        this.record = record;
        this.sex = sex;
        this.roll = clientRoll.PHYSICIAN;
        this.acceptedPatient = new ArrayList<>();
        Physician.idCounter += 1;
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

    public void pick (ArrayList<String> possible){ //possible is ana array of those disease that he can heal
        Scanner scanner = new Scanner(System.in);
        ArrayList<Patient> all = Patient.added;
        ArrayList<Patient> hisField = new ArrayList<>();
        for (Patient patient :
                all) {
            if (possible.contains(patient.disease) && Objects.equals(patient.getStatus(), Status.ADMISSION)){
                hisField.add(patient);
            }
        }
        System.out.println("Here's all patient that you can heal :");
        for (Patient patient :
                hisField) {
            System.out.println(Physician.repeat);
            patient.introToDoc();
        }
        System.out.println(Physician.repeat);
        while (true){
            System.out.println("Choose one of them by his/her id (or zero to cancel the pick up): ");
            String chosen = scanner.next();
            if (chosen.equals("0")) {
                System.out.println("No patient picked up.");
                break;
            }
            boolean isAccepted = false;
            for (Patient patient :
                    hisField) {
                if (patient.ID.equals(chosen)) {
                    patient.setStatus(Status.TREATMENT);
                    patient.admissionTime = LocalDateTime.now();
                    this.acceptedPatient.add(patient);
                    patient.myDoc = this;
                    isAccepted = true;
                    break;
                }
            }
            if (isAccepted){
                System.out.println("Patient added successfully");
                break;
            }else{
                System.out.println("invalid ID, try again!");
            }
        }
    }

    public void listPatient () {
        for (Patient patient :
                acceptedPatient) {
            System.out.println(Physician.repeat);
            System.out.println("Name : " + patient.name);
            System.out.println("Last name : " + patient.lastName);
            System.out.println("Disease : " + patient.disease);
            System.out.print("The start of treatment : ");
            System.out.println(patient.admissionTime.getYear() + '/' + patient.admissionTime.getMonth().toString()
            + '/' + patient.admissionTime.getDayOfMonth() + '\t' + patient.admissionTime.getHour() + ':' +
                    patient.admissionTime.getMinute() + ':' + patient.admissionTime.getSecond());
            System.out.println();
        }
        System.out.println(Physician.repeat);
    }
    public void viewPatient(String name, String lastName) {
        ArrayList<Patient> res = new ArrayList<>();
        if(name == null) {
            for (Patient p :
                    acceptedPatient) {
                if (p.lastName.toLowerCase().contains(lastName.toLowerCase())) {
                    res.add(p);
                }
            }                
        }else if (lastName == null) {
            for (Patient p :
                    acceptedPatient) {
                if(p.name.toLowerCase().contains(name.toLowerCase())){
                    res.add(p);
                }
            }
        }else {
            for (Patient p :
                    acceptedPatient) {
                if (p.name.toLowerCase().contains(name.toLowerCase()) &&
                        p.lastName.toLowerCase().contains(lastName.toLowerCase())) {
                    res.add(p);
                }
            }
        }
        showResult(res);
    }
    public void viewPatient(String ID) {
        ArrayList<Patient> res = new ArrayList<>();
        for (Patient p :
                acceptedPatient) {
            if (Objects.equals(ID, p.ID)) {
                res.add(p);
                break;
            }
        }
        showResult(res);
    }

    public void showID () {
        for (Patient patient : this.acceptedPatient) {
            System.out.println(Physician.repeat);
            System.out.println("ID : " + patient.ID + " Disease : " + patient.disease);
        }
        System.out.println(Physician.repeat);
    }
    private void showResult(ArrayList<Patient> patients) {

        System.out.println("Here's the result of your search : ");
        for (Patient p :
                patients) {
            System.out.println(Physician.repeat);
            System.out.println("Name : " + p.name);
            System.out.println("Last name : " + p.lastName);
            System.out.println("Sex & Age : " + p.sex + ", " + p.age);
            System.out.println("Mode : " + p.mode);
            System.out.println("Disease : " + p.disease);
            System.out.println("Medicines : ");
            for (String medicine :
                    p.getMedicines()) {
                System.out.print(medicine + '\t');
            }
            System.out.println();
        }
        System.out.println(Physician.repeat);
    }
    public void writeMedicine(Patient patient, int needs) {
        for (int i = 0; i < needs; i++) {
            patient.addMedicine(medicines[i]);
        }
    }
    public void discharge () {
        Patient toDischarge = null;
        System.out.println("Which patient Do you want to remove ?");
        Scanner scanner = new Scanner(System.in);
        for (Patient patient :
                acceptedPatient) {
            System.out.println(Physician.repeat);
            patient.introToDoc();
        }
        System.out.println(Physician.repeat);
        while (true) {
            System.out.print("ID : ");
            String choose = scanner.next();
            boolean accepted = false;
            for (Patient patient :
                    acceptedPatient) {
                if (patient.ID.equals(choose)) {
                    accepted = true;
                    toDischarge = patient;
                    break;
                }
            }
            if (accepted){
                System.out.println("Accepted !");
                break;
            }
            else
                System.out.println("Wrong try again !");
        }
        toDischarge.dischargedTime = LocalDateTime.now();
        toDischarge.setStatus(Status.DISCHARGED);
        this.acceptedPatient.remove(toDischarge);

        String history = "The patient is now healed and his doctor was Doc." + this.name + " " + this.lastName +
                "and now the patient can go home ...";
        toDischarge.setHistory(history);
    }
//    public void add

}
