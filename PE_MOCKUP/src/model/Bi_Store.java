package model;

import controller.StoreManagement;
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

public class Bi_Store 
{
    private static ArrayList<Bicycle> list = new ArrayList<>();

    public Bi_Store() throws ParseException 
    {
        readFile();
    }
    
    public static void readFile() 
    {
        try (BufferedReader br = new BufferedReader(new FileReader("InputFile.txt"))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                //System.out.println("Reading line: " + line); // Debug print
                String[] parts = line.split(" ");
                //System.out.println("Split parts: " + Arrays.toString(parts)); // Debug print
                if (parts.length == 4 && parts[0].matches("B[0-9]{1,2}")) 
                {
                    Bicycle bi = new Bicycle();
                    bi.setCode(parts[0]);
                    bi.setSpeed(Double.parseDouble(parts[1]));
                    bi.setDistance(Double.parseDouble(parts[2]));
                    bi.setElectric(Boolean.parseBoolean(parts[3]));
                    list.add(bi);
                    //System.out.println("Added bicycle: " + bi); // Debug print
                } 
                else
                    System.out.println("| >> Skipping line: " + line);
            }
            System.out.println("| >> Total bicycles read: " + list.size());
        } catch (Exception e) 
        {
            System.out.println("| >> Error: " + e.getMessage());
        }
    }

    public boolean isEmptyList() {
        return list.isEmpty();
    }
    
    public static void printBike(Bicycle bi)
    {
        System.out.printf(" %-10s  %-5s %7.1f %10.1f %-30s", bi.getName(), bi.getCode(), bi.getSpeed(), bi.getDistance(), bi.needToRecharge());
        System.out.println(" ");
    }
    
    public static void printList()
    {
        Headers.printHeaders();
        for (Bicycle bi : list)
//            printBike(bi);
            System.out.println(bi);
    }
    
    public static boolean deleteBikeComp(Predicate<Bicycle> e)
    {
        for (Bicycle bi : list)
            return list.removeIf(e);
        return false;
    }
    
    public static void deleteBike()
    {
        try 
        {
            String searchID = GetInput.getString("| >> Enter ID (format is Bxx, with x from 0-9): ");
            if (searchID.matches("B[0-9]{1,2}"))
            {
                deleteBikeComp(e -> e.getCode().equals(searchID));
                printList();
                System.out.println("| >> Delete successfully.");
            }
            else
                System.out.println("| >> Wrong code format, please try again.");
        } catch (Exception e) 
        {
            System.out.println("| >> Error: " + e.getMessage());
        }
    }
    
    public static void addBike() 
    {
        Bicycle bi = new Bicycle();
        while (true)
        {
            String addID = GetInput.getString("| >> Enter code (format is Bxx, with x from 0-9): ");
            if (addID.matches("B[0-9]{2}"))
            {
                boolean idExists = false;
                for (Bicycle biCode : list)
                {
                    if (addID.equals(biCode.getCode()))
                    {
                        idExists = true;
                        break;
                    }
                }
                if (!idExists)
                {
                    bi.setCode(addID);
                    break;
                }
                else
                    System.out.println("| >> ID already exists, please try again.");
            }
            else
                System.out.println("| >> Wrong ID format, please try again.");
        } 
        bi.setSpeed(GetInput.getDouble("| >> Enter speed: "));
        bi.setDistance(GetInput.getDouble("| >> Enter distance: "));
        bi.setElectric(GetInput.getBool("| >> Is your bike electric? (true/false) "));
        list.add(bi);
    }

    public static void displayBike()
    {
        Bicycle minBike = null;
        double timetomove, min = 99999;
        for (Bicycle bi : list)
        {
            timetomove = bi.getDistance() / bi.getSpeed();
            if (timetomove < min) 
            {
                min = timetomove;
                minBike = bi;
            }
        }
        Headers.printHeaders();
        printBike(minBike);
        System.out.printf("| >> Time to move: %.2f\n", min);
    }
}