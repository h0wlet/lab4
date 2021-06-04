import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TransportCompany {
    private final ArrayList<Factory> factories;
    private final ArrayList<Consumer> consumers;
    private final Depot depot;
    private final Configuration config;

    public TransportCompany(String configFile) throws IOException {
        config = new Configuration(configFile);
        factories = new ArrayList<>();
        HashMap<String, Storage> factoryStorages = new HashMap<>();
        HashMap<String, Storage> consumerStorages = new HashMap<>();
        consumers = new ArrayList<>();

        for (var productName : config.products) {
            if (!factoryStorages.containsKey(productName)) {
                factoryStorages.put(productName, new Storage(config.sizeOfWarehouseAtDepartureStation.get(productName)));
                consumerStorages.put(productName, new Storage(config.sizeOfWarehouseAtDestinationStation.get(productName)));
            }

            factories.add(new Factory(factoryStorages.get(productName), productName, config.productionTime.get(productName)));
            consumers.add(new Consumer(consumerStorages.get(productName), config.consumptionTime.get(productName)));
        }

        var depStation = new Station(config.numberOfWaysAtDepartureStation, factoryStorages);
        var arrStation = new Station(config.numberOfWaysAtArrivalStation, consumerStorages);
        var railwayData = new RailwayData(config, depStation, arrStation);

        depot = new Depot(config, railwayData);
    }

    public void start() {
        for (var fact : factories) {
            fact.start();
        }
        depot.launch();
        for (var cons : consumers) {
            cons.start();
        }
        System.out.println("Program started, press Enter to finish it");
    }

    public void stop() {
        for (var fact : factories) {
            fact.interrupt();
        }
        depot.stop();
        for (var cons : consumers) {
            cons.interrupt();
        }
    }
}
