import java.lang.reflect.Array;
import java.util.ArrayList;

public class Connect4Bot {
    private Move myMove;
    private ArrayList<BoardWithMove> winnable = new ArrayList<>();

    public Move theMove() {
        return myMove;
    }
    public int minMax(Board b, Mark turn, int numRec) {
        int maxSoFar = Integer.MIN_VALUE;
        int minSoFar = Integer.MAX_VALUE;
        int time = numRec;
        Move bestMoveSoFar = new Move();
        ArrayList<Move> moves = new ArrayList<>();
        Mark empty = new Mark(" ");

        Mark player = new Mark("R");
        Mark bot = new Mark("Y");

        for (BoardWithMove win : winnable) {
            Board winBoard = win.getBoard();
            if (sameBoard(b, winBoard)) {
                myMove = win.getMove();
                return 10;
            }
        }
        if (isEmpty(b)){
            myMove = new Move(b.board.length,b.board[0].length/2);
            return 10;
        }

        for (int i = 1; i <= b.board[0].length; i++) {
            if (b.getRow(i) != 0) {
                Move m = new Move(b.getRow(i), i);
                moves.add(m);
            }
        }
        if (b.checkWin(bot))
            return 10;
        else if (b.checkWin(player))
            return -10;
        else if (moves.size() == 0 || time==6)
            return 0;


        for (Move m : moves) {
            b.markMove(m, turn);
            if (turn.toString().equals("Y")) {
                int score = minMax(b, player,time+1);
                if (score > maxSoFar) {
                    maxSoFar = score;
                    bestMoveSoFar = m;
                }
            } else {
                int score = minMax(b, bot,time+1);
                if (score < minSoFar)
                    minSoFar = score;
            }
            b.markMove(m, empty);
        }

        myMove = bestMoveSoFar;
        if (turn.toString().equals("Y")) {
            if (maxSoFar == 10) {
                winnable.add(new BoardWithMove(myMove,b));
            }
            return maxSoFar;
        }
        else {
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
        for (int col=0;col<b.board[0].length;col++){
            if (b.getRow(col)!=b.board.length){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 7;
        int m = 2;
        System.out.println(7/2);
    }
}

