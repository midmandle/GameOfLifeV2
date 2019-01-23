import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertTrue;

public class CellTests {
    @Test
    public void a_cell_identifies_its_living_neighbors_from_the_population() {
        Cell cell = new Cell(new Coordinate(0,0), CellState.Alive);
        Cell livingNeighbor = new Cell(new Coordinate(0, 1), CellState.Alive);
        Cell livingCellNotNeighbor = new Cell(new Coordinate(2, 2), CellState.Alive);
        List<Coordinate> population = asList(cell.getCoordinate(), livingNeighbor.getCoordinate(), livingCellNotNeighbor.getCoordinate());

        assertTrue(cell.determineNumberOfLivingNeighbours(population) == 1);
    }
}
