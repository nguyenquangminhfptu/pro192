package controller;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CandidateList;
import view.Menu;

public class CandidateListView extends Menu<String> {

    static String title = "\n|***** Candidate Management System *****|";
    static String[] arr = {"Load all candidates from file", "Display all candidates", "Add a candidate", 
                            "Search candidate(s) by name", "Delete a candidate by ID", 
                            "Display all candidates in descending order by grades", "Exit"};
    Menu<String> searchMenu;
    
    public CandidateListView() throws ParseException 
    {
        super(title, arr);
    }

    @Override
    public void execute(int n) 
    {
        switch (n)
        {
            case 1 ->
                CandidateList.readFile();
            case 2 ->
                CandidateList.printList();
            case 3 ->
                CandidateList.addCandidate();
            case 4 ->
            {
                try {
                    CandidateList.searchCandidate();
                } catch (ParseException ex) {
                    Logger.getLogger(CandidateListView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case 5 ->
                CandidateList.deleteCandidate();
            case 6 ->
                CandidateList.sortCandidate();
            case 7 ->
                System.exit(0);
            default ->
                System.out.println("INVALID");
        }
    }
    
    public static void main(String[] args) throws ParseException
    {
        new CandidateList();
        CandidateListView manComObj = new CandidateListView();
        manComObj.run();
    }
}