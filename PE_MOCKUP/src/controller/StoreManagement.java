package controller;

import java.text.ParseException;
import model.Bi_Store;
import view.Menu;

public class StoreManagement extends Menu<String> {

    String title = "\n|***** Bicycle Store *****|";
    static String[] arr = {"Display all bicycles", "Add a bicycle", "Delete a bicycle", 
                            "Display the bicycle with the least time to move", "Exit"};
    Menu<String> searchMenu;
    
    public StoreManagement() throws ParseException 
    {
        super("|***** Manage bicycles *****|", arr);
    }

    @Override
    public void execute(int n) 
    {
        switch (n)
        {
            case 1 ->
                Bi_Store.printList();
            case 3 ->
                Bi_Store.deleteBike();
            case 2 ->
                Bi_Store.addBike();
            case 4 ->
                Bi_Store.displayBike();
            case 5 ->
                System.exit(0);
            default ->
                System.out.println("INVALID");
        }
    }
    
    public static void main(String[] args) throws ParseException
    {
        new Bi_Store();
        StoreManagement manComObj = new StoreManagement();
        manComObj.run();
    }
}