public class Board {
    private int row;
    private int col;
    private char[][] board;// board change to a 2D array of chars
    private final char EMPTY = ' ';


    public Board(int inrow, int incol){
        board = new char[inrow][incol];
        for (int row = 0; row < inrow; row++)
            for (int col = 0; col < incol; col++)
                board[row][col] = EMPTY;
        //generate board with specified size
    }
    public void markMove(Move move, Mark mark) {
        board[move.getRow() - 1][move.getCol() - 1] = mark.toString().charAt(0);
    }


    public void getBoard () {

        // display the column heading
        System.out.println();
        System.out.print("   col ");
        for (int col = 0; col < board.length; col++) {
            System.out.print(col + 1);
            if (col < board.length - 1)
                System.out.print(":");
        }
        System.out.println();
        System.out.println();

        // display the board
        for (int row = 0; row < board.length; row++) {
            System.out.print("row " + (row + 1) + ": ");
            for (int col = 0; col < board.length; col++) {
                System.out.print(board[row][col]);
                if (col < board.length - 1)
                    System.out.print("|");
            }
            if (row < board.length - 1) {
                System.out.println("");
                System.out.println("       -----");
            }
            else
                System.out.println();
        }
        System.out.println();
    }


    public boolean cellFree (Move move) {
        return board[move.getRow()-1][move.getCol()-1] == EMPTY;
    }


    public boolean checkRow (int row, Mark mark) {

        if (row < 1 || row > board.length)
            return false;


        for (int col = 0; col < board.length; col++)
            if (board[row - 1][col] != mark.toString().charAt(0))
                return false;


        return true;
    }

    public int rowWin (int row, Mark mark) {

        int count= 0;

        for (int col = 0; col < board.length; col++)
            if (board[row-1][col] == mark.toString().charAt(0))
                count++;

        return count;
    }


    public boolean checkCol (int col, Mark mark) {


        if (col < 1 || col > board.length)
            return false;


        for (int row = 0; row < board.length; row++)
            if (board[row][col - 1] != mark.toString().charAt(0))
                return false;


        return true;
    }

    public int colWin (int col, Mark mark) {

        int count= 0;

        for (int row = 0; row < board.length; row++)
            if (board[row][col-1] == mark.toString().charAt(0))
                count++;

        return count;
    }


    public boolean checkDiag (int diag, Mark mark) {

        if (diag != 1 && diag != 2)
            return false;

        if (diag == 1) {
            for (int diag1 = 0; diag1 < board.length; diag1++)
                if (board[diag1][diag1] != mark.toString().charAt(0))
                    return false;
        } else

            for (int diag2 = 0; diag2 < board.length; diag2++)
                if (board[diag2][board.length - diag2 - 1] != mark.toString().charAt(0))
                    return false;


        return true;
    }

    public int diagWin (int diag, Mark mark) {
        int count = 0;

        if (diag == 1) {
            for (int diag1 = 0; diag1 < board.length; diag1++)
                if (board[diag1][diag1] == mark.toString().charAt(0))
                    count++;
        }
        else
            for (int diag2 = 0; diag2 < board.length; diag2++)
                if (board[diag2][board.length - diag2 - 1] == mark.toString().charAt(0))
                    count++;


        return count;
    }





}
