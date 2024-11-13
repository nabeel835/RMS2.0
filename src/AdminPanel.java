import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdminPanel extends JPanel {
    private ArrayList<Order> orders;
    private UserDatabase userDatabase;

    public AdminPanel(JPanel mainPanel, UserDatabase userDatabase) {
        this.orders = new ArrayList<>();
        this.userDatabase = userDatabase;

        setLayout(new BorderLayout());
        JLabel title = new JLabel("Admin Panel", SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        JButton addWaiterButton = new JButton("Add Waiter");
        JButton viewOrdersButton = new JButton("View All Orders");
        JButton generateReceiptButton = new JButton("Generate Receipts for Tables");
        JButton logoutButton = new JButton("Logout");

        addWaiterButton.addActionListener(e -> addWaiter());
        viewOrdersButton.addActionListener(e -> viewOrders());
        generateReceiptButton.addActionListener(e -> generateReceiptsForTables());

        // Logout functionality
        logoutButton.addActionListener(e -> {
            CardLayout layout = (CardLayout) mainPanel.getLayout();
            layout.show(mainPanel, "Login"); // Switch to login panel
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewOrdersButton);
        buttonPanel.add(generateReceiptButton);
        buttonPanel.add(addWaiterButton);
        buttonPanel.add(logoutButton); // Add logout button

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void viewOrders() {
        // Create a list of orders in string format
        List<String> orderList = orders.stream()
                .map(order -> order.toString())
                .collect(Collectors.toList());

        if (orderList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No orders available.", "Orders", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Display orders in a scrollable list
            JList<String> ordersDisplay = new JList<>(orderList.toArray(new String[0]));
            JScrollPane scrollPane = new JScrollPane(ordersDisplay);
            JOptionPane.showMessageDialog(this, scrollPane, "All Orders", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void generateReceiptsForTables() {
        if (!orders.isEmpty()) {
            // Group orders by table number
            List<Integer> tableNumbers = orders.stream()
                    .map(Order::getTableNumber)
                    .distinct()
                    .collect(Collectors.toList());

            for (Integer tableNumber : tableNumbers) {
                // Filter orders by the current table number
                List<Order> tableOrders = orders.stream()
                        .filter(order -> order.getTableNumber() == tableNumber)
                        .collect(Collectors.toList());

                Receipt receipt = new Receipt(tableOrders);
                JOptionPane.showMessageDialog(this, receipt.toString(), "Receipt for Table " + tableNumber, JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No orders available to generate receipts.", "Receipt Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    private void addWaiter() {
        String username = JOptionPane.showInputDialog(this, "Enter Waiter Username:");
        String password = JOptionPane.showInputDialog(this, "Enter Waiter Password:");
        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            userDatabase.addUser(username, password, "Waiter");
            JOptionPane.showMessageDialog(this, "Waiter added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
