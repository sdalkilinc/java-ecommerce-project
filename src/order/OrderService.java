package order;

public interface OrderService {

    String placeOrder(Cart cart);

    String refund(Cart cart);
}
