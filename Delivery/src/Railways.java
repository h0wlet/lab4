

public class Railways {
    private final int capacity;
    private int busyCount;

    public Railways(int capacity) {
        this.capacity = capacity;
        busyCount = 0;
    }

    public synchronized void getRailway() throws InterruptedException {
        while (busyCount >= capacity) {
            wait();
        }
        busyCount++;
    }

    public synchronized void freeRailway() throws Exception {
        if (busyCount <= 0) {
            throw new Exception("The railway is empty");
        }
        busyCount--;
        notifyAll();
    }
}
