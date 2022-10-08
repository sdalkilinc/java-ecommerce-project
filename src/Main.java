import category.Category;

import java.util.Scanner;

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
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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

    private static String[] prepareMenuOptions(){
        return new String[] {"List Categories", "List Products", "List Discount", "See Balance", "Add Balance",
        "Place an order", "See cart", "See order details", "See your address", "Close App"};
    }

}
