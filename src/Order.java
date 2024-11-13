public class Order {
    private int tableNumber;
    private String dish;
    private int quantity;

    // Constructor
    public Order(int tableNumber, String dish, int quantity) {
        this.tableNumber = tableNumber;
        this.dish = dish;
        this.quantity = quantity;
    }

    // Getters
    public int getTableNumber() {
        return tableNumber;
    }

    public String getDish() {
        return dish;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters
    public void setDish(String dish) {
        this.dish = dish;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Table " + tableNumber + " - Dish: " + dish + " - Quantity: " + quantity;
    }
}
