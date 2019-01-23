import java.util.ArrayList;
import java.util.List;

public class Cell {
    private Coordinate coordinate;
    private CellState cellState;

    public Cell(Coordinate location, CellState cellState) {
        this.coordinate = location;
        this.cellState = cellState;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public int determineNumberOfLivingNeighbours(List<Coordinate> population) {
        List<Coordinate> neighborhood = this.generateNeighborhood();
        List<Coordinate> livingNeighbors = this.identifyLivingNeighbors(neighborhood, population);
        return livingNeighbors.size();
    }

    private List<Coordinate> identifyLivingNeighbors(List<Coordinate> neighborhood, List<Coordinate> population) {
        List<Coordinate> result = new ArrayList<Coordinate>();
        for (Coordinate neighbor :
                neighborhood) {
              if(population.contains(neighbor))
                  result.add(neighbor);
        }
        return result;
    }

    private List<Coordinate> generateNeighborhood() {
        return this.coordinate.identifySurroundingArea();
    }

    boolean isDieing(Population population) {
        if(this.cellState == CellState.Dead)
            return false;
        List<Coordinate> livingCellLocations = population.getLivingCellCoordinates();
        int numberOfNeighbors = determineNumberOfLivingNeighbours(livingCellLocations);
        return numberOfNeighbors < 2 || numberOfNeighbors >= 4;
    }

    boolean isBirth(Population population) {
        if(this.cellState == CellState.Alive)
            return false;
        List<Coordinate> livingCellLocations = population.getLivingCellCoordinates();
        int numberOfNeighbors = determineNumberOfLivingNeighbours(livingCellLocations);
        return numberOfNeighbors == 3;
    }

    boolean isSurvivor(Population population) {
        if(this.cellState == CellState.Dead)
            return false;
        List<Coordinate> livingCellLocations = population.getLivingCellCoordinates();
        int numberOfNeighbors = determineNumberOfLivingNeighbours(livingCellLocations);
        return (numberOfNeighbors >= 2) && (numberOfNeighbors <= 3);
    }
}
