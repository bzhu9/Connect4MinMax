public class ProBot {
    private String turn;
    private int myMove;
    public ProBot(){
        turn = "X";
    }

    public String play(Board b){

        minMax(b, "X");

        return Integer.toString(myMove);
    }
    public int minMax(Board board, String player) {
        // your code here
        int maxSoFar = Integer.MIN_VALUE;
        int minSoFar = Integer.MAX_VALUE;
        int bestMoveSoFar = 0;
        if (board.checkWin("X").equals("w")){
            return 10;
        }
        if (board.checkWin("O").equals("w")) {
            return -10;
        }
        if (board.checkWin("X").equals("c"))
            return 0;
        for (Integer empty:board.getEmpty()){
            board.makeMove(empty, player);
            if (player.equals ("X")){
                int score = minMax(board, "O");
                if (score>maxSoFar) {
                    maxSoFar = score;
                    bestMoveSoFar = empty;
                }
            }
            else {
                int score = minMax(board, "X");
                if (score < minSoFar)
                    minSoFar = score;
            } // end else
            board.undoMove(empty, player);
        }
        myMove = bestMoveSoFar;
        if (player.equals("X"))
            return maxSoFar;
        else
            return minSoFar;
    }
}
