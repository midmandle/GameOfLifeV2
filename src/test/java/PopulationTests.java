import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class PopulationTests {
    @Test
    public void a_population_identifies_survivors() {
        List<Coordinate> livingCellLocations = asList(
                new Coordinate(0, 0),
                new Coordinate(0, 1),
                new Coordinate(1, 0),
                new Coordinate(1, 1)
        );
        Population population = new Population(livingCellLocations);
        List<Coordinate> survivors = population.identifySurvivors();
        assertTrue(survivors.containsAll(livingCellLocations));
    }

    @Test
    public void a_population_identifies_cells_to_die() {
        List<Coordinate> livingCellLocations = asList(
                new Coordinate(0, 0),
                new Coordinate(1, 1),
                new Coordinate(2, 2)
        );
        Population population = new Population(livingCellLocations);
        List<Coordinate> cellsToDie = population.identifyDieingCells();
        List<Coordinate> expectedCells = asList(new Coordinate(0, 0), new Coordinate(2, 2));
        assertEquals(expectedCells, cellsToDie);
    }

    @Test
    public void a_population_identifies_cells_to_be_born() {
        List<Coordinate> livingCellLocations = asList(
                new Coordinate(0, 0),
                new Coordinate(1, 1),
                new Coordinate(2, 0)
        );
        Population population = new Population(livingCellLocations);
        List<Coordinate> cellsToBeBorn = population.identifyBirths();
        List<Coordinate> expectedCells = asList(new Coordinate(1, 0));
        assertEquals(expectedCells, cellsToBeBorn);
    }
}
