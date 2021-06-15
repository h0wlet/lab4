import java.util.ArrayList;
import java.util.HashMap;

public class Station {
    private final int capacity;
    private final ArrayList<Train> trains;
    private final HashMap<String, Storage> storages;

    public Station(int capacity, HashMap<String, Storage> storages) {
        this.capacity = capacity;
        this.storages = storages;
        trains = new ArrayList<>(capacity);
    }

    public synchronized void startLoad(Train train) throws InterruptedException {
        while (trains.size() >= capacity) {
            wait();
        }
        trains.add(train);
    }

    public synchronized void endLoad(Train train) throws Exception {
        if (!trains.remove(train)) {
            throw new Exception("Station is empty");
        }
        notifyAll();
    }

    public Storage getStorage(String name) {
        return storages.get(name);
    }
}
