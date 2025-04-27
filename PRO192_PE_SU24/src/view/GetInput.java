package view;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class GetInput
{
    public static Scanner sc = new Scanner(System.in);
    public static int getInt(String prompt) 
    {
        int input;
        System.out.print(prompt);
        do
        {
            try
            {
                input = Integer.parseInt(sc.nextLine());
                if (input <= 0)
                    System.out.println("| >> Invalid input, please type again.");
            } catch (NumberFormatException e)
            {
                System.out.println("| >> Invalid input, please type again.");
                input = -1;
            }
        } while (input <= 0);
        return input;
    }
    
    public static long getLong(String prompt) 
    {
        long input;
        System.out.print(prompt);
        do
        {
            try
            {
                input = Long.parseLong(sc.nextLine());
                if (input <= 0)
                    System.out.println("| >> Invalid input, please type again.");
            } catch (NumberFormatException e)
            {
                System.out.println("| >> Invalid input, please type again.");
                input = -1;
            }
        } while (input <= 0);
        return input;
    }
    
    public static double getDouble(String prompt) 
    {
        double input;
        System.out.print(prompt);
        do
        {
            try
            {
                input = Double.parseDouble(sc.nextLine());
                if (input <= 0)
                    System.out.println("| >> Invalid input, please type again.");
            } catch (NumberFormatException e)
            {
                System.out.println("| >> Invalid input, please type again.");
                input = -1;
            }
        } while (input <= 0);
        return input;
    }
    
    public static String getString(String prompt)
    {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
    
    public static Date getDate(String prompt)
    {
        //System.out.print(prompt);
        String plainDate = getString(prompt);
        try
        {
            return new SimpleDateFormat("dd-MM-yyyy").parse(plainDate);
        } catch (ParseException e)
        {
            System.out.println("| >> Invalid input, format is DD/MM/YYYY.");
            return getDate(prompt);
        }
    }
    
    public static boolean getBool(String prompt) 
    {
        boolean result;
        System.out.print(prompt);
        String input = sc.nextLine();
        if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("yes") 
                || Integer.parseInt(input) == 1 || input.equalsIgnoreCase("y"))
            result = true;
        else if (input.equalsIgnoreCase("false") || input.equalsIgnoreCase("no") 
                || Integer.parseInt(input) == 0 || input.equalsIgnoreCase("n"))
            result = false;
        else 
        {
            System.out.println("| >> Invalid input, please try again.");
            result = getBool(prompt);
        }
        return result;
    }
}
