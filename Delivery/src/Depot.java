import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Depot {
    private final Configuration config;
    private ScheduledExecutorService scheduler;
    private final ArrayList<Train> trains;
    private final RailwayData railwayData;

    public Depot(Configuration config, RailwayData railwayData) {
        this.config = config;
        this.railwayData = railwayData;
        trains = new ArrayList<>();
        scheduler = Executors.newScheduledThreadPool(config.getTrainsNumber());
    }

    public void launch() {
        for (var trainName : config.trains) {
            launchNewTrain(trainName);
        }
    }

    public void launchNewTrain(String name) {
        int assemblyTime = config.trainAssemblyTime.get(name);
        Depot depot = this;
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                Train train = new Train(name, depot, config, railwayData);
                trains.add(train);
                train.start();
            }
        }, assemblyTime, TimeUnit.SECONDS);
    }

    public void stop() {
        scheduler.shutdown();
        for (var train : trains) {
            train.interrupt();
        }
    }
}
