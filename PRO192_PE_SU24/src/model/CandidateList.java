package model;

import controller.CandidateListView;
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

public class CandidateList 
{
    private static ArrayList<Candidate> list = new ArrayList<>();

    public CandidateList() throws ParseException 
    {
        //readFile();
    }
    
//    public static boolean readCandidateComp(Predicate<Candidate> e)
//    {
//        for (Candidate ca : list)
//            return list.contains(e);
//        return false;
//    }
    
    public static void readFile() 
    {
        try (BufferedReader br = new BufferedReader(new FileReader("candidates_input.txt"))) 
        {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) 
            {
                //System.out.println("| >> Reading line: " + line); // Debug print
                String[] parts = line.split(",");
                //System.out.println("| >> Split parts: " + Arrays.toString(parts)); // Debug print
                if (parts.length == 5 && parts[0].matches("C[0-9]+")) 
                {
                    Candidate ca = new Candidate();
                    ca.setId(parts[0]);
                    ca.setName(parts[1]);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    ca.setBday(sdf.parse(parts[2]));
                    ca.setGrades(Double.parseDouble(parts[3]));
                    ca.setEmail(parts[4]);
                    list.add(ca);
                    count++;
                    //System.out.println("| >> Added candidates: " + ca); // Debug print
                } 
                else
                    System.out.println("| >> Skipping line: " + line);
            }
            System.out.println("| >> Total candidates read: " + count);
            System.out.println("| >> Total candidates in the list: " + list.size());
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
        for (Candidate ca : list)
            System.out.println(ca);
    }
    
    public static void searchCandidate() throws ParseException
    {
        try 
        {
            String searchName = GetInput.getString("| >> Enter name: ");
            Headers.printHeaders();
            for (Candidate ca : list)
            {
                if (ca.getName().equalsIgnoreCase(searchName))
                    System.out.println(ca);
            }
        } catch (Exception e) 
        {
            System.out.println("| >> Error: " + e.getMessage());
        }  
    }
    public static boolean deleteCandidateComp(Predicate<Candidate> e)
    {
        for (Candidate ca : list)
            return list.removeIf(e);
        return false;
    }
    
    public static void deleteCandidate()
    {
        try 
        {
            String searchID = GetInput.getString("| >> Enter ID (format is Cx, with x from 0-9): ");
            if (searchID.matches("C[0-9]+"))
            {
                deleteCandidateComp(e -> e.getId().equals(searchID));
                printList();
                System.out.println("| >> Delete successfully.");
                System.out.println(" ");
            }
            else
                System.out.println("| >> Wrong code format, please try again.");
        } catch (Exception e) 
        {
            System.out.println("| >> Error: " + e.getMessage());
        }
    }
    public static void addCandidate() 
    {
        Candidate ca = new Candidate();
        while (true)
        {
            String addID = GetInput.getString("| >> Enter code (format is Cx, with x from 0-9): ");
            if (addID.matches("C[0-9]+"))
            {
                boolean idExists = false;
                for (Candidate paCode : list)
                {
                    if (addID.equals(paCode.getId()))
                    {
                        idExists = true;
                        break;
                    }
                }
                if (!idExists)
                {
                    ca.setId(addID);
                    break;
                }
                else
                    System.out.println("| >> ID already exists, please try again.");
            }
            else
                System.out.println("| >> Wrong ID format, please try again.");
        } 
        ca.setName(GetInput.getString("| >> Enter name: "));
        ca.setBday(GetInput.getDate("| >> Enter date of birth (dd-MM-yyyy): "));
        ca.setGrades(GetInput.getDouble("| >> Enter grades: "));
        ca.setEmail(GetInput.getString("| >> Enter email: "));
        list.add(ca);
    }
    
    public static void sortCandidate()
    {
        Collections.sort(list, (ca1, ca2) -> Double.compare(ca2.getGrades(), ca1.getGrades()));
        printList();
        System.out.println(" ");
    }
}