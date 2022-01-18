public class Product {
    private String name;
    private int price;
    private boolean inStock;
    private double vipPercents;

    public Product(String name, int price, boolean inStock, double vipPercents) {
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.vipPercents = vipPercents;
    }

    public Product[] addProductToArray(Product[] products) {
        Product[] products1 = new Product[products.length+1];
        for (int i=0; i<products.length; i++) {
            products1[i] = products[i];
        }
        Product toAdd;
        toAdd = new Product(this.name,this.price,this.inStock,this.vipPercents);
        products1[products.length] = toAdd;
        products = products1;

        return products;
    }
    public int getPrice() {
        return price;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
    public boolean isInStock() {
        return inStock;
    }
    public String getName() {
        return name;
    }

    public static final int HUNDRED_PERCENTS = 100;
    public static final int DISCOUNT_PERCENTS_FOR_WORKERS = 10;

    public double priceAfterDiscount(Consumer consumer) {
        double priceAfterDiscount = 0;
        double discountPercents = 0;
        if (consumer.isWorker()) {
            discountPercents = ((Worker) consumer).getRank()*DISCOUNT_PERCENTS_FOR_WORKERS;
        }
        if (consumer.isVip()) {
            priceAfterDiscount = (price-price*(vipPercents/HUNDRED_PERCENTS));
        }
        else {
            priceAfterDiscount = price - price * (discountPercents / HUNDRED_PERCENTS);
        }
        return priceAfterDiscount;
    }

    public String toString() {
        return "name: "+name+" price: "+price+ (inStock?" in stock":" not in stock");
    }

    public boolean isCartProduct() {
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getVipPercents() {
        return vipPercents;
    }

    public void setVipPercents(double vipPercents) {
        this.vipPercents = vipPercents;
    }
}
