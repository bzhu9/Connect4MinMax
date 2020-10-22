import java.lang.reflect.Array;
import java.util.ArrayList;

public class Connect4Bot {
    private Move myMove;
    private ArrayList<BoardWithMove> winnable = new ArrayList<>();

    private Mark empty = new Mark(" ");

    private Mark player = new Mark("R");
    private Mark bot = new Mark("Y");

    public Move theMove() {
        return myMove;
    }
    public int minMax(Board b, Mark turn, int numRec, int lastCol) {
        int maxSoFar = Integer.MIN_VALUE;
        int minSoFar = Integer.MAX_VALUE;
        int time = numRec;
        Move bestMoveSoFar = new Move();
        ArrayList<Move> moves = new ArrayList<>();

//        for (BoardWithMove win : winnable) {
//            Board winBoard = win.getBoard();
//            if (sameBoard(b, winBoard)) {
//                myMove = win.getMove();
//                return 10;
//            }
//        }
//        if (isEmpty(b)){
//            myMove = new Move(b.board.length,b.board[0].length/2);
//            return 10;
//        }
        if (numRec!=0) {
            if (b.checkWin(bot, lastCol)) {
                return 10;
            }
            else if (b.checkWin(player, lastCol)) {
                return -10;
            }
            else if (nextWin(b)){
                return 9;
            }
            else if (checkThree(b,turn,lastCol)){
                if (turn==player){
                    return -3;
                }
                else {
                    return 3;
                }
            }
        }
        boolean isempty = true;
        for (int i = 1; i <= b.board[0].length; i++) {
            if (b.getRow(i) != 0) {
                Move m = new Move(b.getRow(i), i);
                moves.add(m);
                if (b.getRow(i)!=6){
                    isempty=false;
                }
            }
        }
        if (moves.size() == 0 || time==7) {
            return 0;
        }
        else if (isempty){
            myMove=new Move(6,4);
            return 10;
        }


        for (Move m : moves) {
            b.markMove(m, turn);
            if (turn.toString().equals("Y")) {
                int score = minMax(b, player,time+1, m.getCol());
                if (score > maxSoFar) {
                    maxSoFar = score;
                    bestMoveSoFar = m;
                }
            } else {
                int score = minMax(b, bot,time+1, m.getCol());
                if (score < minSoFar)
                    minSoFar = score;
            }
            b.markMove(m, empty);
        }
        myMove = bestMoveSoFar;
        if (turn.toString().equals("Y")) {
//            if (maxSoFar == 10) {
//                winnable.add(new BoardWithMove(myMove,b));
//            }
            //System.out.println(myMove + ": " + maxSoFar);
            return maxSoFar;
        }
        else {
            //System.out.println(myMove + ": " + minSoFar);
            return minSoFar;
        }
    }
    private boolean sameBoard(Board newOne, Board oldOne){
        for (int col=0;col<newOne.board[0].length;col++){
            for (int row = newOne.board.length-1;row>=0;row--){
                if (!Character.toString(newOne.board[row][col]).equals(Character.toString(oldOne.board[row][col]))){
                    return false;
                }
                if (Character.toString(newOne.board[row][col]).equals(" ")){
                    row=-1;
                }
            }
        }
        return true;
    }
    private boolean isEmpty(Board b){
        for (int col=1;col<=b.board[0].length;col++){
            if (b.getRow(col)!=b.board.length){
                return false;
            }
        }
        return true;
    }
    private boolean nextWin(Board b){
        for (int col=1;col<=b.board[0].length;col++){
            Board tempB = b;
            Board temp2 = b;
            int row = b.getRow(col);
            if (row<=1){
                break;
            }
            tempB.markMove(new Move(row,col),bot);
            int nextRow = tempB.getRow(col);
            if (checkThree(tempB,bot,col)){

                temp2.markMove(new Move(row,col),player);
                temp2.markMove(new Move(nextRow,col),bot);
                if (temp2.checkWin(bot,col)){
                    tempB.markMove(new Move(row,col),empty);

                    temp2.markMove(new Move(row,col),empty);
                    temp2.markMove(new Move(nextRow,col),empty);
                    return true;
                }

                temp2.markMove(new Move(row,col),empty);
                temp2.markMove(new Move(nextRow,col),empty);
            }

            tempB.markMove(new Move(row,col),empty);
        }
        return false;
    }
    private boolean check3Rows (Board b,Mark mark, int column) {
        int consecutive = 0;
        int space = 0;
        char[] currentRow = b.board[b.getRow(column)];
        for (int i = column - 4; i<column + 4 - 1; i++){
            if (!(i >= currentRow.length || i<0)){
                if (currentRow[i] == mark.toString().charAt(0))
                    consecutive ++;
                else if (space==0&&consecutive!=0)
                    space++;
                else
                    consecutive = 0;
            }
            if (consecutive==3)
                return true;
        }

        return false;
    }
    private boolean check3Cols (Board b, Mark mark, int column) {
        int consecutive = 0;
        int pastRow = b.getRow(column)+1;
        for (int r = pastRow-4; r<pastRow+4; r++) {

            if (!(r>= b.board.length || r<0)) {
                if (b.board[r][column-1] == mark.toString().charAt(0))
                    consecutive++;
                else
                    consecutive = 0;
            }
            if (consecutive==3)
                return true;
        }
        return false;
    }
    private boolean check3Diags (Board b, Mark mark, int column) {
        // TOP LEFT TO BOTTOM RIGHT
        int consecutive = 0;
        int pastRow = b.getRow(column)+1;
        int space = 0;

        for (int r = pastRow-4, c = column -4; r<pastRow+4 && c<column + 4; r++, c++) {
            if (!(r>= b.board.length || r<0 || c>= b.board[0].length || c<0)) {
                if (b.board[r][c] == mark.toString().charAt(0))
                    consecutive++;
                else if (space==0)
                    space++;
                else
                    consecutive = 0;
            }
            if (consecutive == 3)
                return true;
        }
        consecutive = 0;
        for (int r = pastRow+2, c = column -4; r>pastRow-6 && c<column + 4; r--, c++) {
            if (!(r>= b.board.length || r<0 || c>= b.board[0].length || c<0)) {
                if (b.board[r][c] == mark.toString().charAt(0))
                    consecutive++;
                else
                    consecutive = 0;
            }
            if (consecutive == 3)
                return true;
        }

        return false;
    }
    public boolean checkThree (Board b, Mark mark, int column) {
        return check3Rows(b,mark, column) || check3Cols(b,mark, column) || check3Diags(b,mark, column);
    }

    public static void main(String[] args) {
        Board b = new Board(6,7);
        b.getBoard();
        b.markMove(new Move(6,4),new Mark("R"));
        b.markMove(new Move(6,6),new Mark("R"));
        b.markMove(new Move(6,7),new Mark("R"));

        b.getBoard();
        Connect4Bot connect4Bot = new Connect4Bot();
        b.getBoard();
        System.out.println(b.getRow(6));
        System.out.println(connect4Bot.checkThree(b,connect4Bot.player,5));
//        System.out.println(b.checkWin(new Mark("Y"),4));

    }
}

