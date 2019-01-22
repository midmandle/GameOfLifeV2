import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class World {
    private List<Cell> livingCells;

    public World(List<Coordinate> livingLocations) {
        this.livingCells = this.initialiseLivingCellsAt(livingLocations);
    }

    private List<Cell> initialiseLivingCellsAt(List<Coordinate> livingCellLocations) {
        List<Cell> result = new ArrayList<Cell>();
        for (Coordinate location :
                livingCellLocations) {
            result.add(new Cell(location));
        }
        return result;
    }

    public World tick() {
        List<Cell> survivors = new ArrayList<Cell>();
        World nextWorld = new World(this.getCellLocations(survivors));
        return nextWorld;
    }

    private List<Coordinate> getCellLocations(List<Cell> survivors) {
        return new ArrayList<Coordinate>();
    }

    public List<Coordinate> getLivingCellLocations() {
        List<Coordinate> result = new ArrayList<Coordinate>();
        for (Cell cell :
                this.livingCells) {
                result.add(cell.getCoordinate());
        }
        return result;
    }
}
