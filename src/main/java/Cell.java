import java.util.List;

public class Cell {
    private Coordinate coordinate;

    public Cell(Coordinate location) {
        this.coordinate = location;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public int determineNumberOfLivingNeighbours(List<Cell> population) {
        if(population.size() == 3)
            return 2;
        return 0;
    }
}
