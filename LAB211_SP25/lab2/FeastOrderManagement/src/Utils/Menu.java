package Utils;

import Bussiness.Service;

/**
 *
 * @author LGD
 */
public class Menu {
    public void mainMenu() {
        Service service = new Service();
        
        int choice;

        do {
            System.out.println("============ Main Menu ============");
            System.out.println("1. Register customers");
            System.out.println("2. Update customer information");
            System.out.println("3. Search for customer information by name");
            System.out.println("4. Display feast menus");
            System.out.println("5. Place a feast order");
            System.out.println("6. Update order information");
            System.out.println("7. Save data to file");
            System.out.println("8. Display Customer or Order lists");
            System.out.println("0. Exit");
            
            choice = InputData.inputChoice("Enter choice: ", 0, 8); // consume the newline character
            
            switch (choice) {
                case 1 -> service.registerCustomer();
                case 2 -> service.updateCustomerInformation();
                case 3 -> service.searchCustomerByName();
                case 4 -> service.displayFeast();
                case 5 -> service.createOrder();
                case 6 -> service.updateOrderInformation();
                case 7 -> service.saveToFile();
                case 8 -> menuDisplayOptions(service);
                case 0 -> System.out.println("Exiting the menu...");
                default -> System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0);
    }
    
    public void menuDisplayOptions(Service service) {
        int choice;
        
        do {
            System.out.println("=== Display Customer or Order lists ===");
            System.out.println("1. Display Customer List");
            System.out.println("2. Display Order List");
            System.out.println("0. Back");
            
            choice = InputData.inputChoice("Enter choice: ", 0, 2); // consume the newline character

            switch (choice) {
                case 1 -> service.displayCustomerList();
                case 2 -> service.displayOrderList();
                case 0 -> mainMenu();
                default -> throw new AssertionError();
            }
            
        } while (choice != 0);
    }
}
