import clients.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static javafx.application.Platform.exit;


public class Panels {
    static void patientPanel(ArrayList<Client> clients, Patient patient) {
    }

    static void nursePanel(ArrayList<Client> clients, Nurse nurse) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to your panel, Nurse " + nurse.getName() + " " + nurse.getLastName() + "!");
        String[] nurseOp = {"1. Check the Patients states", "2. Change Password", "3. Log out"};
        while (true) {
            System.out.println("Here's your options :");
            for (String option : nurseOp) {
                System.out.println(option);
            }
            String choice = scanner.next();
            if (choice.equals("1")) {
                checkState(nurse);
            }else if (choice.equals("2")){
                changePassword(nurse);
            }else if (choice.equals("3")) {
                System.out.println("back to login menu, nice to meet you");
                break;
            }else {
                System.out.println("Wrong input, try again !");
            }
        }
    }

    static void checkState(Nurse nurse) {
        Scanner scanner = new Scanner(System.in);
        String[] state = {"1. No Doctor Assign", "2. Checked In Time", "3. Get prescription", "4. Back to main menu"};
        while (true){
            System.out.println("Which state do you want ro check ? ");
            for (String str : state) {
                System.out.println(str);
            }
            String choice = scanner.next();
            if (choice.equals("1")){
                System.out.println("All patient without doctor :");
                nurse.noDoctor();
                break;
            }else if (choice.equals("2")){
                ArrayList<String> date1 = new ArrayList<>(), date2 = new ArrayList<>();
                System.out.println("Enter 2 date and see the all patient that arrived to HIS between them .");
                System.out.println("Time 1 :");
                System.out.print("Year : ");
                date1.add(scanner.next());
                System.out.print("Month : ");
                date1.add(scanner.next());
                System.out.print("Day : ");
                date1.add(scanner.next());
                System.out.println("Time 2 : ");
                System.out.print("Year : ");
                date2.add(scanner.next());
                System.out.print("Month : ");
                date2.add(scanner.next());
                System.out.print("Day : ");
                date2.add(scanner.next());
                nurse.checkIn(date1, date2);
                break;
            }else if (choice.equals("3")){
                System.out.println("writing prescription for a patient :");
                nurse.getPres();
            }else if (choice.equals("4")){
                break;
            }
        }
    }

    static void changePassword(Nurse nurse) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You are going to change your password, ");
        System.out.println("The new password must contains one of special character " +
                "(!@#$%&*&) ");
        while (true) {
            System.out.print("password : ");
            String password = scanner.next();
            boolean accepted = false;
            String[] necessary = {"!", "@", "#", "$", "%", "&", "*"};
            for (String x : necessary) {
                if (password.contains(x)) {
                    accepted = true;
                    break;
                }
            }
            if (accepted) {
                nurse.setPassword(password);
                break;
            } else {
                System.out.println("Invalid password, try again!");
            }
        }
        System.out.println("Password updated successfully :)");
    }


    static void physicianPanel(ArrayList<Client> clients, Physician doctor) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to your panel, Doctor " + doctor.getName() +" "+doctor.getLastName() + "!");
        String[] docOptions = {"1. Pick Patient", "2. List of your Patient", "3. View a patient info",
                 "4. Discharge a patient", "5. Change password ",
                "6. Log out"};
        while (true) {
            System.out.println("Here's your option :");
            for (String op :
                    docOptions) {
                System.out.println(op);
            }
            String choice = scanner.next();
            if ("1".equals(choice)) {
                pickPatient(doctor);
            } else if ("2".equals(choice)) {
                System.out.println("Now you can see all information about your own patient : ");
                doctor.listPatient();
            } else if ("3".equals(choice)) {
                viewPatient(doctor);
            } else if ("4".equals(choice)) {
                System.out.println("Now you can discharge one of your patient :");
                doctor.discharge();
            } else if ("5".equals(choice)) {
                changePassword(doctor);
            } else if ("6".equals(choice)) {
                System.out.println("Have nice day in HIS doctor, goodbye!");
                break;
            } else {
                System.out.println("Invalid input, try again !");
            }
        }
    }

    static void viewPatientByName(Physician doctor) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Do you have name and last name or want to see them ?\nY/N : ");
            String choice = scanner.next();
            if (choice.toUpperCase().equals("Y")) {
                break;
            } else if (choice.toUpperCase().equals("N")) {
                doctor.listPatient();
                break;
            } else {
                System.out.println("Wrong input, try again !");
            }
        }
        while (true) {
            System.out.print("Name (zero to be null) :");
            String name = scanner.next();
            System.out.print("Last name (zero to be null) : ");
            String lastName = scanner.next();
            if (name.equals("0") && lastName.equals("0")) {
                System.out.println("both can't be null, try again !");
                continue;
            } else if (name.equals("0")) {
                name = null;
            } else if (lastName.equals("0")) {
                lastName = null;
            }
            doctor.viewPatient(name, lastName);
            break;
        }

    }

    static void viewPatientByID(Physician doctor) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Do you have an ID ?\nY/N : ");
            String choice = scanner.next();
            if (choice.toUpperCase().equals("Y")) {
                break;
            } else if (choice.toUpperCase().equals("N")) {
                doctor.showID();
                break;
            } else {
                System.out.println("Wrong input, try again !");
            }
        }

        System.out.print("ID : ");
        String choice = scanner.next();
        doctor.viewPatient(choice);

    }

    static void viewPatient(Physician doctor) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How do you want to search the patient ?");
        System.out.println("1. By name and last name\t2. By ID");
        while (true) {
            System.out.print("Choice : ");
            String choice = scanner.next();
            if ("1".equals(choice)) {
                viewPatientByName(doctor);
                break;
            } else if ("2".equals(choice)) {
                viewPatientByID(doctor);
                break;
            } else {
                System.out.println("Wrong input try again !");
            }
        }
    }

    static void pickPatient(Physician doctor) {
        File myFile = new File("C:\\Users\\Ehsan\\Desktop\\advanced_java\\HW_1\\src\\config.txt");
        ArrayList<String> possible = new ArrayList<>();
        Scanner reader = null;
        try {
            reader = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            throw new RuntimeException(e);
        }
        int line = 1;
        while (reader.hasNextLine()) {
            if (line % 2 == 1) {
                String field = reader.next();
                if (field.equals(doctor.field)) {
                    possible.addAll(Arrays.asList(reader.nextLine().split(",")));
                    break;
                }
            } else {
                reader.nextLine();
            }
            line += 1;
        }
        doctor.pick(possible);
    }

    static void changePassword(Physician doctor) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You are going to change your password, ");
        System.out.println("The new password must contains one of special character " +
                "(!@#$%&*&) ");
        while (true) {
            System.out.print("password : ");
            String password = scanner.next();
            boolean accepted = false;
            String[] necessary = {"!", "@", "#", "$", "%", "&", "*"};
            for (String x : necessary) {
                if (password.contains(x)) {
                    accepted = true;
                    break;
                }
            }
            if (accepted) {
                doctor.setPassword(password);
                break;
            } else {
                System.out.println("Invalid password, try again!");
            }
        }
        System.out.println("Password updated successfully :)");
    }


    //panel admin
    static void adminPanel(ArrayList<Client> clients, Admin admin) {
//        System.out.println("in admin panel!");
        Scanner scanner = new Scanner(System.in);
        String[] adminOptions = {"1. Show users", "2. Search a user", "3. Add user", "4. Delete user",
                "5. Change admin password", "6. Log out", "7. Exit"};
        System.out.println("Dear admin, welcome to your panel :)");
        while (true) {
            System.out.println("Here's your option :");
            for (String ind : adminOptions
            ) {
                System.out.println(ind);
            }
            System.out.print(">>");
            String choice = scanner.next();
            if ("1".equals(choice)) {
                listUser(clients, admin);
            } else if ("2".equals(choice)) {
                searchUser(clients, admin);
            } else if ("3".equals(choice)) {
                addUser(clients, admin);
            } else if ("4".equals(choice)) {
                deleteUser(clients, admin);
            } else if ("5".equals(choice)) {
                changePassword(admin);
            } else if ("6".equals(choice)) {
                System.out.println("Back to login menu");
                break;
            } else if ("7".equals(choice)) {
                System.out.println("The app is explicitly closed by admin user, you were such a bad admin :/");
                exit();
            }
        }
    }

    static void changePassword(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You are going to change admin's password, " +
                "I mean yourself :)");
        System.out.println("The new password must contains one of special character " +
                "(!@#$%&*&)");
        while (true) {
            System.out.print("password : ");
            String password = scanner.next();
            boolean accepted = false;
            String[] necessary = {"!", "@", "#", "$", "%", "&", "*"};
            for (String x : necessary) {
                if (password.contains(x)) {
                    accepted = true;
                    break;
                }
            }
            if (accepted) {
                admin.setPassword(password);
                break;
            } else {
                System.out.println("Invalid password, try again!");
            }
        }
        System.out.println("Password updated successfully :)");
    }

    static void listUser(ArrayList<Client> clients, Admin admin) {
        admin.listUsers(clients);
    }

    static void searchUser(ArrayList<Client> clients, Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert the part of lastname(or all of it)");
        System.out.print(">>");
        String part = scanner.next();
        ArrayList<Client> found = admin.searchUser(clients, part);
        admin.listUsers(found);
    }

    static void addUser(ArrayList<Client> clients, Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which user do you want to add?");
        System.out.println("1. Physician\t2. Nurse\t3. Patient");
        String choice = scanner.next();
        while (true) {
            if ("1".equals(choice)) {
                addPhysician(clients, admin);
                break;
            } else if ("2".equals(choice)) {
                addNurse(clients, admin);
                break;
            } else if ("3".equals(choice)) {
                addPatient(clients, admin);
                break;
            } else {
                System.out.println("Wrong input!, try again :");
            }
        }
    }

    static void addPatient(ArrayList<Client> clients, Admin admin) {
        Scanner scanner = new Scanner(System.in);
        String username, password, name, lastName, record, disease;
        Sex sex = Sex.NONE_BINARY;
        Integer age;
        Mode mode = null;
        while (true) {
            System.out.print("Username : ");
            username = scanner.next();
            boolean accepted = true;
            for (Client client : clients) {
                if (Objects.equals(username, client.getUsername())) {
                    System.out.println("username is nit acceptable, try again");
                    accepted = false;
                }
            }
            if (accepted)
                break;
        }
        while (true) {
            System.out.print("password : ");
            password = scanner.next();
            boolean accepted = false;
            String[] necessary = {"!", "@", "#", "$", "%", "&", "*"};
            for (String x : necessary) {
                if (password.contains(x)) {
                    accepted = true;
                    break;
                }
            }
            if (accepted)
                break;
            else {
                System.out.println("Invalid password, try again!");
            }
        }
        System.out.print("Name : ");
        name = scanner.next();
        System.out.print("last name : ");
        lastName = scanner.next();
        System.out.print("How old is patient? : ");
        age = scanner.nextInt();
        String test;
        do {
            System.out.println("• " + Sex.MALE +
                    "\t• " + Sex.FEMALE + "\t• " + Sex.NONE_BINARY);
            System.out.print("Sex : ");
            test = scanner.next();
            if (Objects.equals(test.toLowerCase(), Sex.MALE.toString().toLowerCase()))
                sex = Sex.MALE;
            else if (Objects.equals(test.toLowerCase(), Sex.FEMALE.toString().toLowerCase()))
                sex = Sex.FEMALE;
            else if (Objects.equals(test.toLowerCase(), Sex.NONE_BINARY.toString().toLowerCase()))
                sex = Sex.NONE_BINARY;
            else
                test = null;
        } while (test == null);
        System.out.println("Done");
        do {
            System.out.println("• " + Mode.INSURANCE +
                    "\t• " + Mode.NORMAL + "\t• " + Mode.VIP);
            System.out.print("Mode : ");
            test = scanner.next();
            if (Objects.equals(test.toLowerCase(), Mode.INSURANCE.toString().toLowerCase()))
                mode = Mode.INSURANCE;
            else if (Objects.equals(test.toLowerCase(), Mode.NORMAL.toString().toLowerCase()))
                mode = Mode.NORMAL;
            else if (Objects.equals(test.toLowerCase(), Mode.VIP.toString().toLowerCase()))
                mode = Mode.VIP;
            else
                test = null;
        } while (test == null);
        File myFile = new File("C:\\Users\\Ehsan\\Desktop\\advanced_java\\HW_1\\src\\config.txt");
        Scanner reader = null;
        try {
            reader = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            throw new RuntimeException(e);
        }

        int lineCounter = 1;
        ArrayList<String> options = new ArrayList<>();
        while (reader.hasNextLine()) {
            if (lineCounter % 2 == 0) {
                options.addAll(Arrays.asList(reader.nextLine().toLowerCase().split(",")));
            } else
                reader.nextLine();
            lineCounter += 1;
        }
        System.out.println("Here's your option for diseases, if you can't see it you can add it later :"); //to-do
        do {
            for (String str :
                    options) {
                System.out.print(str + "\t");
            }
            ;
            System.out.println();
            System.out.print("Disease : ");
            disease = scanner.nextLine();
            disease = disease.toLowerCase();
        } while (!options.contains(disease));
        reader.close();
        try {
            admin.addPatient(username, password, name, lastName, age, sex, disease, mode, clients);
            System.out.println("Patient added successfully!");
        } catch (Exception e) {
            System.out.println("Couldn't add physician :(");
            throw new RuntimeException(e);
        }


    }

    static void addNurse(ArrayList<Client> clients, Admin admin) {
        Scanner scanner = new Scanner(System.in);
        String username, password, name, lastName, record;
        Sex sex = Sex.NONE_BINARY;
        while (true) {
            System.out.print("Username : ");
            username = scanner.next();
            boolean accepted = true;
            for (Client client : clients) {
                if (Objects.equals(username, client.getUsername())) {
                    System.out.println("username is nit acceptable, try again");
                    accepted = false;
                }
            }
            if (accepted)
                break;
        }
        while (true) {
            System.out.print("password : ");
            password = scanner.next();
            boolean accepted = false;
            String[] necessary = {"!", "@", "#", "$", "%", "&", "*"};
            for (String x : necessary) {
                if (password.contains(x)) {
                    accepted = true;
                    break;
                }
            }
            if (accepted)
                break;
            else {
                System.out.println("Invalid password, try again!");
            }
        }
        System.out.print("Name : ");
        name = scanner.next();
        System.out.print("last name : ");
        lastName = scanner.next();
        System.out.print("Record : ");
        record = scanner.nextLine();
        String test;
        do {
            System.out.println("• " + Sex.MALE +
                    "\t• " + Sex.FEMALE + "\t• " + Sex.NONE_BINARY);
            System.out.print("Sex : ");
            test = scanner.next();
            if (Objects.equals(test.toLowerCase(), Sex.MALE.toString().toLowerCase()))
                sex = Sex.MALE;
            else if (Objects.equals(test.toLowerCase(), Sex.FEMALE.toString().toLowerCase()))
                sex = Sex.FEMALE;
            else if (Objects.equals(test.toLowerCase(), Sex.NONE_BINARY.toString().toLowerCase()))
                sex = Sex.NONE_BINARY;
            else
                test = null;
        } while (test == null);
        try {
            admin.addNurse(username, password, name, lastName, record, sex, clients);
            System.out.println("Nurse added successfully!");
        } catch (Exception e) {
            System.out.println("Couldn't add physician :(");
            throw new RuntimeException(e);
        }
    }

    static void addPhysician(ArrayList<Client> clients, Admin admin) {
        Scanner scanner = new Scanner(System.in);
        String username, password, name, lastName, field, record;
        Sex sex = Sex.NONE_BINARY;
        while (true) {
            System.out.print("Username : ");
            username = scanner.next();
            boolean accepted = true;
            for (Client client : clients) {
                if (Objects.equals(username, client.getUsername())) {
                    System.out.println("username is nit acceptable, try again");
                    accepted = false;
                }
            }
            if (accepted)
                break;
        }
        while (true) {
            System.out.print("password : ");
            password = scanner.next();
            boolean accepted = false;
            String[] necessary = {"!", "@", "#", "$", "%", "&", "*"};
            for (String x : necessary) {
                if (password.contains(x)) {
                    accepted = true;
                    break;
                }
            }
            if (accepted)
                break;
            else {
                System.out.println("Invalid password, try again!");
            }
        }
        System.out.print("Name : ");
        name = scanner.next();
        System.out.print("last name : ");
        lastName = scanner.next();
        File myFile = new File("C:\\Users\\Ehsan\\Desktop\\advanced_java\\HW_1\\src\\config.txt");
        Scanner reader = null;
        try {
            reader = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            throw new RuntimeException(e);
        }
        System.out.println("Here's your option for field, if you can't see it you can add it later :"); //to-do
        int lineCounter = 1;
        ArrayList<String> options = new ArrayList<>();
        while (reader.hasNextLine()) {
            if (lineCounter % 2 == 1) {
                options.add(reader.nextLine());
            } else
                reader.nextLine();
            lineCounter += 1;
        }
        do {
            for (String str :
                    options) {
                System.out.print(str + "\t");
            }
            ;
            System.out.println();
            System.out.print("Field : ");
            field = scanner.next();
            field = field.toUpperCase();
        } while (!options.contains(field));
        reader.close();
        System.out.print("Record : ");
        record = scanner.nextLine();
        String test;
        do {
            System.out.println("• " + Sex.MALE +
                    "\t• " + Sex.FEMALE + "\t• " + Sex.NONE_BINARY);
            System.out.print("Sex : ");
            test = scanner.next();
            if (Objects.equals(test.toLowerCase(), Sex.MALE.toString().toLowerCase()))
                sex = Sex.MALE;
            else if (Objects.equals(test.toLowerCase(), Sex.FEMALE.toString().toLowerCase()))
                sex = Sex.FEMALE;
            else if (Objects.equals(test.toLowerCase(), Sex.NONE_BINARY.toString().toLowerCase()))
                sex = Sex.NONE_BINARY;
            else
                test = null;
        } while (test == null);
        try {
            admin.addPhysician(username, password, name, lastName, field, record, sex, clients);
            System.out.println("Physician added successfully!");
        } catch (Exception e) {
            System.out.println("Couldn't add physician :(");
            throw new RuntimeException(e);
        }
    }

    static void deleteUser(ArrayList<Client> clients, Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Here you can remove a user using its code.\nDo you have a code ?\nY/N :");
        String choice = scanner.next();
        while (true) {
            if (choice.toLowerCase().equals("y")) {
                break;
            } else if (choice.toLowerCase().equals("n")) {

                while (true) {
                    System.out.println("Do you want to search a user or see all users ?");
                    System.out.println("• All\t• Search");
                    String choice2 = scanner.next();
                    if (choice2.toLowerCase().equals("all")) {
                        listUser(clients, admin);
                        break;
                    } else if (choice2.toLowerCase().equals("search")) {
                        searchUser(clients, admin);
                        break;
                    } else {
                        System.out.println("Wrong input try again !");
                    }
                }
                break;
            } else {
                System.out.println("Wrong input try again !");
            }
        }
        System.out.println("Now we are sure that you have code, enter the user code to delete it or enter \"0\"" +
                "to cancel delete.");

        while (true) {
            System.out.print("Code : ");
            String code = scanner.next();
            if (Objects.equals(code, "0")) {
                System.out.println("nothing happened !");
                break;
            } else if (Objects.equals(code, "001")) {
                System.out.println("Oh no no no you can't remove admin :)");
                continue;
            }
            boolean res = admin.deleteClient(code, clients);
            if (res)
                break;
            else
                System.out.println("not a valid code try again :");
        }
    }
}


