package Main;

import Bussiness.Menu;

public class Program {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        Menu menu = new Menu();
        // print main menu
        menu.mainMenu();
    }
}