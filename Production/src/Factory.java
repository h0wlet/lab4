

public class Factory extends Thread{
    private final Storage storage;
    private final String name;
    private final int productionTime;

    public Factory(Storage storage, String name, int productionTime) {
        this.storage = storage;
        this.name = name;
        this.productionTime = productionTime;
    }

    @Override
    public void run() {
        Product prd;
        while (!isInterrupted()) {
            try {
                Thread.sleep(productionTime);
                prd = new Product(name);
                storage.setProduct(prd);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
