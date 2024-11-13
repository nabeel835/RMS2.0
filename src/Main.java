import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Restaurant Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel mainPanel = new JPanel(new CardLayout());

        UserDatabase userDatabase = new UserDatabase(); // Initialize user database

        LoginPanel loginPanel = new LoginPanel(mainPanel, userDatabase);
        AdminPanel adminPanel = new AdminPanel(mainPanel, userDatabase);
        WaiterPanel waiterPanel = new WaiterPanel(mainPanel, adminPanel);

        mainPanel.add(loginPanel, "Login");
        mainPanel.add(adminPanel, "Admin");
        mainPanel.add(waiterPanel, "Waiter");

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
