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
        for (Cell livingCell :
                this.livingCells) {
            result.put(livingCell.getCoordinate(), livingCell);
        }

        for (Cell deadCell :
                this.deadCells) {
            result.put(deadCell.getCoordinate(), deadCell);
        }

        return result;
    }

    private List<Coordinate> identifyDeadLocations() {
        List<Coordinate> result = new ArrayList<Coordinate>();
        for (Cell livingCell :
                this.livingCells) {
            List<Coordinate> surroundingLocation = livingCell.getCoordinate().identifySurroundingArea();
            surroundingLocation = removeLivingCellLocations(surroundingLocation);
            result = addDistinctLocations(result, surroundingLocation);
        }
        return result;
    }

    private List<Coordinate> removeLivingCellLocations(List<Coordinate> surroundingLocations) {
        List<Coordinate> result = new ArrayList<Coordinate>();
        result.addAll(surroundingLocations);
        for (Cell livingCell :
                this.livingCells) {
            int indexOfCoordinate = result.indexOf(livingCell.getCoordinate());
            if(indexOfCoordinate >= 0)
                result.remove(indexOfCoordinate);
        }
        return result;
    }

    private List<Coordinate> addDistinctLocations(List<Coordinate> result, List<Coordinate> surroundingLocations) {
        for (Coordinate location :
                surroundingLocations) {
            if (result.contains(location))
                continue;
            result.add(location);
        }
        return result;
    }

    public List<Coordinate> identifySurvivors() {
        final List<Coordinate> survivorLocations = new ArrayList<Coordinate>();
        this.currentCellSpace.forEach((coordinate, cell) -> {
           if(isSurvivor(cell))
               survivorLocations.add(coordinate);
        });
        return survivorLocations;
    }

    private boolean isSurvivor(Cell cell) {
        if(cell.getCurrentState() == CellState.Dead)
            return false;
        List<Coordinate> livingCellLocations = this.getCellLocations(this.livingCells);
        int numberOfNeighbors = cell.determineNumberOfLivingNeighbours(livingCellLocations);
        return (numberOfNeighbors >= 2) && (numberOfNeighbors <= 3);
    }


    private List<Coordinate> getCellLocations(List<Cell> cells) {
        List<Coordinate> result = new ArrayList<Coordinate>();
        for (Cell cell :
                cells) {
            result.add(cell.getCoordinate());
        }
        return result;
    }

    public List<Coordinate> identifyDieingCells() {
        final List<Coordinate> dieingLocations = new ArrayList<Coordinate>();
        this.currentCellSpace.forEach(((coordinate, cell) -> {
            if(isDieing(cell))
                dieingLocations.add(coordinate);
        }));
        return dieingLocations;
    }

    private boolean isDieing(Cell cell) {
        if(cell.getCurrentState() == CellState.Dead)
            return false;
        List<Coordinate> livingCellLocations = this.getCellLocations(this.livingCells);
        int numberOfNeighbors = cell.determineNumberOfLivingNeighbours(livingCellLocations);
        return numberOfNeighbors < 2 || numberOfNeighbors >= 4;
    }

    public List<Coordinate> identifyBirths() {
        final List<Coordinate> birthLocations = new ArrayList<Coordinate>();
        this.currentCellSpace.forEach(((coordinate, cell) -> {
            if(isBirth(cell))
                birthLocations.add(coordinate);
        }));
        return birthLocations;
    }

    private boolean isBirth(Cell cell) {
        if(cell.getCurrentState() == CellState.Alive)
            return false;
        List<Coordinate> livingCellLocations = this.getCellLocations(this.livingCells);
        int numberOfNeighbors = cell.determineNumberOfLivingNeighbours(livingCellLocations);
        return numberOfNeighbors == 3;  
    }
}

