import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
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
}
