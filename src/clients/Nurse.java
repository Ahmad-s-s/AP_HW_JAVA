package clients;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Nurse extends Client {
    String ID;
    static Integer idCounter = 0;
    public static ArrayList<Nurse> added = new ArrayList<>();
    String record;
    static String repeat = new String(new char[40]).replace("\0", "-");
    static Scanner scanner = new Scanner(System.in);

    Nurse(String username, String password, String name, String lastName,
          String record, Sex sex) {
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
            this.ID = "0" + idCounter.toString();
        else
            this.ID = "00" + idCounter.toString();

    }

    @Override
    public void Menu() {

    }

    public void noDoctor() {
        ArrayList<Patient> all = Patient.added;
        ArrayList<Patient> noDoctor = new ArrayList<>();
        for (Patient patient :
                all) {
            if (patient.getStatus().equals(Status.ADMISSION)) {
                noDoctor.add(patient);
            }
        }
        for (Patient patient :
                noDoctor) {
            System.out.println(Nurse.repeat);
            showPatient(patient);
        }
        System.out.println(Nurse.repeat);
    }

    private void showPatient(Patient patient) {
        System.out.println("ID : " + patient.ID + "\t" + "Name : " + patient.name +
                "lastName : " + patient.lastName);
    }

    private boolean checkTime(LocalDateTime toCheck, ArrayList<String> t1, ArrayList<String> t2) {
        if (toCheck.getYear() >= Integer.parseInt(t1.get(0)) &&
                toCheck.getMonthValue() >= Integer.parseInt(t1.get(1)) &&
                toCheck.getDayOfMonth() >= Integer.parseInt(t1.get(2)) &&
                toCheck.getYear() <= Integer.parseInt(t2.get(0)) &&
                toCheck.getMonthValue() <= Integer.parseInt(t2.get(2)) &&
                toCheck.getDayOfMonth() <= Integer.parseInt(t2.get(2))) {
            return true;
        } else {
            return false;
        }
    }

    public void checkIn(ArrayList<String> t1, ArrayList<String> t2) {
        ArrayList<Patient> all = Patient.added;
        ArrayList<Patient> matched = new ArrayList<>();
        for (Patient patient :
                all) {
            if (checkTime(patient.admissionTime, t1, t2)) {
                matched.add(patient);
            }
        }
        for (Patient p : matched) {
            System.out.println(Nurse.repeat);
            showPatient(p);
        }
        System.out.println(Nurse.repeat);
    }

    public void getPres() {
        ArrayList<Patient> all = Patient.added;
        ArrayList<Patient> available = new ArrayList<>();
        Patient needMed = null;
        for (Patient patient : all) {
            if (patient.getStatus().equals(Status.TREATMENT)) {
                available.add(patient);
            }
        }
        System.out.println("Choose which patient to write prescription : ");
        for (Patient patient : available) {
            System.out.println(Nurse.repeat);
            showPatient(patient);
        }
        System.out.println(Nurse.repeat);
        while (true) {
            System.out.print("ID : ");
            String chosen = Nurse.scanner.next();
            boolean isTrue = false;
            for (Patient patient : available) {
                if (patient.ID.equals(chosen)) {
                    isTrue = true;
                    needMed = patient;
                    break;
                }
            }
            if (isTrue) {
                break;
            } else {
                System.out.println("unacceptable ID, try again :");
            }
        }
        System.out.println("from 1 to 3, how terrible is the patient's status ?");
        int terr;
        while (true) {
            System.out.print("Terribleness : ");
            terr = Nurse.scanner.nextInt();
            if (terr >= 1 && terr <= 3) {
                break;
            } else {
                System.out.println("not acceptable, try again !");
            }
        }
        needMed.myDoc.writeMedicine(needMed, terr);
    }
}
