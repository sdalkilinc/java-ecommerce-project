import balance.Balance;
import balance.CustomerBalance;
import balance.GiftCardBalance;
import category.Category;
import discount.Discount;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        DataGenerator.createCustomer();
        DataGenerator.createCategory();
        DataGenerator.createProduct();
        DataGenerator.createBalance();
        DataGenerator.createDiscount();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Select Customer");
        for(int i=0; i<StaticConstants.CUSTOMER_LIST.size(); i++){
            System.out.println("Type " + i + " for customer:" + StaticConstants.CUSTOMER_LIST.get(i).getUserName());
        }

        Customer customer = StaticConstants.CUSTOMER_LIST.get(scanner.nextInt());
        Cart cart = new Cart(customer);

        while (true){

            System.out.println("What would you like to do? Just type id for selection");

            for (int i = 0; i < prepareMenuOptions().length; i++) {
                System.out.println(i + "-" + prepareMenuOptions()[i]);
            }

            int menuSelection = scanner.nextInt();

            switch (menuSelection){
                case 0:
                    for(Category category: StaticConstants.CATEGORY_LIST){
                        System.out.println("Category Code" + category.generateCategoryCode() + " category name:" + category.getName());
                    }
                    break;
                case 1:
                    try {
                        for(Product product: StaticConstants.PRODUCT_LIST){
                            System.out.println("Product name: " + product.getName() + "Product category name:" + product.getCategoryName());
                        }
                    }catch (Exception e){
                        System.out.println("Product could not print because category not found for product name:" + e.getMessage().split(",")[1]);
                    }
                    break;
                case 2:
                    for(Discount discount: StaticConstants.DISCOUNT_LIST){
                        System.out.println("Discount name: " + discount.getName() + "discount threshold amount: " + discount.getThresholdAmount());
                    }
                    break;
                case 3:
                    CustomerBalance cBalance = findCustomerBalance(customer.getId());
                    GiftCardBalance gBalance = findGiftCardBalance(customer.getId());
                    double totalBalance = cBalance.getBalance() + gBalance.getBalance();
                    System.out.println("Total Balance:" + totalBalance);
                    System.out.println("Customer Balance:" + cBalance.getBalance());
                    System.out.println("Gift Card Balance:" + gBalance.getBalance());
                    break;
                case 4:
                    CustomerBalance customerBalance = findCustomerBalance(customer.getId());
                    GiftCardBalance giftCardBalance = findGiftCardBalance(customer.getId());
                    System.out.println("Which Account would you like to add?");
                    System.out.println("Type 1 for Customer Balance:" + customerBalance.getBalance());
                    System.out.println("Type 2 for Gift Card Balance:" + giftCardBalance.getBalance());
                    int balanceAccountSelection = scanner.nextInt();
                    System.out.println("How much you would like to add?");
                    double additionalAmount = scanner.nextInt();

                    switch (balanceAccountSelection){
                        case 1:
                            customerBalance.addBalance(additionalAmount);
                            System.out.println("New Customer Balance:" + customerBalance.getBalance());
                            break;
                        case 2:
                            giftCardBalance.addBalance(additionalAmount);
                            System.out.println("New Gift Card Balance:" + giftCardBalance.getBalance());
                            break;
                    }
                    break;
                case 5:
                    Map<Product, Integer> map = new HashMap<>();
                    cart.setProductMap(map);
                    while (true){
                        System.out.println("Which product you want to add to your cart. For exit product selection Type: exit");
                        for(Product product: StaticConstants.PRODUCT_LIST){
                            try {
                                System.out.println(
                                        "id:" + product.getId() + "price:" + product.getPrice() +
                                        "product category" + product.getCategoryName() +
                                                "stock:" + product.getRemainingStock() +
                                                "product delivery due" + product.getDeliveryDueDate()
                                );
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }

                        String productId = scanner.next();
                        try {
                            Product product = findProductById(productId);
                            if(!putItemToCartIfStockAvailable(cart, product)){
                                System.out.println("Stock is insufficient. Please try again");
                                continue;
                            }
                        } catch (Exception e) {
                            System.out.println("Product does not exist. Please try again");
                            continue;
                        }
                        System.out.println("Do you want to add more product. Type Y for adding more, N for exit");
                        String decision = scanner.next();
                        if(!decision.equals("Y")){
                            break;
                        }
                    }

                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;

            }
        }
    }

    private static boolean putItemToCartIfStockAvailable(Cart cart, Product product) {
        System.out.println("Please provide product count:");
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();

        Integer cartCount = cart.getProductMap().get(product);
        if(cartCount != null && product.getRemainingStock() > cartCount+count){
            cart.getProductMap().put(product, cartCount+count);
            return true;
        } else if (product.getRemainingStock()>count){
            cart.getProductMap().put(product, count);
            return true;
        }
        return false;
    }

    private static Product findProductById(String productId) throws Exception {

        for(Product product: StaticConstants.PRODUCT_LIST){
            if(product.getId().toString().equals(productId)){
                return product;
            }
        }
        throw new Exception("Product not found");
    }


    private static CustomerBalance findCustomerBalance(UUID customerId){

        for(Balance customerBalance: StaticConstants.CUSTOMER_BALANCE_LIST){
            if(customerBalance.getCustomerId().toString().equals(customerId.toString())){
                return (CustomerBalance) customerBalance;
            }
        }
       CustomerBalance customerBalance = new CustomerBalance(customerId, 0d);
       StaticConstants.CUSTOMER_BALANCE_LIST.add(customerBalance);

        return customerBalance;
    }

    private static GiftCardBalance findGiftCardBalance(UUID customerId){

        for(Balance giftCardBalance: StaticConstants.GIFT_CARD_BALANCE_LIST){
            if(giftCardBalance.getCustomerId().toString().equals(customerId.toString())){
                return (GiftCardBalance) giftCardBalance;
            }
        }

        GiftCardBalance giftCardBalance = new GiftCardBalance(customerId, 0d);
        StaticConstants.GIFT_CARD_BALANCE_LIST.add(giftCardBalance);

        return giftCardBalance;
    }



    private static String[] prepareMenuOptions(){
        return new String[] {"List Categories", "List Products", "List Discount", "See Balance", "Add Balance",
        "Place an order", "See cart", "See order details", "See your address", "Close App"};
    }

}
