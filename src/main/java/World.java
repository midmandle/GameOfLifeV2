import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class World {
    private final Population population;

    public World(List<Coordinate> livingLocations) {
        this.population = new Population(livingLocations);
    }

    public World tick() {
        List<Coordinate> survivorLocations = this.population.identifySurvivors();
        List<Coordinate> birthLocations = this.population.identifyBirths();
        List<Coordinate> nextLivingLocations = new ArrayList<Coordinate>();
        nextLivingLocations.addAll(survivorLocations);
        nextLivingLocations.addAll(birthLocations);
        World nextWorld = new World(nextLivingLocations);
        return nextWorld;
    }

    public List<Coordinate> getLivingCellLocations() {
        return this.population.getLivingCellCoordinates();
    }
}
