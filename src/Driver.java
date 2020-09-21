import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        int row;
        int col;
        Scanner in = new Scanner(System.in);


        Mark player = new Mark("R");
        Mark botMark = new Mark("Y");

        Board b = new Board(6,7);
        b.getBoard();

        Advanced proBot = new Advanced();

        int count =0;
        int playerCount =0;
        int botCount = 0;

        Random ran = new Random();

        int ranNum = ran.nextInt(2);


        while(count <=8){
            if(ranNum == 0){
                ArrayList<Boolean> winPlayer = new ArrayList<>();

                System.out.print("Please enter column: ");
                col =  in.nextInt();
                Move move = new Move(col);
                b.markMove(move,player);
                b.getBoard();


                if(checkWin("R")){
                    System.out.println("Player Wins!");
                    break;
                }

                playerCount++;
                if(playerCount+ botCount ==42){
                    break;
                }


                ArrayList<Boolean> winBot = new ArrayList<>();
                System.out.println("Bot Turn");

                System.out.println(proBot.minMax(b,botMark));
                b.markMove(proBot.theMove(),botMark);


                b.getBoard();


                winBot.add(b.checkDiag(1,botMark));
                winBot.add(b.checkDiag(2,botMark));
                for (int i = 1; i < 4; i++) {
                    winBot.add(b.checkCol(i,botMark));
                    winBot.add(b.checkRow(i,botMark));

                }

                if(checkWin("Y")){
                    System.out.println("Bot Wins!");
                    break;
                }
                botCount++;

                if(playerCount+ botCount ==42){
                    break;
                }

            }


            //bot goes first
            else{


                System.out.println("Bot Turn");
                ArrayList<Boolean> winBot = new ArrayList<>();

                System.out.println(proBot.minMax(b,botMark));
                b.markMove(proBot.theMove(),botMark);

                b.getBoard();



                if(checkWin("Y")){
                    System.out.println("Bot Wins!");
                    break;
                }
                botCount++;

                if(playerCount+ botCount =42){
                    break;
                }

                ArrayList<Boolean> winPlayer = new ArrayList<>();
                System.out.print("Please enter column: ");
                col =  in.nextInt();
                Move move = new Move(col);
                b.markMove(move,player);
                b.getBoard();

                if(checkWin("R")){
                    System.out.println("Player Wins!");
                    break;
                }
                playerCount++;
                if(playerCount+ botCount ==42){
                    break;
                }



            }

            count++;


        }
        if(playerCount + botCount ==42){

            System.out.println("Draw!");


        }


    }
}