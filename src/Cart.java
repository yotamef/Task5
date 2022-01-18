public class Cart{
    private Product[] products;

    public Cart() {
        products = new Product[0];
    }


//    public double getPrice(Consumer consumer) {
//        double price = 0;
//        if (this.products!=null) {
//            for (int i=0; i<this.products.length; i++) {
//                price = price + products[i].priceAfterDiscount(consumer);
//            }
//        }
//        return price;
//    }


    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public void addToCart(Product product) {
        products = product.addProductToArray(products);
    }

    public void cleanCart() {
        products = new Product[0];
    }


}
