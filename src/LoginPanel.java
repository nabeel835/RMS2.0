import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    public LoginPanel(JPanel mainPanel, UserDatabase userDatabase) {
        setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JLabel statusLabel = new JLabel();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (userDatabase.authenticate(username, password, "Admin")) {
                    CardLayout layout = (CardLayout) mainPanel.getLayout();
                    layout.show(mainPanel, "Admin");
                } else if (userDatabase.authenticate(username, password, "Waiter")) {
                    CardLayout layout = (CardLayout) mainPanel.getLayout();
                    layout.show(mainPanel, "Waiter");
                } else {
                    statusLabel.setText("Invalid credentials");
                }
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(statusLabel);
    }
}
