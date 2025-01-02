package NestedLoops.No;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Number Guessing Game!");

        int totalScore = 0;
        boolean playAgain;

        do {
            System.out.print("Enter the range (e.g., 1-100): ");
            String[] rangeInput;
            int lowerBound = 0, upperBound = 0;
            boolean validRange = false;

            while (!validRange) {
                try {
                    rangeInput = scanner.nextLine().split("-");
                    lowerBound = Integer.parseInt(rangeInput[0].trim());
                    upperBound = Integer.parseInt(rangeInput[1].trim());

                    if (lowerBound < upperBound) {
                        validRange = true;
                    } else {
                        System.out.println("Invalid range. Ensure the lower bound is less than the upper bound.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input format. Please enter the range in the format 'lower-upper' (e.g., 1-100).");
                }
            }

            int randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int maxAttempts = 10;
            int attempts = 0;
            int roundScore = 0;

            System.out.println("\nA random number has been generated between " + lowerBound + " and " + upperBound + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess the number.");

            while (attempts < maxAttempts) {
                System.out.print("Attempt " + (attempts + 1) + ": Enter your guess: ");

                int guess;
                try {
                    guess = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter an integer.");
                    scanner.nextLine(); // Clear buffer
                    continue;
                }
                attempts++;

                if (guess == randomNumber) {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    roundScore = (maxAttempts - attempts + 1) * 10; // Points based on remaining attempts
                    totalScore += roundScore;
                    break;
                } else if (guess < randomNumber) {
                    System.out.println("The number is higher than " + guess + ". Try again.");
                } else {
                    System.out.println("The number is lower than " + guess + ". Try again.");
                }
            }

            if (attempts == maxAttempts && roundScore == 0) {
                System.out.println("Sorry, you've used all your attempts. The correct number was " + randomNumber + ".");
            }

            System.out.println("Round score: " + roundScore);
            System.out.println("Total score: " + totalScore);

            System.out.print("Do you want to play another round? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
            scanner.nextLine(); // Consume newline

        } while (playAgain);

        System.out.println("Thank you for playing! Your final score is: " + totalScore);
        scanner.close();
    }
}
