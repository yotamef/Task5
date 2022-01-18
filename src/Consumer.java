import java.util.Date;

public class Consumer extends Person{
    private boolean vip;
    private Cart cart;
    private double sumPurchases;
    private boolean boughtSomething;
    private int timesOfShop;
    private Date lastShop;

    public Consumer(String firstName, String lastName, String username, String password) {
        super(firstName, lastName, username, password);
        this.vip = false;
        sumPurchases = 0;
        boughtSomething = false;
        cart = new Cart();
        timesOfShop = 0;
        lastShop = null;
    }

    public Consumer(String firstName, String lastName, String username, String password, boolean vip) {
        super(firstName, lastName, username, password);
        this.vip = vip;
        sumPurchases = 0;
        boughtSomething = false;
        cart = new Cart();
        timesOfShop = 0;
        lastShop = null;
    }

    public void addToCart(Product product) {
        cart.addToCart(product);
        boughtSomething = true;
    }

    public Cart getCart() {
        return cart;
    }

    public String introduce() {
        String vip = "";
        if (this.vip) {
            vip = "(vip)!";
        }
        return super.introduce()+ (vip);
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setBoughtSomething(boolean boughtSomething) {
        this.boughtSomething = boughtSomething;
    }

    public int getTimesOfShop() {
        return timesOfShop;
    }

    public void setTimesOfShop(int timesOfShop) {
        this.timesOfShop = timesOfShop;
    }

    public boolean isVip() {
        return vip;
    }

    public boolean isBoughtSomething() {
        return boughtSomething;
    }

    public double getSumPurchases() {
        return sumPurchases;
    }

    public void setSumPurchases(double sumPurchases) {
        this.sumPurchases = this.sumPurchases + sumPurchases;
    }

    public Date getLastShop() {
        return lastShop;
    }

    public String toString() {
        String toReturn = "";
        toReturn = getFirstName() +" "+getLastName()+"\n";
        if (vip)
            toReturn = toReturn + "vip"+"\n";
        toReturn = toReturn+ "times of shop: "+timesOfShop+"\n";
        toReturn = toReturn + "sum of prices: "+sumPurchases+"\n";
        toReturn = toReturn + "last shop: "+lastShop;
        return toReturn;
    }

    public void setLastShop(Date lastShop) {
        this.lastShop = lastShop;
    }

    @Override
    public boolean isWorker() {
        return false;
    }
}
