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
//        if (time==2){
//            System.out.println("cadshgjhmbnvbfsretrtghsdertrjhbnvcdrtytjhjvcxsaerrtgh");
//        }
//        System.out.println(time);
        Move bestMoveSoFar = new Move();
        ArrayList<Move> moves = new ArrayList<>();

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
        if (b.checkWin(bot,lastCol))
            return 10;
        else if (b.checkWin(player,lastCol))
            return -10;
//        else if ()
        else if (moves.size() == 0 || time==6)
            return 0;


        for (Move m : moves) {
            b.markMove(m, turn);
            int col = m.getCol();
            if (turn.toString().equals("Y")) {
                int score = minMax(b, player,time+1,col);
                if (score > maxSoFar) {
                    maxSoFar = score;
                    bestMoveSoFar = m;
                }
            } else {
                int score = minMax(b, bot,time+1,col);
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
        for (int col=1;col<=b.board[0].length;col++){
            if (b.getRow(col)!=b.board.length){
                return false;
            }
        }
        return true;
    }
    public boolean twoWin(Board b){
        for (int col=1;col<=b.board[0].length;col++){
            Board tempB = b;
            Board temp2 = b;
            int row = b.getRow(col);
            tempB.markMove(new Move(row,col),bot);
            int nextRow = tempB.getRow(col);
            tempB.getBoard();
            System.out.println("sfdgfhbcvxsderfhg");
            temp2.markMove(new Move(row,col),player);
            temp2.markMove(new Move(nextRow,col),bot);
            temp2.getBoard();
            System.out.println("helllllllllooooooooooooooooo");
            if (tempB.checkWin(bot,col)&&temp2.checkWin(bot,col)){
                return true;
            }
            tempB.markMove(new Move(row,col),empty);

            temp2.markMove(new Move(row,col),empty);
            temp2.markMove(new Move(nextRow,col),empty);

        }
        return false;
    }

    public static void main(String[] args) {
        Board b = new Board(6,7);
        b.getBoard();
//        b.markMove(new Move(6,1),new Mark("Y"));
//        b.markMove(new Move(5,1),new Mark("Y"));
        b.markMove(new Move(4,4),new Mark("Y"));
        b.markMove(new Move(3,3),new Mark("Y"));
//        b.markMove(new Move(4,3),new Mark("Y"));
//        b.markMove(new Move(5,4),new Mark("Y"));
        b.getBoard();
        Connect4Bot connect4Bot = new Connect4Bot();
        b.getBoard();
        b.markMove(new Move(2,2),new Mark("Y"));
        b.markMove(new Move(1,1),new Mark("Y"));
        b.getBoard();
        System.out.println(b.getRow(4));
        System.out.println(b.checkWin(new Mark("Y"),4));

    }
}

