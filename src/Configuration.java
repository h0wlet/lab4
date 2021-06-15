import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Configuration {

    //станция
    public final Integer distance;
    public final Integer numberOfWaysAtDepartureStation;
    public final Integer numberOfWaysAtArrivalStation;
    public final Integer numberOfWaysFromDepartureToDestination;
    public final Integer numberOfWaysFromDestinationToDeparture;

    //товар
    public final ArrayList<String> products = new ArrayList<>();
    public final HashMap<String, Integer> sizeOfWarehouseAtDepartureStation = new HashMap<>();
    public final HashMap<String, Integer> sizeOfWarehouseAtDestinationStation = new HashMap<>();
    public final HashMap<String, Integer> productionTime = new HashMap<>();
    public final HashMap<String, Integer> consumptionTime = new HashMap<>();
    public final HashMap<String, Integer> loadingTime = new HashMap<>();
    public final HashMap<String, Integer> unloadingTime = new HashMap<>();

    //поезд
    public final ArrayList<String> trains = new ArrayList<>();
    public final HashMap<String, Integer> trainCapacity = new HashMap<>();
    public final HashMap<String, Integer> trainSpeed = new HashMap<>();
    public final HashMap<String, Integer> trainAssemblyTime = new HashMap<>();
    public final HashMap<String, Integer> trainAmortizationTime = new HashMap<>();
    public final HashMap<String, String> trainProductName = new HashMap<>();


    public Configuration(String file) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(file));

        //Станция
        distance = Integer.parseInt(properties.getProperty("distance"));
        numberOfWaysAtDepartureStation = Integer.parseInt(properties.getProperty("numberOfWaysAtDepartureStation"));
        numberOfWaysAtArrivalStation = Integer.parseInt(properties.getProperty("numberOfWaysAtArrivalStation"));
        numberOfWaysFromDepartureToDestination = Integer.parseInt(properties.getProperty("numberOfWaysFromDepartureToDestination"));
        numberOfWaysFromDestinationToDeparture = Integer.parseInt(properties.getProperty("numberOfWaysFromDestinationToDeparture"));


        //товар
        String[] productNames = properties.getProperty("products").split(";");
        products.addAll(Arrays.asList(productNames));
        for (var prName : products) {
            sizeOfWarehouseAtDepartureStation.put(prName, Integer.parseInt(properties.getProperty(prName + ".sizeOfWarehouseAtDepartureStation")));
            sizeOfWarehouseAtDestinationStation.put(prName, Integer.parseInt(properties.getProperty(prName + ".sizeOfWarehouseAtDestinationStation")));
            productionTime.put(prName, Integer.parseInt(properties.getProperty(prName + ".productionTime")));
            consumptionTime.put(prName, Integer.parseInt(properties.getProperty(prName + ".consumptionTime")));
            loadingTime.put(prName, Integer.parseInt(properties.getProperty(prName + ".loadingTime")));
            unloadingTime.put(prName, Integer.parseInt(properties.getProperty(prName + ".unloadingTime")));
        }

        //поезд
        String[] trainNames = properties.getProperty("trains").split(";");
        trains.addAll(Arrays.asList(trainNames));
        for (var trName : trains) {
            trainCapacity.put(trName, Integer.parseInt(properties.getProperty(trName + ".trainCapacity")));
            trainSpeed.put(trName, Integer.parseInt(properties.getProperty(trName + ".trainSpeed")));
            trainAssemblyTime.put(trName, Integer.parseInt(properties.getProperty(trName + ".trainAssemblyTime")));
            trainAmortizationTime.put(trName, Integer.parseInt(properties.getProperty(trName + ".trainAmortizationTime")));
            trainProductName.put(trName, properties.getProperty(trName + ".product"));
        }
    }

    public int getTrainsNumber(){
        return trains.size();
    }

}
