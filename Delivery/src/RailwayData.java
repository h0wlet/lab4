

public class RailwayData {
    public final Station departureStation;
    public final Station arrivalStation;
    public final Railways forwardRailways;
    public final Railways backRailways;

    public RailwayData(Configuration config, Station dep, Station arr) {
        forwardRailways = new Railways(config.numberOfWaysFromDepartureToDestination);
        backRailways = new Railways(config.numberOfWaysFromDestinationToDeparture);
        departureStation = dep;
        arrivalStation = arr;
    }
}
