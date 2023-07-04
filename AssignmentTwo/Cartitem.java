//Haris Sujethan 501088408
public class Cartitem {
    private Product product;
    private String productOptions;

    public Cartitem(Product product, String productOptions) {
        this.product = product;
        this.productOptions = productOptions;
    }

    public Product getProduct() {
        return product;
    }

    public void setProd(Product prod) {
        this.product = product;
    }

    public String getProductOptions() {
        return productOptions;
    }

    public void setProductOptions(String productOptions) {
        this.productOptions = productOptions;
    }
}
