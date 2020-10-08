import java.util.Random;
import java.util.Scanner;

public class Game {
    public void playHuman(){
        int col;
        Scanner in = new Scanner(System.in);
        Mark player = new Mark("R");
        Mark botMark = new Mark("Y");

        Board b = new Board(6, 7);
        b.getBoard();

        Connect4Bot proBot = new Connect4Bot();
        int count = 0;
        int playerCount = 0;
        int botCount = 0;

        Random ran = new Random();

        int ranNum = ran.nextInt(2);


        while (count <= 21) {
            if (ranNum == 0) {
                System.out.print("Please enter column: ");
                col = in.nextInt();
                Move move = new Move(b.getRow(col), col);
                b.markMove(move, player);
                b.getBoard();


                if (b.checkWin(player,col)) {
                    System.out.println("Player Wins!");
                    break;
                }

                playerCount++;
                if (playerCount + botCount == 42) {
                    break;
                }

                System.out.println("Bot Turn");

                System.out.println(proBot.minMax(b, botMark,0,0));
                Move m = proBot.theMove();
                System.out.println("BOT MOVING HERE:" + m);
                b.markMove(m, botMark);


                b.getBoard();


                if (b.checkWin(botMark,m.getCol())) {
                    System.out.println("Bot Wins!");
                    break;
                }
                botCount++;

                if (playerCount + botCount == 42) {
                    break;
                }

            }


            //bot goes first
            else {


                System.out.println("Bot Turn");

                System.out.println(proBot.minMax(b, botMark,0,0));
                Move m = proBot.theMove();
                b.markMove(m, botMark);

                b.getBoard();


                if (b.checkWin(botMark,m.getCol())) {
                    System.out.println("Bot Wins!");
                    break;
                }
                botCount++;

                if (playerCount + botCount == 42) {
                    break;
                }

                System.out.print("Please enter column: ");
                col = in.nextInt();
                Move move = new Move(b.getRow(col), col);
                b.markMove(move, player);
                b.getBoard();

                if (b.checkWin(player,col)) {
                    System.out.println("Player Wins!");
                    break;
                }
                playerCount++;
                if (playerCount + botCount == 42) {
                    break;
                }
            }
            count++;
        }
        if (playerCount + botCount == 42) {

            System.out.println("Draw!");
        }
    }





    public void playAI(){

        Mark botMark = new Mark("Y");
        Mark bot2 = new Mark("R");

        Board b = new Board(6,7);
        b.getBoard();

        Advanced proBot = new Advanced("Y");
        Advanced proBot2 = new Advanced("R");

        int count =0;
        int botCount = 0;
        int bot2Count =0;

        while(count <=21){
            System.out.println("Bot Turn");

            System.out.println(proBot.minMax(b,botMark,0));
            Move m = proBot.theMove();
            b.markMove(m,botMark);
            b.getBoard();


            if(b.checkWin(botMark,m.getCol())){
                System.out.println("Bot1 Wins!");
                break;
            }

            botCount++;
            if(botCount+ bot2Count ==42){
                break;
            }

            System.out.println("Bot2 Turn");

            System.out.println(proBot2.minMax(b,bot2,0));
            Move m2 = proBot2.theMove();
            b.markMove(proBot2.theMove(),bot2);


            b.getBoard();


            if(b.checkWin(bot2,m2.getCol())){
                System.out.println("Bot2 Wins!");
                break;
            }
            bot2Count++;

            if(bot2Count+ botCount ==42){
                break;
            }

            count++;

        }
        if(bot2Count + botCount ==42){

            System.out.println("Draw!");
        }
    }
}
