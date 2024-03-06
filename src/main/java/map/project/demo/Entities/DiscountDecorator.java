package map.project.demo.Entities;

public class DiscountDecorator implements OrderComponent {
    private final OrderComponent decoratedOrder;
    public DiscountDecorator(OrderComponent decoratedOrder) {
        this.decoratedOrder = decoratedOrder;
    }

    @Override
    public float calculateTotalAmount() {
        float originalAmount = decoratedOrder.calculateTotalAmount();
        if (isBlackFridayOrChristmas()) {
            float newAmount= (float) applyDiscount(originalAmount, 0.2);
            decoratedOrder.setTotalAmount(newAmount);
            return newAmount;

        } else {
            return originalAmount;
        }
    }

    @Override
    public String getDate() {
        return decoratedOrder.getDate();
    }

    @Override
    public void setTotalAmount(float totalAmount) {
    }

    private boolean isBlackFridayOrChristmas() {
        String[] dateParts = decoratedOrder.getDate().split("\\.");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        return (month == 11 && day >= 25 && day <= 30) || (month == 12 && day >= 20 && day <= 30);
    }

    private double applyDiscount(double amount, double discountPercentage) {
        System.out.println("The 20% discount was applied! Have an amazing Christmas!");
        return amount * (1 - discountPercentage);
    }
}
