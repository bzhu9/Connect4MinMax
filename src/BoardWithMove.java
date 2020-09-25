public class BoardWithMove {
    private Move move;
    private Board board;

    public BoardWithMove(Move m, Board b){
        move = m;
        board = b;
    }

    public Board getBoard() {
        return board;
    }
    public Move getMove() {
        return move;
    }
}
