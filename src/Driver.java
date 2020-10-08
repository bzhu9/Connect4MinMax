import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Human v AI(1) or AI v AI(2): ");
        int playType = in.nextInt();
        in.nextLine();
        Game g1 = new Game();

        if (playType == 2) {


            g1.playAI();
        }
        else {

            g1.playHuman();


        }

    }
}