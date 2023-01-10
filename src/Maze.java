/**
* Класс, содержащий лабиринт
* */


public class Maze {

    private int height;
    private int width;
    private int[][] mazeMap;

    public Maze(int inputHeight, int inputWidth) {
        height = inputHeight + 2;
        width = inputWidth + 2;
        mazeMap = new int[height][width];
        createWall(0,0,height,"vertical");
        createWall(height - 1,0,height,"vertical");
        createWall(0,0,height,"horizontal");
        createWall(width - 1,0,height,"horizontal");
        createMaze();

    }

    public void setCoordinateValue(int horizontalCoordinate, int verticalCoordinate, int value) {
        mazeMap[horizontalCoordinate][verticalCoordinate] = value;
    }

    public int getCoordinateValue(int horizontalCoordinate, int verticalCoordinate) {
        return mazeMap[horizontalCoordinate][verticalCoordinate];
    }


    private void createMaze(){
        createWall(4,1, 4,"horizontal");
        createWall(6,6, 5,"horizontal");
        createWall(8,1, 5,"horizontal");
        createWall(9,3, 1,"horizontal");
        createWall(6,1, 4,"vertical");
        createWall(8,1, 4,"vertical");
        createWall(7,8, 3,"vertical");
        makeExit(9,2);
    }


    private void makeExit(int line, int column) {
        mazeMap[line][column] = -2;
    }

    private void createWall(int lineNumber, int startCoordinate, int size, String direction) {

        switch (direction) {
            case "vertical": {
                size = refreshSize(startCoordinate, size, height);
                createVerticalWall(startCoordinate, size, lineNumber);
            }

            case "horizontal": {
                size = refreshSize(startCoordinate, size, width);
                createHorizontalWall(startCoordinate, size, lineNumber);
            }
        }

    }


    public String printMaze() {
      StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(mazeMap[i][j]);
                sb.append("\t|\t");
            }
            sb.append("\n");
            for (int j = 0; j < width; j++) {
                sb.append("--\t\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    };


    private void createHorizontalWall(int startCoordinate, int size, int line) {
        for (int i = 0; i < size; i++) {
            mazeMap[line][startCoordinate++] = -1;
        }
    }


    private void createVerticalWall(int startCoordinate, int size, int line) {
        for (int i = 0; i < size; i++) {
            mazeMap[startCoordinate++][line] = -1;
        }
    }



    private int refreshSize(int startCoordinate, int size, int lineSize) {
        if (!checkSize(startCoordinate,size,lineSize)) {
            if (size  < 0) {
                size = 0;
            }
            else {
                size = lineSize - startCoordinate;
            }
        }
        return size;
    }


    private boolean checkSize(int startCoordinate, int size, int lineSize) {
        if ((startCoordinate + size - 1 > lineSize) && (size > 0)) {
            return false;
        }
        else {
            return true;
        }
    }


}
