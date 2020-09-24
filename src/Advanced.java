import java.util.ArrayList;

public class Advanced {
    private Move myMove;
    public Move theMove() {
        return myMove;
    }

    public int minMax(Board board, Mark playerTurn) {
        int maxSoFar = Integer.MIN_VALUE;
        int minSoFar = Integer.MAX_VALUE;
        Move bestMoveSoFar = new Move();
        ArrayList<Move> moves = new ArrayList<>();
        Mark empty = new Mark(" ");

        Mark player = new Mark("R");
        Mark bot = new Mark("Y");

//        System.out.println("in board");
//        board.getBoard();
        for (int i=1;i<=board.board[0].length;i++){
            if (board.getRow(i)!=0){
                Move m = new Move(board.getRow(i),i);
//                System.out.println(m + "efan");
                moves.add(m);
            }
        }
//        System.out.println("cheeeeeese");


        if (board.checkWin(bot))
            return 10;

        else if (board.checkWin(player))
            return -10;

        else if(moves.size()==0)
            return 0;


        for (Move m : moves) {
            board.markMove(m,playerTurn);
//            board.getBoard();
            if (playerTurn.toString().equals("Y") ){
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
        if (playerTurn.toString().equals("Y"))
            return maxSoFar;
        else
            return minSoFar;
    }

}

