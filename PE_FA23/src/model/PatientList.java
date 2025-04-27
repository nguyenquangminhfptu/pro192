package model;

import controller.PatientManagement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.Headers;
import view.GetInput;
import view.Menu;

public class PatientList 
{
    private static ArrayList<Patient> list = new ArrayList<>();

    public PatientList() throws ParseException 
    {
        readFile();
    }
    
    public static void readFile() 
    {
        try (BufferedReader br = new BufferedReader(new FileReader("patient.csv"))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                //System.out.println("Reading line: " + line); // Debug print
                String[] parts = line.split(",");
                //System.out.println("Split parts: " + Arrays.toString(parts)); // Debug print
                if (parts.length == 4 && parts[0].matches("P[0-9]{3}")) 
                {
                    Patient pa = new Patient();
                    pa.setId(parts[0]);
                    pa.setName(parts[1]);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    pa.setBday(sdf.parse(parts[2]));
                    pa.setPhone(parts[3]);
                    list.add(pa);
                    //System.out.println("Added bicycle: " + pa); // Debug print
                } 
                else
                    System.out.println("| >> Skipping line: " + line);
            }
            System.out.println("| >> Total patients read: " + list.size());
        } catch (Exception e) 
        {
            System.out.println("| >> Error: " + e.getMessage());
        }
    }

    public boolean isEmptyList() {
        return list.isEmpty();
    }
    
    public static void printList()
    {
        Headers.printHeaders();
        for (Patient pa : list)
            System.out.println(pa);
    }
    
    public static boolean deleteBikeComp(Predicate<Patient> e)
    {
        for (Patient pa : list)
            return list.removeIf(e);
        return false;
    }
    
    public static void searchPatient() throws ParseException
    {
        String[] searchArr = {"Search by ID", "Search by name", "Search by date of birth", 
            "Search by phone number", "Back to main menu"};
        Menu<String> searchMenu = new Menu<>("|***** Search patient *****|", searchArr)
        {
            @Override
            public void execute(int ch) 
            {
                switch (ch) 
                {
                    case 1 -> 
                    {
                        try 
                        {
                            String searchID = GetInput.getString("| >> Enter ID (format is Pxxx, with x from 0-9): ");
                            Headers.printHeaders();
                            for (Patient pa : list)
                            {
                                if (searchID.matches("P[0-9]{3}"))
                                    if (pa.getId().equals(searchID))
                                        System.out.println(pa);
                            }
                        } catch (Exception e) 
                        {
                            System.out.println("| >> Error: " + e.getMessage());
                        }   
                    }
                    case 2 -> 
                    {
                        try 
                        {
                            String searchName = GetInput.getString("| >> Enter name: ");
                            Headers.printHeaders();
                            for (Patient pa : list)
                            {
                                if (pa.getName().equalsIgnoreCase(searchName))
                                    System.out.println(pa);
                            }
                        } catch (Exception e) 
                        {
                            System.out.println("| >> Error: " + e.getMessage());
                        }
                    }
                    case 3 -> 
                    {
                        try 
                        {
                            Date searchBday = GetInput.getDate("| >> Enter date of birth (dd/MM/yyyy): ");
                            Headers.printHeaders();
                            for (Patient pa : list)
                            {
                                if (pa.getBday().equals(searchBday))
                                        System.out.println(pa);
                            }
                        } catch (Exception e) 
                        {
                            System.out.println("| >> Error: " + e.getMessage());
                        }
                    }
                    case 4 -> 
                    {
                        try 
                        {
                            String searchPhone = GetInput.getString("| >> Enter phone number: ");
                            for (Patient pa : list)
                            {
                                if (pa.getPhone().equals(searchPhone))
                                    System.out.println(pa);
                            }
                        } catch (Exception e) 
                        {
                            System.out.println("| >> Error: " + e.getMessage());
                        }
                    }
                    case 5 -> 
                    {
                        PatientManagement manComObj = null;
                        try {
                            manComObj = new PatientManagement();
                        } catch (ParseException ex) {
                            Logger.getLogger(PatientList.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        manComObj.run();
                    }
                }
            }
        };
        searchMenu.run();
    }
    
    public static void addPatient() 
    {
        Patient pa = new Patient();
        while (true)
        {
            String addID = GetInput.getString("| >> Enter code (format is Pxxx, with x from 0-9): ");
            if (addID.matches("P[0-9]{3}"))
            {
                boolean idExists = false;
                for (Patient paCode : list)
                {
                    if (addID.equals(paCode.getId()))
                    {
                        idExists = true;
                        break;
                    }
                }
                if (!idExists)
                {
                    pa.setId(addID);
                    break;
                }
                else
                    System.out.println("| >> ID already exists, please try again.");
            }
            else
                System.out.println("| >> Wrong ID format, please try again.");
        } 
        pa.setName(GetInput.getString("| >> Enter name: "));
        pa.setBday(GetInput.getDate("| >> Enter date of birth (dd/MM/yyyy): "));
        while (true)
        {
            String addPhone = GetInput.getString("| >> Enter phone number: ");
            boolean idExists = false;
            for (Patient paCode : list)
            {
                if (addPhone.equals(paCode.getPhone()))
                {
                    idExists = true;
                    break;
                }
            }
            if (!idExists)
            {
                pa.setPhone(addPhone);
                break;
            }
            else
                System.out.println("| >> Phone number already exists, please try again.");
        }
        list.add(pa);
    }

    public static void updatePhoneCheck()
    {
        
        boolean found = false;
        String oldPhone = GetInput.getString("| >> Enter old number: ");
        for (Patient pa : list)
        {
            if (pa.getPhone().equals(oldPhone))
            {
                System.out.println("| >> Phone number found.");
                found = true;
                if (found)
                {
                    String newPhone = GetInput.getString("| >> Enter new phone number: ");
                    pa.changePhone(newPhone);
                }
            }
        }
        if (!found)
                System.out.println("| >> Phone number not found.");
    }
}