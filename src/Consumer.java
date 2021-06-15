

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
                Log.logInfo("Consumer consume product");
            } catch (InterruptedException e) {
                Log.logError("Consumer stopped");
                e.printStackTrace();
            }
        }
    }
}
