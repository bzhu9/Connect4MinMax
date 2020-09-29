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


    private boolean checkRows (Mark mark) {
        for (int row = 0; row < 4; row++) {
            boolean temp = true;
            for (int col = 0; col < board.length; col++)
                if (board[row][col] != mark.toString().charAt(0))
                    temp =  false;
            if (temp)
                return true;
        }
        return false;
    }


    private boolean checkCols (Mark mark) {
        for (int col = 0; col<board[0].length; col++) {
            boolean temp = true;
            for (int row = 0; row < board.length; row++)
                if (board[row][col] != mark.toString().charAt(0))
                    temp =  false;
            if (temp)
                return true;
        }
        return false;
    }
    private boolean checkDiags (Mark mark) {
        boolean d1 = true;
        boolean d2 = true;
        for (int diag1 = 0; diag1 < board.length; diag1++)
            if (board[diag1][diag1] != mark.toString().charAt(0))
                d1 = false;

        for (int diag2 = 0; diag2 < board.length; diag2++)
            if (board[diag2][board.length - diag2 - 1] != mark.toString().charAt(0))
                d2 =  false;
        return d1 || d2;
    }

    public boolean checkWin (Mark mark) {
        return checkRows(mark) || checkCols(mark) || checkDiags(mark);
    }

}
