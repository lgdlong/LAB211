package Utils;


import java.util.Scanner;

public class InputData {
    public static int inputChoice(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int choice;
        
        do {
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());
            
        } while (choice < min || choice > max || choice < 0);
        
        return choice;
    }
    
    
    
    
}
