import java.util.ArrayList;

public class Advanced {
    private Move myMove;
    private Mark player;
    private Mark bot;
    private int count = 0;
    public Advanced (String mark){
        bot = new Mark(mark);
        if (mark.equals("R")) {
            player = new Mark("Y");
        }
        else {
            player = new Mark("R");
        }
    }
    public Move theMove() {
        return myMove;
    }

    public int minMax(Board board, Mark playerTurn) {
        int maxSoFar = Integer.MIN_VALUE;
        int minSoFar = Integer.MAX_VALUE;
        Move bestMoveSoFar = new Move();
        ArrayList<Move> moves = new ArrayList<>();
        count++;
        Mark empty = new Mark(" ");

        for (int i=1;i<=board.board[0].length;i++){
            if (board.getRow(i)!=0){
                Move m = new Move(board.getRow(i),i);
                moves.add(m);
            }
        }


        if (board.checkWin(bot))
            return 10;

        else if (board.checkWin(player))
            return -10;

        else if(moves.size()==0)
            return 0;


        for (Move m : moves) {
            board.markMove(m,playerTurn);
//            board.getBoard();
            if (playerTurn.toString().equals(bot.toString())){
                int score = minMax(board, player);
                if (score > maxSoFar) {
                    maxSoFar = score;
                    bestMoveSoFar = m;
                }
            }
            else {
                int score = minMax(board, bot);
                if(score < minSoFar)
                    minSoFar = score;
            }
            board.markMove(m,empty);
        }

        myMove = bestMoveSoFar;
        if (playerTurn.toString().equals(bot.toString())) {
            return maxSoFar;
        }
        else {
            return minSoFar;
        }
    }

}

