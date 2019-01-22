import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

public class Coordinate {

    private final int yCoordinate;
    private final int xCoordinate;

    public Coordinate(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return yCoordinate == that.yCoordinate &&
                xCoordinate == that.xCoordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(yCoordinate, xCoordinate);
    }

    public List<Coordinate> identifySurroundingArea() {
        return asList(
                new Coordinate(this.xCoordinate - 1, this.yCoordinate - 1),
                new Coordinate(this.xCoordinate, this.yCoordinate - 1),
                new Coordinate(this.xCoordinate + 1, this.yCoordinate - 1),
                new Coordinate(this.xCoordinate - 1, this.yCoordinate),
                new Coordinate(this.xCoordinate + 1, this.yCoordinate),
                new Coordinate(this.xCoordinate - 1, this.yCoordinate + 1),
                new Coordinate(this.xCoordinate, this.yCoordinate + 1),
                new Coordinate(this.xCoordinate + 1, this.yCoordinate + 1)
        );
    }
}
