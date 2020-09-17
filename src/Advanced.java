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

        Mark player = new Mark("O");
        Mark bot = new Mark("X");

        Mark empty = new Mark(" ");
        ArrayList<Boolean> winPlayer = new ArrayList<>();
        ArrayList<Boolean> winBot = new ArrayList<>();
        ArrayList<Boolean> cellFree = new ArrayList<>();

        for (int i = 1; i <=3; i++) {
            for (int j = 1; j <=3; j++) {
                cellFree.add(board.cellFree(new Move(i,j)));
            }
        }

        winPlayer.add(board.checkDiag(1,player));
        winPlayer.add(board.checkDiag(2,player));
        for (int i = 1; i < 4; i++) {
            winPlayer.add(board.checkCol(i,player));
            winPlayer.add(board.checkRow(i,player));
        }

        winBot.add(board.checkDiag(1,bot));
        winBot.add(board.checkDiag(2,bot));
        for (int i = 1; i < 4; i++) {
            winBot.add(board.checkCol(i,bot));
            winBot.add(board.checkRow(i,bot));

        }

        if (winBot.contains(true))
            return 10;

        else if (winPlayer.contains(true))
            return -10;

        else if( !cellFree.contains(true))
            return 0;



        for (int i = 1; i <=3 ; i++) {
            for (int j = 1; j <=3 ; j++) {
                Move m = new Move(i,j);
                if(board.cellFree(m)){
                    moves.add(m);
                }
            }
        }

        for (Move m : moves) {
            board.markMove(m,playerTurn);
            if (playerTurn.toString().equals("X") ){
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
        if (playerTurn.toString().equals("X"))
            return maxSoFar;
        else
            return minSoFar;
    }

}

