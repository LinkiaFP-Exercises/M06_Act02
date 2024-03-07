package Controler;

import config.DatabaseConnectionTest;
import view.Menu;

public class Manager {

    public static void main(String[] args) {

        DatabaseConnectionTest.testConnection();
        Menu.start();
    }
}
