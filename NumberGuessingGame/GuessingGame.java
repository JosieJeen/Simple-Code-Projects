/*
 * This porgram generates a random number from 1-10 and then
 * has the user try to guess that number
 * Jose Echeverria Martinez, 9/08/2025
 * Java 24.0.2
 */

import javax.swing.JOptionPane;

public class GuessingGame {
    public static void main(String[] args) {
        
        int computerNumber = (int) (Math.random( ) * 10 + 1 );
        System.out.println("The correct guess would be " + computerNumber);
        int userAnswer = 0;
        int count = 0;
        while (computerNumber != userAnswer){
            count++;
            String response = JOptionPane.showInputDialog(null,
                 "Enter a guess between 1 and 10");
            userAnswer = Integer.parseInt(response);
            String result=null;
            if(userAnswer == computerNumber){
                result = "Correct";
            }
            else {
                if (userAnswer <= 0 || userAnswer > 10){
                    result = "Invalid Guess";
                }
                else if (userAnswer > computerNumber){
                    result = "Too High";
                }
                else {
                    result = "Too Low";
                }
            }
            JOptionPane.showMessageDialog(null, result
                + "\nTry number " + count);
        
        }
        
        if (count == 1){
            JOptionPane.showMessageDialog(null, "You must be psychic!");
        }
        else if (count < 3) {
            JOptionPane.showMessageDialog(null, "Amazing!");
        }
        else if (count < 5) {
            JOptionPane.showMessageDialog(null, "Great Job!");
        }
        else {
            JOptionPane.showMessageDialog(null, "You need some practice");
        }
    }
    
}
