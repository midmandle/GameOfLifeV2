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
        List<Cell> survivors = this.identifySurvivors();
        World nextWorld = new World(this.getCellLocations(survivors));
        return nextWorld;
    }

    private List<Cell> identifySurvivors() {
        List<Cell> result = new ArrayList<Cell>();
        for (Cell cell :
                this.livingCells) {
            System.out.println(cell.determineNumberOfLivingNeighbours(this.getLivingCellLocations()));
            if (cell.determineNumberOfLivingNeighbours(this.getLivingCellLocations()) == 2)
                result.add(cell);
        }
        return result;
    }

    private List<Coordinate> getCellLocations(List<Cell> cells) {
        List<Coordinate> result = new ArrayList<Coordinate>();
        for (Cell cell :
                cells) {
            result.add(cell.getCoordinate());
        }
        return result;
    }

    public List<Coordinate> getLivingCellLocations() {
        return this.getCellLocations(this.livingCells);
    }
}
