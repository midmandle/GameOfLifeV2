import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class World {
    private List<Coordinate> livingCellLocations;

    public void setLivingCells(List<Coordinate> livingCellLocations) {
        this.livingCellLocations = livingCellLocations;
    }

    public World tick() {
        World nextWorld = new World();
        nextWorld.setLivingCells(this.livingCellLocations);
        return nextWorld;
    }

    public List<Coordinate> getLivingCellLocations() {
        return this.livingCellLocations;
    }
}
