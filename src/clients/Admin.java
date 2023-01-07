package clients;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Admin extends Client {
    final static String id = "001";

    public Admin() {
        codeCounter += 1;
        this.setUsername("admin");
        this.name = null;
        this.lastName = null;
        this.setPassword("admin");
        this.roll = clientRoll.ADMIN;
    }

    @Override
    public void Menu() {

    }

    public void listUsers(ArrayList<Client> clients) {
        String repeat = new String(new char[40]).replace("\0", "-");
        for (Client client :
                clients) {
            System.out.println(repeat);
            client.whoAmI();
        }
        System.out.println(repeat);
    }

    public ArrayList<Client> searchUser(ArrayList<Client> clients, String part) {
        ArrayList<Client> res = new ArrayList<>();
        for (Client client :
                clients) {
            if (client.lastName.toLowerCase().contains(part.toLowerCase())) {
                res.add(client);
            }
        }
        return res;
    }

    //add
    public void addPhysician(String username, String password, String name, String lastName,
                             String field, String record, Sex sex, ArrayList<Client> clients) {
        Physician doctor = new Physician(username, password, name, lastName, field, record, sex);
        codeCounter += 1;
        if (Client.codeCounter >= 100)
            doctor.setCode(codeCounter.toString()) ;
        else if (Client.codeCounter >= 10)
            doctor.setCode("0" + codeCounter.toString());
        else
            doctor.setCode("00" + codeCounter.toString());
        Physician.added.add(doctor);
        clients.add(doctor);
    }

    public void addNurse(String username, String password, String name, String lastName,
                         String record, Sex sex, ArrayList<Client> clients) {
        Nurse nurse = new Nurse(username, password, name, lastName, record, sex);
        codeCounter += 1;
        if (Client.codeCounter >= 100)
            nurse.setCode(codeCounter.toString());
        else if (Client.codeCounter >= 10)
            nurse.setCode( "0" + codeCounter.toString());
        else
            nurse.setCode( "00" + codeCounter.toString());
        Nurse.added.add(nurse);
        clients.add(nurse);
    }

    public void addPatient(String username, String password, String name, String lastName,
                           Integer age, Sex sex, String disease, Mode mode, ArrayList<Client> clients) {
        Patient patient = new Patient(username, password, name, lastName, age, sex, disease, mode);
        codeCounter += 1;
        if (Client.codeCounter >= 100)
            patient.setCode( codeCounter.toString());
        else if (Client.codeCounter >= 10)
            patient.setCode("0" + codeCounter.toString());
        else
            patient.setCode("00" + codeCounter.toString());
        Patient.added.add(patient);
        clients.add(patient);
    }

    public boolean deleteClient(String code, ArrayList<Client> clients) {
        boolean found = false;
        for (Client client: clients) {
            if (Objects.equals(client.getCode(), code)) {
                clients.remove(client);
                found = true;
                break;
            }
        }
        return found;
    }
    public void changePassword(String password) {
        this.setPassword(password);
    }

}
