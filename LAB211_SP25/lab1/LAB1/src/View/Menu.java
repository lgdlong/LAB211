package View;

import Utils.InputData;
import Service.RegistrationService;

public class Menu {
    public static void mainMenu() {
        int choice;
        do {
            System.out.println("\n==== Main Menu ====");
            System.out.println("1. Register");
            System.out.println("2. Manage Registration");
            System.out.println("0. Exit");

            choice = InputData.inputChoice(0, 2);

            switch (choice) {
                case 1:
                    RegistrationService.createRegistration();
                    break;
                case 2:
                    managementMenu();
                    break;
                case 0:
                    break;
            }
        } while (choice != 0);
    }
    
    public static void managementMenu() {
        int choice;
        do {
            System.out.println("\n==== Management Menu ====");
            System.out.println("1. Display registered list");
            System.out.println("2. Delete registration");
            System.out.println("3. Search registration by name");
            System.out.println("4. Filter by campus");
            System.out.println("5. Statistics of Registration Numbers by Location");
            System.out.println("6. Save data to File");
            System.out.println("0. Back");

            choice = InputData.inputChoice(0, 6);

            switch (choice) {
                case 1:
                    RegistrationService.displayAllRegistrations();
                    break;
                case 2:

                    break;
                case 3:
                    RegistrationService.searchByName();
                    break;
                case 4:
                    
                    break;
                case 5:
                    RegistrationService.registrationStatisticsByLocation();
                    break;
                case 6:

                    break;
                case 0:
                    mainMenu();
                    break;
                default:
                    throw new AssertionError();
            }
        } while (choice != 0);
    }
}
