import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WaiterPanel extends JPanel {
    private AdminPanel adminPanel;
    private ArrayList<Order> orders;
    private JComboBox<String> menuComboBox;
    private JTextField tableField;
    private JTextField quantityField;
    private JList<String> ordersDisplay;
    private DefaultListModel<String> ordersListModel;

    public WaiterPanel(JPanel mainPanel, AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
        this.orders = new ArrayList<>();
        this.ordersListModel = new DefaultListModel<>();

        setLayout(new BorderLayout());
        JLabel title = new JLabel("Waiter Panel", SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        menuComboBox = new JComboBox<>(new String[]{"Burger", "Chicken Shawarma", "Pizza", "Pasta"});
        tableField = new JTextField(5);
        quantityField = new JTextField(3);

        JButton addOrderButton = new JButton("Add Order");
        JButton viewOrdersButton = new JButton("View Orders");
        JButton updateOrderButton = new JButton("Update Order");
        JButton deleteOrderButton = new JButton("Delete Order");
        JButton logoutButton = new JButton("Logout");

        addOrderButton.addActionListener(e -> addOrder());
        viewOrdersButton.addActionListener(e -> viewOrders());
        updateOrderButton.addActionListener(e -> updateOrder());
        deleteOrderButton.addActionListener(e -> deleteOrder());

        // Logout functionality
        logoutButton.addActionListener(e -> {
            CardLayout layout = (CardLayout) mainPanel.getLayout();
            layout.show(mainPanel, "Login"); // Switch to login panel
        });

        // Panel for input and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Table Number:"));
        inputPanel.add(tableField);
        inputPanel.add(new JLabel("Menu Item:"));
        inputPanel.add(menuComboBox);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(addOrderButton);
        inputPanel.add(viewOrdersButton);
        inputPanel.add(updateOrderButton);
        inputPanel.add(deleteOrderButton);
        inputPanel.add(logoutButton);

        add(inputPanel, BorderLayout.CENTER);

        // Display orders
        ordersDisplay = new JList<>(ordersListModel);
        JScrollPane ordersScrollPane = new JScrollPane(ordersDisplay);
        add(ordersScrollPane, BorderLayout.EAST);
    }

    private void addOrder() {
        String dish = (String) menuComboBox.getSelectedItem();
        try {
            int tableNumber = Integer.parseInt(tableField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            Order order = new Order(tableNumber, dish, quantity);
            orders.add(order);
            ordersListModel.addElement(order.toString());
            adminPanel.addOrder(order);
            JOptionPane.showMessageDialog(this, "Order Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewOrders() {
        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No orders to display.", "Orders", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder orderList = new StringBuilder("Current Orders:\n");
            for (Order order : orders) {
                orderList.append(order).append("\n");
            }
            JOptionPane.showMessageDialog(this, orderList.toString(), "Orders", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateOrder() {
        int selectedIndex = ordersDisplay.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select an order to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Order selectedOrder = orders.get(selectedIndex);
        String newDish = JOptionPane.showInputDialog(this, "Enter new dish:", selectedOrder.getDish());
        if (newDish != null && !newDish.isEmpty()) {
            selectedOrder.setDish(newDish);
        }

        String newQuantityStr = JOptionPane.showInputDialog(this, "Enter new quantity:", selectedOrder.getQuantity());
        try {
            if (newQuantityStr != null && !newQuantityStr.isEmpty()) {
                int newQuantity = Integer.parseInt(newQuantityStr);
                selectedOrder.setQuantity(newQuantity);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid quantity input.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update the displayed order in the list
        ordersListModel.set(selectedIndex, selectedOrder.toString());
        JOptionPane.showMessageDialog(this, "Order updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteOrder() {
        int selectedIndex = ordersDisplay.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select an order to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this order?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            orders.remove(selectedIndex);
            ordersListModel.remove(selectedIndex);
            JOptionPane.showMessageDialog(this, "Order deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
