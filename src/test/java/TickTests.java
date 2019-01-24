import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TickTests {
    @Test
    public void a_world_with_no_living_cells_returns_a_world_without_living_cells() {
        List<Coordinate> livingCellLocations = new ArrayList<Coordinate>();
        World world = new World(livingCellLocations);

        World nextWorld = world.tick();

        List<Coordinate> nextLivingCellLocations = nextWorld.getLivingCellLocations();
        assertTrue(nextLivingCellLocations.isEmpty());
    }

    @Test
    public void a_world_with_one_living_cell_returns_a_world_with_no_living_cells() {
        List<Coordinate> livingCellLocations = asList(
                new Coordinate(0,0)
        );
        World world = new World(livingCellLocations);

        World nextWorld = world.tick();

        List<Coordinate> nextLivingCellLocations = nextWorld.getLivingCellLocations();
        assertTrue(nextLivingCellLocations.isEmpty());
    }

    @Test
    public void a_world_with_surviving_cells_returns_a_world_with_surviving_cells() {
        List<Coordinate> livingCellLocations = asList(
                new Coordinate(0, 0),
                new Coordinate(0, 1),
                new Coordinate(1, 0)
        );
        World world = new World(livingCellLocations);

        World nextWorld = world.tick();

        List<Coordinate> nextLivingCellLocations = nextWorld.getLivingCellLocations();
        assertTrue(nextLivingCellLocations.containsAll(livingCellLocations));
    }

    @Test
    public void a_world_with_dieing_cells_returns_a_world_without_those_cells() {
        List<Coordinate> livingCellLocations = asList(
                new Coordinate(0, 0),
                new Coordinate(1, 1),
                new Coordinate(2, 0)
        );
        World world = new World(livingCellLocations);
        World nextWorld = world.tick();

        List<Coordinate> nextLivingLocations = nextWorld.getLivingCellLocations();
        List<Coordinate> expectedNextLivingLocations = asList(new Coordinate(1, 1));
        assertEquals(expectedNextLivingLocations, nextLivingLocations);
    }
}
