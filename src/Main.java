import clients.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static javafx.application.Platform.exit;

public class Main {
    static void status() {

    }

    static void logIn(ArrayList<Client> clients, Admin admin) {
        int attempts = 3;
        Scanner scanner = new Scanner(System.in);
        String username, password;
        System.out.println("Welcome to login page!\nthis page is just designed for admin, " +
                "physicians, nurses and patients.\nNow you can enter your information to log in:");

        boolean flag = true;
        do {
            System.out.println("you have " + attempts + " left");
            System.out.print("user name :\n>>");
            username = scanner.next();
            System.out.print("password :\n>>");
            password = scanner.next();
            clientRoll roll = clientRoll.NONNAMED;
            String code = null;
            boolean wrongPass = false;
            for (Client c :
                    clients) {
                if (c.amI(username, password)) {
                    code = c.getCode();
                    roll = c.roll;
                    break;
                } else if (c.wrongPass(username, password)) {
                    wrongPass = true;
                    break;
                }
            }
//            System.out.println(roll);
            if (!wrongPass) {
                switch (roll) {
                    case ADMIN:
                        System.out.println("accepted");
                        Panels.adminPanel(clients, admin);
                        flag = true;
                        break;
                    case PHYSICIAN:
                        Physician doctor =null;
                        for (Physician physician : Physician.added) {
                            if (physician.getCode().equals(code)){
                                doctor = physician;
                                break;
                            }
                        }
                        Panels.physicianPanel(clients, Objects.requireNonNull(doctor));
                        flag = true;
                        break;
                    case NURSE:
                        Nurse nurse = null;
                        for (Nurse nrs : Nurse.added) {
                            if (nrs.getCode().equals(code)) {
                                nurse = nrs;
                                break;
                            }
                        }
                        Panels.nursePanel(clients, nurse);
                        flag = true;
                        break;
                    case PATIENT:
                        Patient patient = null;
                        for (Patient patient1 : Patient.added) {
                            if (patient1.getCode().equals(code)) {
                                patient = patient1;
                                break;
                            }
                        }
                        Panels.patientPanel(clients, patient);
                        flag = true;
                        break;
                    default:
                        System.out.println("User name not found!");
                        flag = false;
                }
            } else {
                flag = false;
                attempts -= 1;
                System.out.println("User not found, you have " +
                        attempts + " left!");
                if (attempts == 0) {
                    System.out.println("you have to wait for 2 minutes :)");
                    attempts = 3;
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        System.out.println("error");
                        throw new RuntimeException(e);
                    }
                    System.out.println("Ok, try again.");
                }
            }
        } while (!flag);
    }


    static public void first(ArrayList<Client> clients, Admin admin) {
        String[] options = {"Log in", "Check Hospital", "Exit"};
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Welcome to Ehsan Universal Hospital for all people in world!");
            System.out.println("Choose an option to continue :");

            for (int i = 0; i < 3; i++) {
                StringBuilder builder = new StringBuilder();
                builder.append(i + 1);
                builder.append(". ");
                builder.append(options[i]);
                System.out.println(builder.toString());
            }
            System.out.println(">>");
            String option = scanner.next();
            switch (option) {
                case "1":
                    logIn(clients, admin);
                    break;
                case "2":
                    status();
                    break;
                case "3":
                    System.out.println("Nice to meet you :)\nhope to see you again!");
                    exit();
                    break;
                default:
                    System.out.println("Wrong input, try again");
            }
        } while (true);
    }

    public static void main(String[] args) {
        ArrayList<Client> allUsers = new ArrayList<Client>();
        Admin admin = new Admin();
//        System.out.println();
        allUsers.add(admin);
        first(allUsers, admin);
    }
}