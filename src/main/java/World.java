import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class World {
    public void setLivingCells(List<Coordinate> livingCellLocations) {

    }

    public World tick() {
        return new World();
    }

    public List<Coordinate> getLivingCellLocations() {
        return new ArrayList<Coordinate>();
    }
}
