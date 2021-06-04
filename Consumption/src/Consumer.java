

public class Consumer extends Thread{
    private final Storage storage;
    private final int consumptionTime;

    public Consumer(Storage storage, int consumptionTime) {
        this.storage = storage;
        this.consumptionTime = consumptionTime;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(consumptionTime);
                Product prd = storage.getProduct();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
