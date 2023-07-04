
//Haris Sujethan 501088408
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Cart {
    private String id;
    private ArrayList<Cartitem> cartitems = new ArrayList<>();

    public Cart(String id) {
        this.id = id;
    }

    public Cart(String id, ArrayList<Cartitem> cartitems) {
        this.id = id;
        this.cartitems = cartitems;
    }

    public void addProduct(Cartitem item) {
        this.cartitems.add(item);
    }

    public void CountProduct(Cartitem item) {
        this.cartitems.add(item);

    }

    public void remProduct(Cartitem item) {

        int i;
        for (i = 0; i < cartitems.size(); i++) {
            if (this.cartitems.get(i).getProduct().getId().equals(item.getProduct().getId())) {
                this.cartitems.remove(i);
            } else {
                System.out.println("Error"); // unlikely to run, just for testing
            }
        }

    }

    public String getid() {
        return id;
    }

    public ArrayList<Cartitem> getCartitems() {
        return cartitems;
    }

    public void setCartitems(ArrayList<Cartitem> cartitems) {
        this.cartitems = cartitems;
    }
}