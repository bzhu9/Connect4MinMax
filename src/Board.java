public class Board {
    public char[][] board;// board change to a 2D array of chars
    private final char EMPTY = ' ';


    public Board(int inrow, int incol){
        board = new char[inrow][incol];
        for (int row = 0; row < inrow; row++)
            for (int col = 0; col < incol; col++)
                board[row][col] = EMPTY;
        //generate board with specified size
    }
    public void markMove(Move move, Mark mark) {
        board[move.getRow() -1][move.getCol() -1] = mark.toString().charAt(0);
    }

    public int getRow(int col) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col-1] != EMPTY)
                return i;
        }
        return board.length;
    }

    public void getBoard () {

        // display the column heading
        System.out.println();
        System.out.print("   col ");
        for (int col = 0; col < board[0].length; col++) {
            System.out.print(col + 1);
            if (col < board[0].length - 1)
                System.out.print(":");
        }
        System.out.println();
        System.out.println();

        // display the board
        for (int row = 0; row < board.length; row++) {
            System.out.print("row " + (row + 1) + ": ");
            for (int col = 0; col < board[0].length; col++) {
                System.out.print(board[row][col]);
                if (col < board[0].length - 1)
                    System.out.print("|");
            }
            if (row < board.length - 1) {
                System.out.println("");
                System.out.println("       -------------");
            }
            else
                System.out.println();
        }
        System.out.println();
    }


    public boolean cellFree (Move move) {
        return board[move.getRow()-1][move.getCol()-1] == EMPTY;
    }


    private boolean checkRows (Mark mark, int column) {
        int consecutive = 0;
        char[] currentRow = board[getRow(column)-1];
        for (int i = column - 4 - 1; i<column + 4 - 1; i++){
            if (!(i >= currentRow.length || i<0)){
                if (currentRow[i] == mark.toString().charAt(0))
                    consecutive ++;
                else
                    consecutive = 0;
            }
            if (consecutive==4)
                return true;
        }

        return false;
    }


    private boolean checkCols (Mark mark, int column) {
        int consecutive = 0;
        int pastRow = getRow(column)-1;
        for (int r = pastRow-4; r<pastRow+4; r++) {
            if (!(r>= board[0].length || r<0)) {
                if (board[r][column] == mark.toString().charAt(0))
                    consecutive++;
                else
                    consecutive = 0;
            }
            if (consecutive==4)
                return true;
        }
        return false;
    }
    private boolean checkDiags (Mark mark, int column) {
        boolean d1 = true;
        boolean d2 = true;
        // BOTTOM LEFT TO TOP RIGHT
        int consecutive = 0;
        int pastRow = getRow(column)-1;

        for (int r = pastRow-4, c = column -4; r<pastRow+4 && c<column + 4; r++, c++) {

        }

        for (int diag1 = 0; diag1 < board.length; diag1++)
            if (board[diag1][diag1] != mark.toString().charAt(0))
                d1 = false;

        for (int diag2 = 0; diag2 < board.length; diag2++)
            if (board[diag2][board.length - diag2 - 1] != mark.toString().charAt(0))
                d2 =  false;
        return d1 || d2;
    }

    public boolean checkWin (Mark mark, int column) {
        return checkRows(mark, column) || checkCols(mark, column) || checkDiags(mark, column);
    }

}
