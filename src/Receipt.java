import java.util.List;

public class Receipt {
    private List<Order> orders;

    public Receipt(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Receipt\n");
        receipt.append("------------------------------------------------\n");
        double totalAmount = 0;

        for (Order order : orders) {
            receipt.append("Dish: ").append(order.getDish()).append("\n");
            receipt.append("Quantity: ").append(order.getQuantity()).append("\n");
            double price = getPrice(order.getDish()) * order.getQuantity();
            receipt.append("Price: $").append(price).append("\n");
            receipt.append("------------------------------------------------\n");
            totalAmount += price;
        }

        receipt.append("Total Amount: $").append(totalAmount).append("\n");
        receipt.append("Date: ").append(java.time.LocalDate.now()).append("\n");
        receipt.append("------------------------------------------------\n");
        return receipt.toString();
    }

    private double getPrice(String dish) {
        switch (dish.toLowerCase()) {
            case "burger":
                return 5.99;
            case "chicken shawarma":
                return 7.99;
            case "pizza":
                return 8.99;
            case "pasta":
                return 6.99;
            default:
                return 0;
        }
    }
}
