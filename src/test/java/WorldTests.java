import org.junit.Test;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;

public class WorldTests {
    @Test
    public void a_world_identifies_the_current_living_cells() {
        World world = new World(asList(new Coordinate(0,0)));
        assertEquals(asList(new Coordinate(0,0)), world.getLivingCellLocations());
    }
}
