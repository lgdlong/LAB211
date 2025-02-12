package View;

import Utils.InputData;
import Service.Service;

public class Menu {
    public static void mainMenu() {
        int choice;
        do {
            System.out.println("\n==== Main Menu ====");
            System.out.println("1. Register");
            System.out.println("2. Update registration information");
            System.out.println("3. Display registered list");
            System.out.println("4. Delete registration");
            System.out.println("5. Search registration by name");
            System.out.println("6. Filter by campus");
            System.out.println("7. Statistics of Registration Numbers by Location");
            System.out.println("8. Save data to File");
            System.out.println("0. Exit");

            choice = InputData.inputChoice(0, 8);

            switch (choice) {
                case 1:
                    Service.createRegistration();
                    break;
                case 2:
                    Service.updateRegistration();
                    break;
                case 3:
                    Service.displayAllRegistrations();
                    break;
                case 4:
                    Service.deleteRegistration();
                    break;
                case 5:
                    Service.searchByName();
                    break;
                case 6:
                    Service.filterByCampus();
                    break;
                case 7:
                    Service.registrationStatisticsByLocation();
                    break;
                case 8:
                    Service.save();
                    break;
                case 0:
                    break;
            }
        } while (choice != 0);
    }
}
