package pwmanager;

import javax.swing.JFrame;
import pwmanager.LoginPage;
import pwmanager.UserPage;
import pwmanager.DatabaseOperations;

public class Main {
    public static void main(String[] args) {
        LoginPage loginPage = LoginPage.getInstance();
        loginPage.setVisible(true);
    }
}
