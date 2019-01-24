import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Population {
    private final List<Cell> livingCells;
    private final List<Cell> deadCells;
    private final Map<Coordinate, Cell> currentCellSpace;

    public Population(List<Coordinate> livingCellLocations) {
        this.livingCells = this.generateLivingCellsForLocations(livingCellLocations);
        this.deadCells = this.generateDeadCells();
        this.currentCellSpace = generateCellSpace();
    }

    private List<Cell> generateLivingCellsForLocations(List<Coordinate> livingCellLocations) {
        return this.generateCells(livingCellLocations, CellState.Alive);
    }

    private List<Cell> generateCells(List<Coordinate> cellLocations, CellState state) {
        List<Cell> result = new ArrayList<Cell>();
        for (Coordinate location:
             cellLocations) {
           result.add(new Cell(location, state));
        }
        return result;
    }

    private List<Cell> generateDeadCells() {
        List<Coordinate> deadCellLocations = this.identifyDeadLocations();
        return this.generateCells(deadCellLocations, CellState.Dead);
    }

    private Map<Coordinate, Cell> generateCellSpace() {
        Map<Coordinate, Cell> result = new HashMap<Coordinate, Cell>();
        addCellsToSpace(result, this.livingCells);
        addCellsToSpace(result, this.deadCells);
        return result;
    }

    private void addCellsToSpace(Map<Coordinate, Cell> space, List<Cell> cells) {
        for (Cell livingCell :
                cells) {
            space.put(livingCell.getCoordinate(), livingCell);
        }
    }

    private List<Coordinate> identifyDeadLocations() {
        List<Coordinate> result = new ArrayList<Coordinate>();
        for (Cell livingCell :
                this.livingCells) {
            result = addSurroundingDeadLocations(result, livingCell);
        }
        return result;
    }

    private List<Coordinate> addSurroundingDeadLocations(List<Coordinate> result, Cell livingCell) {
        List<Coordinate> surroundingLocation = livingCell.generateNeighborhood();
        surroundingLocation = removeLivingCellLocations(surroundingLocation);
        result = addDistinctLocations(result, surroundingLocation);
        return result;
    }

    private List<Coordinate> removeLivingCellLocations(List<Coordinate> surroundingLocations) {
        List<Coordinate> result = new ArrayList<Coordinate>();
        result.addAll(surroundingLocations);
        for (Cell livingCell :
                this.livingCells) {
            result = removeIfExists(result, livingCell);
        }
        return result;
    }

    private List<Coordinate> removeIfExists(List<Coordinate> result, Cell livingCell) {
        int indexOfCoordinate = result.indexOf(livingCell.getCoordinate());
        if(indexOfCoordinate >= 0)
            result.remove(indexOfCoordinate);
        return result;
    }

    private List<Coordinate> addDistinctLocations(List<Coordinate> result, List<Coordinate> surroundingLocations) {
        for (Coordinate location :
                surroundingLocations) {
            if (!result.contains(location))
                result.add(location);
        }
        return result;
    }

    private List<Coordinate> getCellLocations(List<Cell> cells) {
        List<Coordinate> result = new ArrayList<Coordinate>();
        for (Cell cell :
                cells) {
            result.add(cell.getCoordinate());
        }
        return result;
    }

    List<Coordinate> getLivingCellCoordinates() {
        return this.getCellLocations(this.livingCells);
    }

    public List<Coordinate> identifyDieingCells() {
        final List<Coordinate> dieingLocations = new ArrayList<Coordinate>();
        this.currentCellSpace.forEach(((coordinate, cell) -> {
            if(cell.isDieing(this))
                dieingLocations.add(coordinate);
        }));
        return dieingLocations;
    }

    public List<Coordinate> identifyBirths() {
        final List<Coordinate> birthLocations = new ArrayList<Coordinate>();
        this.currentCellSpace.forEach(((coordinate, cell) -> {
            if(cell.isBirth(this))
                birthLocations.add(coordinate);
        }));
        return birthLocations;
    }

    public List<Coordinate> identifySurvivors() {
        final List<Coordinate> survivorLocations = new ArrayList<Coordinate>();
        this.currentCellSpace.forEach((coordinate, cell) -> {
            if(cell.isSurvivor(this))
                survivorLocations.add(coordinate);
        });
        return survivorLocations;
    }
}

