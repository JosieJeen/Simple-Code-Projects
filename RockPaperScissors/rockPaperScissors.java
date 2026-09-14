import java.util.Scanner;

public class rockPaperScissors {
    public static void main(String[] args) throws Exception {
        System.out.println("\n\tThis is a game for Rock Paper Scissors!\n");

        Scanner scanner = new Scanner(System.in);

        String[] choices = {"rock", "paper", "scissors"};
        String userChoice;

        while (true) {
            System.out.println("Enter your choice (rock, paper, scissors): ");
            userChoice = scanner.nextLine().toLowerCase();

            if (userChoice.equals("rock") || userChoice.equals("paper") || userChoice.equals("scissors")) {
                break;
            }

            System.out.println("Invalid choice! Please choose rock, paper, or scissors.");
        }

        String computerChoice = choices[(int) (Math.random() * choices.length)];

        System.out.println("You chose: " + userChoice);
        System.out.println("Computer chose: " + computerChoice);
        System.out.println("\n-------------------\n");;

        if (userChoice.equals(computerChoice)) {
            System.out.println("It's a tie!");
        } 
        else if ((userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                (userChoice.equals("paper") && computerChoice.equals("rock")) ||
                (userChoice.equals("scissors") && computerChoice.equals("paper"))) {

            System.out.println("You win!");
        } else {
            System.out.println("Computer wins!");
        }

        System.out.println("\n-------------------\n");;

        scanner.close();

    }
}
