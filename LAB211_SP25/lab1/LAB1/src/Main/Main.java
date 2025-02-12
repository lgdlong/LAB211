package Main;

import Model.Registration;
import Repository.RegistrationRepo;
import Utils.ValidationData;

public class Main {
    public static void main(String[] args) {
//        Menu.mainMenu();

        displayRegistrationHasDiscount(new RegistrationRepo());
    }

    // display all registration has discount
    public static void displayRegistrationHasDiscount(RegistrationRepo repo) {
        for (Registration r : repo.getRegistrationList()) {
            if (ValidationData.isPhoneDiscount(r.getPhoneNumber())) {
                System.out.println(r);
            }
        }
    }
}
