import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Board b = new Board();
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe! You are player O");

        System.out.print("Which bot would you like to play against (1=basic, 2=pro): ");
        String botChoice = s.nextLine();

        if (botChoice.equals("1")) {
            BasicBot bot = new BasicBot();
            while (b.checkWin("X").equals("n")) {
                b.makeMove(Integer.parseInt(bot.play(b)), "X");
                System.out.println(b.printBoard() + "\n");
                if (b.checkWin("X").equals("w")) {
                    System.out.println("Player X wins! Better luck next time");
                    break;
                }
                System.out.print("Enter your move (1-9): ");
                String playerMove = s.nextLine();
                b.makeMove(Integer.parseInt(playerMove), "O");
                if (b.checkWin("O").equals("w")) {
                    System.out.println(b.printBoard() + "\n");
                    System.out.println("You win! Good job");
                    break;
                }

            }
        }
        if (botChoice.equals("2")) {
            ProBot bot = new ProBot();
            while (b.checkWin("X").equals("n")) {
                b.makeMove(Integer.parseInt(bot.play(b)), "X");
                System.out.println(b.printBoard() + "\n");
                if (b.checkWin("X").equals("w")) {
                    System.out.println("Player X wins! Better luck next time");
                    break;
                }
                if (b.checkWin("O").equals("c")) {
                    System.out.println(b.printBoard() + "\n");
                    System.out.println("Cats Game!");
                    break;
                }
                System.out.print("Enter your move (1-9): ");
                String playerMove = s.nextLine();
                b.makeMove(Integer.parseInt(playerMove), "O");
                if (b.checkWin("O").equals("w")) {
                    System.out.println(b.printBoard() + "\n");
                    System.out.println("You win! Good job");
                    break;
                }

            }
        }





    }
}
