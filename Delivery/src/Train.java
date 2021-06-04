import java.util.ArrayList;
import java.util.Calendar;

public class Train extends Thread{
    private final String name;
    private final ArrayList<Product> products;
    private final Depot depot;
    private final Configuration config;
    private final RailwayData railwayData;

    public Train(String name, Depot depot, Configuration config, RailwayData railwayData) {
        this.name = name;
        this.railwayData = railwayData;
        products = new ArrayList<>();
        this.depot = depot;
        this.config = config;
    }

    private void load() throws Exception{
        railwayData.departureStation.startLoad(this);
        var str = railwayData.departureStation.getStorage(config.trainProductName.get(name));
        var capacity = config.trainCapacity.get(name);

        while (products.size() <= capacity) {
            Thread.sleep(config.loadingTime.get(config.trainProductName.get(name)));
            products.add(str.getProduct());
        }

        railwayData.departureStation.endLoad(this);
    }

    private void unload(Storage str) throws Exception {
        while (!products.isEmpty()) {
            Thread.sleep(config.unloadingTime.get(products.get(0).getName()));
            str.setProduct(products.remove(0));
        }
        railwayData.arrivalStation.endLoad(this);
    }

    @Override
    public void run() {
        long startTime = Calendar.getInstance().getTimeInMillis() / 1000;
        var amortizationTime = config.trainAmortizationTime.get(name);
        while ((Calendar.getInstance().getTimeInMillis() / 1000 - startTime) < amortizationTime && !isInterrupted()) {
            try {
                load();
                railwayData.forwardRailways.getRailway();
                int distancePassed = 0;
                int offset = config.trainSpeed.get(name);
                while (distancePassed < config.distance) {
                    distancePassed += offset;
                    Thread.sleep(100);
                }

                railwayData.arrivalStation.startLoad(this);
                railwayData.forwardRailways.freeRailway();

                Storage strA = railwayData.arrivalStation.getStorage(products.get(0).getName());
                unload(strA);
                railwayData.backRailways.freeRailway();

                distancePassed = 0;
                while (distancePassed < config.distance) {
                    distancePassed += offset;
                    Thread.sleep(100);
                }
                railwayData.backRailways.freeRailway();
            } catch (Exception e) {
                return;
            }
        }
        depot.stop();
    }
}
