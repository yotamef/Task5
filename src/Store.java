import java.util.Scanner;

public class Store {
    private Person[] consumers;
    private Person[] workers;
    private Product[] products;

    public Store () {
        consumers = new Person[0];
        workers = new Person[0];
        products = new Product[0];
    }

    public void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert product name");
        String name = scanner.nextLine();
        System.out.println("Insert price");
        int price = scanner.nextInt();
        System.out.println("true/false: is in stock/ not in stock");
        boolean inStock = scanner.nextBoolean();
        System.out.println("Insert vip percents");
        int vipPercents = scanner.nextInt();
        Product newProduct = new Product(name, price, inStock, vipPercents);
        products = newProduct.addProductToArray(products);
    }

    public void printConsumers(boolean vipCondition) {
        for (int i=0; i<consumers.length; i++) {
            if (vipCondition) {
                if (((Consumer)consumers[i]).isVip())
                    System.out.println(consumers[i]);
            }
            else {
                System.out.println(consumers[i]);
            }
        }
    }
    public void printConsumerWhoBought() {
        for (int i=0; i<consumers.length; i++) {
            if (((Consumer) consumers[i]).isBoughtSomething()) {
                System.out.println(consumers[i]);
            }
        }
    }
    public void printMostValuableConsumer() {
        double mostPurchase = 0;
        int theSpecialConsumer = -1;
        if (consumers.length>0) {
            mostPurchase = ((Consumer) consumers[0]).getSumPurchases();
            theSpecialConsumer = 0;
            for (int i=0; i<consumers.length; i++) {
                if (((Consumer) consumers[i]).getSumPurchases()>mostPurchase) {
                    mostPurchase = ((Consumer) consumers[i]).getSumPurchases();
                    theSpecialConsumer = i;
                }
            }
            if (theSpecialConsumer!=-1)
                System.out.println(consumers[theSpecialConsumer]);
        }
    }

    public void changeProductStatus() {
        Scanner scanner = new Scanner(System.in);
        printAllProducts(false);
        System.out.println("Choose product");
        int index = scanner.nextInt();
        System.out.println("Is in stock? (true or false)");
        boolean inStock = scanner.nextBoolean();
        products[index-1].setInStock(inStock);
        System.out.println("Status change successfully");
    }

    public void printAllProducts(boolean stockCondition) {
        int numbering = 1;
        for (int i=0; i<products.length; i++) {
            if (!stockCondition) {
                System.out.println(numbering + ". " + products[i]);
                numbering++;
            }
            else {
                if (products[i].isInStock()) {
                    System.out.println(numbering + ". " + products[i]);
                    numbering++;
                }
            }
        }
    }
    public Product searchExistProduct(int index) {
        int numbering = 0;
        for (int i=0; i<products.length; i++) {
            if (products[i].isInStock()) {
                numbering++;
                if (numbering==index) {
                    return products[i];
                }
            }
        }
        return null;
    }

    public Person login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1- for consumer or 2- for worker");
        int personType = scanner.nextInt();
        System.out.println("Insert username");
        String username = scanner.next();
        System.out.println("Insert password");
        String password = scanner.next();

        boolean exist = false;
        int indexOfUsername = indexOfUsername(username,personType);
        if (indexOfUsername!=-1) {
            if (personType==1) {
                if (consumers[indexOfUsername].getPassword().equals(password))
                    exist = true;
            }
            else {
                if (workers[indexOfUsername].getPassword().equals(password))
                    exist = true;
            }
        }
        if (exist&&personType==1) {
            return consumers[indexOfUsername];
        }
        else if (exist&&personType==2) {
            return workers[indexOfUsername];
        }
        else {
            System.out.println("no such user");
            return null;
        }
    }

    public void createAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1- consumer or 2- worker");
        int type = scanner.nextInt();

        String firstName;
        do {
            System.out.println("Insert first name");
            firstName = scanner.next();
            if (!stringWithoutNumbers(firstName)) {
                System.out.println("You can't have number in your name");
            }
        } while (!stringWithoutNumbers(firstName));

        String lastName;
        do {
            System.out.println("Insert last name");
            lastName = scanner.next();
            if (!stringWithoutNumbers(lastName)) {
                System.out.println("You can't have number in your name");
            }
        } while (!stringWithoutNumbers(lastName));

        String username;
        do {
            System.out.println("Insert username");
            username = scanner.next();
            if (indexOfUsername(username, type)!=-1) {
                System.out.println("Username already exist");
            }
        } while (indexOfUsername(username, type)!=-1);

        String password;
        do {
            System.out.println("Insert password");
            password = scanner.next();
            if (password.length() < 6)
                System.out.println("Password too short");
        } while (password.length()<6);

        if (type==1) {
            System.out.println("1- vip or 2- not");
            int answer = scanner.nextInt();
            boolean vip = (answer==1);
            Consumer consumer = new Consumer(firstName,lastName,username,password,vip);
            addPerson(consumer);
        }
        else {
            int rank;
            do {
                System.out.println("1- for worker, 2- for manager or 3- for big manager");
                rank = scanner.nextInt();
            } while (rank>3 || rank<1);
            Worker worker = new Worker(firstName,lastName,username,password,rank);
            addPerson(worker);
        }

    }
    private boolean stringWithoutNumbers(String str) {
        for (int i=0; i<str.length(); i++) {
            if (Character.isDigit(str.charAt(i)))
                return false;
        }
        return true;
    }

    private int indexOfUsername(String username, int type) {
        int exist = -1;
        for (int i=0; type==1? i<consumers.length: i< workers.length; i++) {
            if ((type==1)? consumers[i].getUsername().equals(username):workers[i].getUsername().equals(username)) {
                exist = i;
            }
        }
        return exist;
    }

    public void addPerson(Person person) {
        Person[] people;
        if (person.isWorker()) {
            people = new Person[workers.length+1];
        }
        else {
            people = new Person[consumers.length+1];
        }
        for (int i=0; i< people.length-1; i++) {
            if (person.isWorker())
                people[i] = workers[i];
            else {
                people[i] = consumers[i];
            }
        }
        people[people.length-1] = person;
        if (person.isWorker())
            workers = people;
        else {
            consumers = people;
        }
    }
    public Product[] getProducts() {
        return products;
    }
}
