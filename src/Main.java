import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {

    public static final int CREATE_ACCOUNT = 1;
    public static final int LOGIN = 2;
    public static final int END = 3;


    public static void main(String[] args) {
        Store store = new Store();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("Choose option:");
            System.out.println("1- create new account");
            System.out.println("2- login");
            System.out.println("3- end");
            choice = scanner.nextInt();
            switch (choice) {
                case CREATE_ACCOUNT:
                    store.createAccount();
                    break;
                case LOGIN:
                    Person person = store.login();
                    if (person==null) {
                        break;
                    }
                    else if (person.isWorker()) {
                        System.out.println(person.introduce());
                        int choice2;
                        do {
                            choice2 = workersMenu(store, person);
                        } while (choice2!=8);
                    }
                    else {
                        System.out.println(person.introduce());
                        shopMenu(store, (Consumer) person);
                    }
                    break;
                case END:
                    break;
            }
        } while (choice!=3);

    }

    public static final int PRINT_CONSUMERS = 1;
    public static final int PRINT_VIPS = 2;
    public static final int PRINT_CONSUMERS_WHO_BOUGHT_SOMETHING = 3;
    public static final int PRINT_MOST_VALUABLE_CONSUMER = 4;
    public static final int ADD_PRODUCT = 5;
    public static final int CHANGE_PRODUCT_STATUS = 6;
    public static final int MAKE_SHOPPING = 7;
    public static final int END2 = 8;

    public static int workersMenu(Store store, Person worker) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose option");
        System.out.println("1- print consumers");
        System.out.println("2- print vips");
        System.out.println("3- print consumers who bought something");
        System.out.println("4- print most valuable consumer");
        System.out.println("5- add product");
        System.out.println("6- change status for product");
        System.out.println("7- make shopping");
        System.out.println("8- end");
        int choice = scanner.nextInt();

        switch(choice) {
            case PRINT_CONSUMERS:
                store.printConsumers(false);
                break;
            case PRINT_VIPS:
                store.printConsumers(true);
                break;
            case PRINT_CONSUMERS_WHO_BOUGHT_SOMETHING:
                store.printConsumerWhoBought();
                break;
            case PRINT_MOST_VALUABLE_CONSUMER:
                store.printMostValuableConsumer();
                break;
            case ADD_PRODUCT:
                store.addProduct();
                break;
            case CHANGE_PRODUCT_STATUS:
                store.changeProductStatus();
                break;
            case MAKE_SHOPPING:
                shopMenu(store, (Consumer) worker);
                break;
            case END2:
                break;
        }
        return choice;
    }

    public static void shopMenu(Store store,  Consumer consumer) {
        Scanner scanner = new Scanner(System.in);
        GregorianCalendar calendar = new GregorianCalendar();

        int choice;
        consumer.getCart().cleanCart();
        consumer.setTimesOfShop(consumer.getTimesOfShop()+1);
        double priceOfPurchase = 0;
        do {
            store.printAllProducts(true);
            System.out.println("Choose product");
            choice = scanner.nextInt();
            if (choice != -1 && choice <= store.getProducts().length + 1) {
                int amount = 0;
                do {
                    System.out.println("How many " + store.searchExistProduct(choice).getName() + " do you want?");
                    amount = scanner.nextInt();
                } while (amount < 1);
                consumer.addToCart(store.searchExistProduct(choice));
                priceOfPurchase = priceOfPurchase + (store.searchExistProduct(choice).priceAfterDiscount(consumer))*amount;
                System.out.println(priceOfPurchase+" nis");

            }
        } while (choice!=-1);
        consumer.setSumPurchases(priceOfPurchase);
        consumer.setLastShop(calendar.getTime());
    }
}
