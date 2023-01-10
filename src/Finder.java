import java.util.*;

public class Finder {
    Maze maze;
    Coordinate currentPosition;
    Coordinate startPosition;
    ArrayDeque<Coordinate> waves = new ArrayDeque<>();
    boolean exitIsFind;

    public Finder(Maze inputMaze, Coordinate inputPosition) {
        maze = inputMaze;
        currentPosition = new Coordinate(inputPosition.getHorizontalCoordinate(), inputPosition.getVerticalCoordinate(), inputPosition.getValue());
        startPosition = new Coordinate(inputPosition.getHorizontalCoordinate(), inputPosition.getVerticalCoordinate(), inputPosition.getValue());
        exitIsFind = false;

    }


    public void findAWay() {
        waves.addLast(currentPosition);
        System.out.println(waves);
        while (!exitIsFind) {
            if (waves.isEmpty()) {
                System.out.println("Пройти невозможно");
                System.out.println(maze.printMaze());
                break;

            }
            currentPosition = waves.removeFirst();
            makeStep(currentPosition);
//            System.out.println(maze.printMaze());
        }
    }



    private void makeStep(Coordinate nextCoordinate) {
        currentPosition = nextCoordinate;
        if (isExit(nextCoordinate)) {
            System.out.println("Тут");
            exitIsFind = true;
        }
        else if (couldStep(nextCoordinate)){
            System.out.println("Ниже");
            maze.setCoordinateValue(nextCoordinate.getHorizontalCoordinate(), nextCoordinate.getVerticalCoordinate(), nextCoordinate.getValue());
            fillQueue(nextCoordinate);
        }

        else {
            System.out.println("Не должно быть");
        }
    }


    private void fillQueue(Coordinate coordinate) {
        if (lookUp(coordinate) == 0 || lookUp(coordinate) == -2) {
            waves.addLast(getUp(coordinate));
        }
        if (lookDown(coordinate) == 0 || lookDown(coordinate) == -2) {
            waves.addLast(getDown(coordinate));
        }
        if (lookRight(coordinate) == 0 || lookRight(coordinate) == -2) {
            waves.addLast(getRight(coordinate));
        }
        if (lookLeft(coordinate) == 0 || lookLeft(coordinate) == -2) {
            waves.addLast(getLeft(coordinate));
        }
    }


    private int lookUp(Coordinate coordinate) {
        return maze.getCoordinateValue(coordinate.getHorizontalCoordinate(), coordinate.getVerticalCoordinate() + 1);
    }

    private int lookDown(Coordinate coordinate) {
        return maze.getCoordinateValue(coordinate.getHorizontalCoordinate(), coordinate.getVerticalCoordinate() - 1);
    }

    private int lookRight(Coordinate coordinate) {
        return maze.getCoordinateValue(coordinate.getHorizontalCoordinate() + 1, coordinate.getVerticalCoordinate());
    }

    private int lookLeft(Coordinate coordinate) {
        return maze.getCoordinateValue(coordinate.getHorizontalCoordinate() - 1, coordinate.getVerticalCoordinate());
    }


    private Coordinate getUp(Coordinate coordinate) {
     return new Coordinate(coordinate.getHorizontalCoordinate(),coordinate.getVerticalCoordinate() + 1, coordinate.getValue() + 1);
    }

    private Coordinate getDown(Coordinate coordinate) {
        return new Coordinate(coordinate.getHorizontalCoordinate(),coordinate.getVerticalCoordinate() - 1, coordinate.getValue() + 1);
    }

    private Coordinate getRight(Coordinate coordinate) {
        return new Coordinate(coordinate.getHorizontalCoordinate() + 1,coordinate.getVerticalCoordinate(), coordinate.getValue() + 1);
    }

    private Coordinate getLeft(Coordinate coordinate) {
        return new Coordinate(coordinate.getHorizontalCoordinate() - 1,coordinate.getVerticalCoordinate(), coordinate.getValue() + 1);
    }




    private boolean isExit(Coordinate coordinate) {
        if (maze.getCoordinateValue(coordinate.getHorizontalCoordinate(), coordinate.getVerticalCoordinate()) == -2) {
            return true;
        }
        else {
            return false;
        }
    }


    private boolean couldStep(Coordinate coordinate) {
        if (maze.getCoordinateValue(coordinate.getHorizontalCoordinate(), coordinate.getVerticalCoordinate()) == 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
