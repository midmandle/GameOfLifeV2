import java.util.ArrayList;
import java.util.List;

public class World {
    private final Population population;

    public World(List<Coordinate> livingLocations) {
        this.population = new Population(livingLocations);
    }

    public World tick() {
        List<Coordinate> survivorLocations = this.population.identifySurvivors();
        World nextWorld = new World(survivorLocations);
        return nextWorld;
    }

    public List<Coordinate> getLivingCellLocations() {
        return this.population.getLivingCellCoordinates();
    }
}
