package controller;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PatientList;
import view.Menu;

public class PatientManagement extends Menu<String> {

    String title = "\n|***** Patient Management *****|";
    static String[] arr = {"Display all patients", "Search patient", "Add patient", 
                            "Update phone number", "Exit"};
    Menu<String> searchMenu;
    
    public PatientManagement() throws ParseException 
    {
        super("|***** Patient Management *****|", arr);
    }

    @Override
    public void execute(int n) 
    {
        switch (n)
        {
            case 1 ->
                PatientList.printList();
            case 2 ->
            {
                try {
                    PatientList.searchPatient();
                } catch (ParseException ex) {
                    Logger.getLogger(PatientManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            case 3 ->
                PatientList.addPatient();
            case 4 ->
                PatientList.updatePhoneCheck();
            case 5 ->
                System.exit(0);
            default ->
                System.out.println("INVALID");
        }
    }
    
    public static void main(String[] args) throws ParseException
    {
        new PatientList();
        PatientManagement manComObj = new PatientManagement();
        manComObj.run();
    }
}