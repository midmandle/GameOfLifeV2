import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(JUnitParamsRunner.class)
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
                new Coordinate(2, 2)
        );
        World world = new World(livingCellLocations);
        World nextWorld = world.tick();

        List<Coordinate> nextLivingLocations = nextWorld.getLivingCellLocations();
        List<Coordinate> expectedNextLivingLocations = asList(new Coordinate(1, 1));
        assertEquals(expectedNextLivingLocations, nextLivingLocations);
    }

    @Test
    public void a_world_with_cells_to_be_born_returns_a_new_world_with_those_cells() {
        List<Coordinate> livingCellLocations = asList(
                new Coordinate(0, 0),
                new Coordinate(1, 0),
                new Coordinate(0, 1)
        );
        World world = new World(livingCellLocations);
        World nextWorld = world.tick();

        List<Coordinate> nextLivingLocations = nextWorld.getLivingCellLocations();
        List<Coordinate> expectedNextLivingLocations = asList(
                new Coordinate(0, 0),
                new Coordinate(1, 0),
                new Coordinate(0, 1),
                new Coordinate(1, 1)
        );
        assertTrue(nextLivingLocations.containsAll(expectedNextLivingLocations));
    }


    @Test
    @Parameters(method="transform1, transform2")
    public void a_world_produces_other_worlds(List<Coordinate> initialLocations, List<Coordinate> expectedNextLocations) {
        World world = new World(initialLocations);
        World nextWorld = world.tick();
        List<Coordinate> nextLivingLocations = nextWorld.getLivingCellLocations();
        assertTrue(nextLivingLocations.containsAll(expectedNextLocations));
    }

    public static Object[] transform1() {
        return new Object[] {
                Arrays.asList(
                        new Coordinate(0,0),
                        new Coordinate(1, 1),
                        new Coordinate(2, 0)
                ),
                Arrays.asList(
                        new Coordinate(1, 0),
                        new Coordinate(1, 1)
                )
        };
    }

    public static Object[] transform2() {
        return new Object[] {
                Arrays.asList(
                        new Coordinate(0,0),
                        new Coordinate(1, 0),
                        new Coordinate(2, 0)
                ),
                Arrays.asList(
                        new Coordinate(1, 0),
                        new Coordinate(1, 1),
                        new Coordinate(1, -1)
                )
        };
    }

    public static Object[] transform3() {
        return new Object[] {
                Arrays.asList(
                        new Coordinate(0,0),
                        new Coordinate(2,0),
                        new Coordinate(1, 1),
                        new Coordinate(0, 2),
                        new Coordinate(2, 2)
                ),
                Arrays.asList(
                        new Coordinate(1, 0),
                        new Coordinate(0, 1),
                        new Coordinate(2, 1),
                        new Coordinate(1, 2)
                )
        };
    }
}
