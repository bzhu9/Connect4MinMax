public class Move {

    private int row, col;   // the coordinates, either 1, 2, or 3

    public Move(){

    }

    public Move (int inRow, int inCol) {
        row = inRow;
        col = inCol;
    }

    public int getRow () {
        return row;
    }

    public int getCol () {
        return col;
    }

    @Override
    public String toString() {
        return row + ", " + col;
    }
}
