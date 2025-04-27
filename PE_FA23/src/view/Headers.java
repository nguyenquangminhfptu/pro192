package view;

public class Headers 
{
    public static void printMenu()
    {
        System.out.println("|************************ School Management App ************************|");
        System.out.println("| 1. Display all students.                                              |");
        System.out.println("| 2. Search a student information.                                      |");
        System.out.println("| 3. Add a new student.                                                 |");
        System.out.println("| 4. Print all students in descending order based on average scores.    |");
        System.out.println("| 5. Display student statistics based on year.                          |");
        System.out.println("| 6. Exit the program.                                                  |");
        System.out.println("|***********************************************************************|");
    }
    
    public static void printSelection()
    {
        System.out.println("|******************** Search a student information *********************|");
        System.out.println("| 1. Search date of birth.                                              |");
        System.out.println("| 2. Search average score.                                              |");
        System.out.println("| 3. Go back to main menu.                                              |");
        System.out.println("|***********************************************************************|");
    }
    
    public static void printHeaders()
    {
        System.out.println("|**************************** List of Patients *************************|");
        System.out.println("| ID |         Name         |        Date of Birth       | Phone number |");       
    }
    //System.out.println("|*********************************************************************|");
}
