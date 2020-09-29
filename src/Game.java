import java.util.ArrayList;
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

        Advanced proBot = new Advanced("Y");

        int count = 0;
        int playerCount = 0;
        int botCount = 0;

        Random ran = new Random();

        int ranNum = ran.nextInt(2);


        while (count <= 8) {
            if (ranNum == 0) {
                System.out.print("Please enter column: ");
                col = in.nextInt();
                Move move = new Move(b.getRow(col), col);
                b.markMove(move, player);
                b.getBoard();


                if (b.checkWin(player)) {
                    System.out.println("Player Wins!");
                    break;
                }

                playerCount++;
                if (playerCount + botCount == 16) {
                    break;
                }

                System.out.println("Bot Turn");

                System.out.println(proBot.minMax(b, botMark));
                b.markMove(proBot.theMove(), botMark);


                b.getBoard();


                if (b.checkWin(botMark)) {
                    System.out.println("Bot Wins!");
                    break;
                }
                botCount++;

                if (playerCount + botCount == 16) {
                    break;
                }

            }


            //bot goes first
            else {


                System.out.println("Bot Turn");

                proBot.minMax(b, botMark);
                b.markMove(proBot.theMove(), botMark);

                b.getBoard();


                if (b.checkWin(botMark)) {
                    System.out.println("Bot Wins!");
                    break;
                }
                botCount++;

                if (playerCount + botCount == 16) {
                    break;
                }

                System.out.print("Please enter column: ");
                col = in.nextInt();
                Move move = new Move(b.getRow(col), col);
                b.markMove(move, player);
                b.getBoard();

                if (b.checkWin(player)) {
                    System.out.println("Player Wins!");
                    break;
                }
                playerCount++;
                if (playerCount + botCount == 16) {
                    break;
                }
            }
            count++;
        }
        if (playerCount + botCount == 16) {

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

        while(count <=8){
            System.out.println("Bot Turn");

            System.out.println(proBot.minMax(b,botMark));
            System.out.println(proBot.theMove().toString());
            b.markMove(proBot.theMove(),botMark);
            b.getBoard();


            if(b.checkWin(botMark)){
                System.out.println("Bot1 Wins!");
                break;
            }

            botCount++;
            if(botCount+ bot2Count ==16){
                break;
            }

            System.out.println("Bot2 Turn");

            System.out.println(proBot2.minMax(b,bot2));
            System.out.println(proBot2.theMove().toString());
            b.markMove(proBot2.theMove(),bot2);


            b.getBoard();


            if(b.checkWin(bot2)){
                System.out.println("Bot2 Wins!");
                break;
            }
            bot2Count++;

            if(bot2Count+ botCount ==16){
                break;
            }

            count++;

        }
        if(bot2Count + botCount ==16){

            System.out.println("Draw!");
        }
    }
}
