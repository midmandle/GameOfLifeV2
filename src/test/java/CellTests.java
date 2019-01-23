import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertTrue;

public class CellTests {
    @Test
    public void a_cell_can_identify_if_it_will_survive() {
        Cell cell = new Cell(new Coordinate(0,  0), CellState.Alive);
        Cell cell1 = new Cell(new Coordinate(0, 1), CellState.Alive);
        Cell cell2 = new Cell(new Coordinate(1, 0), CellState.Alive);
        Population population = new Population(asList(cell.getCoordinate(), cell1.getCoordinate(), cell2.getCoordinate()));
        assertTrue(cell.isSurvivor(population));
    }

    @Test
    public void a_cell_can_identify_if_it_will_die() {
        Cell cell = new Cell(new Coordinate(0,  0), CellState.Alive);
        Cell cell1 = new Cell(new Coordinate(0, 1), CellState.Alive);
        Cell cell2 = new Cell(new Coordinate(0, 2), CellState.Alive);
        Population population = new Population(asList(cell.getCoordinate(), cell1.getCoordinate(), cell2.getCoordinate()));
        assertTrue(cell.isDieing(population));
    }

    @Test
    public void a_cell_can_identify_if_it_will_be_born() {
        Cell cell = new Cell(new Coordinate(0,  0), CellState.Alive);
        Cell cell1 = new Cell(new Coordinate(0, 1), CellState.Alive);
        Cell cell2 = new Cell(new Coordinate(0, 2), CellState.Alive);
        Cell cell3 = new Cell(new Coordinate(1, 1), CellState.Dead);
        Population population = new Population(asList(cell.getCoordinate(), cell1.getCoordinate(), cell2.getCoordinate()));

        assertTrue(cell3.isBirth(population));
    }

    @Test
    public void a_cell_identifies_its_living_neighbors_from_the_population() {
        Cell cell = new Cell(new Coordinate(0,0), CellState.Alive);
        Cell livingNeighbor = new Cell(new Coordinate(0, 1), CellState.Alive);
        Cell livingCellNotNeighbor = new Cell(new Coordinate(2, 2), CellState.Alive);
        List<Coordinate> population = asList(cell.getCoordinate(), livingNeighbor.getCoordinate(), livingCellNotNeighbor.getCoordinate());

        assertTrue(cell.determineNumberOfLivingNeighbours(population) == 1);
    }
}
