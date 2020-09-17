import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        int opponent;
        boolean gameOver = false;


        do{
            System.out.print("Who would you like to play against(1=basic bot, 2=pro bot): ");
            Scanner input = new Scanner(System.in);
            opponent = input.nextInt();

        }while(!(opponent == 1 || opponent ==2));

        int row;
        int col;
        Scanner in = new Scanner(System.in);


        Mark player = new Mark("O");
        Mark botMark = new Mark("X");
        Mark botMark2 = new Mark("O");
        Board b = new Board(3,3);
        b.getBoard();

        Advanced proBot = new Advanced();

        int count =0;
        int playerCount =0;
        int botCount = 0;
        int otherCount = 0;

        Random ran = new Random();

        int ranNum = 1;// ran.nextInt(2);


        while(count <=8){
            if(ranNum == 0){
                ArrayList<Boolean> winPlayer = new ArrayList<>();

                System.out.print("Please enter row:    ");
                row = in.nextInt();
                System.out.println("");
                System.out.print("Please enter column: ");
                col =  in.nextInt();
                Move move = new Move(row,col);
                b.markMove(move,player);
                b.getBoard();


                winPlayer.add(b.checkDiag(1,player));
                winPlayer.add(b.checkDiag(2,player));
                for (int i = 1; i < 4; i++) {
                    winPlayer.add(b.checkCol(i,player));
                    winPlayer.add(b.checkRow(i,player));
                }
                if(winPlayer.contains(true)){
                    System.out.println("Player Wins!");
                    break;
                }
                playerCount++;
                if(playerCount+ botCount ==9){
                    break;
                }


                ArrayList<Boolean> winBot = new ArrayList<>();
                System.out.println("Bot Turn");

                if(opponent ==1){
                    System.out.println(proBot.minMax(b,botMark));
                    b.markMove(proBot.theMove(),botMark);
                }


                b.getBoard();


                winBot.add(b.checkDiag(1,botMark));
                winBot.add(b.checkDiag(2,botMark));
                for (int i = 1; i < 4; i++) {
                    winBot.add(b.checkCol(i,botMark));
                    winBot.add(b.checkRow(i,botMark));

                }

                if(winBot.contains(true)){
                    System.out.println("Bot Wins!");
                    break;
                }
                botCount++;

                if(playerCount+ botCount ==9){
                    break;
                }

            }


            //bot goes first
            else{


                System.out.println("Bot Turn");
                ArrayList<Boolean> winBot = new ArrayList<>();

                if(opponent ==1){
                    System.out.println(proBot.minMax(b,botMark));
                    b.markMove(proBot.theMove(),botMark);
                }

                b.getBoard();


                winBot.add(b.checkDiag(1,botMark));
                winBot.add(b.checkDiag(2,botMark));
                for (int i = 1; i < 4; i++) {
                    winBot.add(b.checkCol(i,botMark));
                    winBot.add(b.checkRow(i,botMark));

                }

                if(winBot.contains(true)){
                    System.out.println("Bot Wins!");
                    break;
                }
                botCount++;

                if(playerCount+ botCount ==9){
                    break;
                }

                ArrayList<Boolean> winPlayer = new ArrayList<>();

                System.out.print("Please enter row:    ");
                row = in.nextInt();
                System.out.println("");
                System.out.print("Please enter column: ");
                col =  in.nextInt();
                Move move = new Move(row,col);
                b.markMove(move,player);
                b.getBoard();


                winPlayer.add(b.checkDiag(1,player));
                winPlayer.add(b.checkDiag(2,player));
                for (int i = 1; i < 4; i++) {
                    winPlayer.add(b.checkCol(i,player));
                    winPlayer.add(b.checkRow(i,player));
                }
                if(winPlayer.contains(true)){
                    System.out.println("Player Wins!");
                    break;
                }
                playerCount++;
                if(playerCount+ botCount ==9){
                    break;
                }



            }

            count++;


        }
        if(playerCount + botCount ==9){

            System.out.println("Cats Game!");


        }


    }
}