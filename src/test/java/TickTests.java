import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TickTests {
    @Test
    public void a_world_with_no_living_cells_returns_a_world_without_living_cells() {
        World world = new World();
        List<Coordinate> livingCellLocations = new ArrayList<Coordinate>();
        world.setLivingCells(livingCellLocations);

        World nextWorld = world.tick();

        List<Coordinate> nextLivingCellLocations = nextWorld.getLivingCellLocations();
        assertTrue(nextLivingCellLocations.isEmpty());
    }

    @Test
    public void a_world_with_one_living_cell_returns_a_world_with_no_living_cells() {
        World world = new World();
        List<Coordinate> livingCellLocations = asList(new Coordinate(0,0));
        world.setLivingCells(livingCellLocations);

        World nextWorld = world.tick();

        List<Coordinate> nextLivingCellLocations = nextWorld.getLivingCellLocations();
        assertTrue(nextLivingCellLocations.isEmpty());
    }
}
