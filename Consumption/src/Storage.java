import java.util.ArrayList;

public class Storage {
    private final int capacity;
    private final ArrayList<Product> products;

    public Storage(int capacity) {
        this.capacity = capacity;
        products = new ArrayList<>(capacity);
    }

    public synchronized Product getProduct() throws InterruptedException {
        while (products.isEmpty()) {
            wait();
        }
        Product prd = products.remove(0);
        notifyAll();
        return prd;
    }

    public synchronized void setProduct(Product prd) throws InterruptedException {
        while (products.size() >= capacity) {
            wait();
        }
        products.add(prd);
        notifyAll();
    }
}
