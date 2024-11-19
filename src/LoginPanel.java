import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    public LoginPanel(JPanel mainPanel, UserDatabase userDatabase) {
        setLayout(new GridLayout(3, 2));
        setSize(850,480);
        setLocation(380,200);
        setBackground(new Color(230, 240, 250)); // Light blue background
        setBorder(BorderFactory.createLineBorder(new Color(100, 150, 200), 2));


        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usernameLabel.setForeground(new Color(50, 50, 150));

        


        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setForeground(new Color(50, 50, 150));

        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(100, 150, 200)); // Button background color
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/b.jpeg"));
//        Image i2 = i1.getImage().getScaledInstance(850, 480, Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel image = new JLabel(i3);
//        image.setBounds(0, 0, 850, 480);
//        add(image);

        JLabel statusLabel = new JLabel();
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        statusLabel.setForeground(Color.RED);

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
