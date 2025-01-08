package View;

import Utils.InputData;

public class Menu {
    public static void mainMenu() {
        System.out.println("==== Main Menu ====");
        System.out.println("1. Register");
        System.out.println("2. Manage Registration");
        System.out.println("0. Exit");
        
        int choice = InputData.inputChoice(0, 2);
        
        switch (choice) {
            case 1:
                
                break;
            case 2:
                
                break;
            case 0:
                
                break;
        }
    }
}
