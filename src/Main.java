public class Main {
    public static void main(String[] args) {

        Maze myMaze = new Maze(10,10);
        System.out.println(myMaze.printMaze());
        Coordinate startingPosition = new Coordinate(3,9, 1);
        Finder finder = new Finder(myMaze, startingPosition);
        finder.findAWay();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println(myMaze.printMaze());
    }
}