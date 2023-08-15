/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wargame;

/**
 *
 * @author shrey
 */
import java.util.Scanner;

public class WarGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();

        Player player1 = null;
        Player player2 = null;

        System.out.println("Welcome to War Game!");

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter a username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter a password: ");
                    String password = scanner.nextLine();
                   userManager.registerPlayer(username, password);
                    break;

                case 2:
                    System.out.print("Enter Player 1's username: ");
                    String loginUsername1 = scanner.nextLine();
                    System.out.print("Enter Player 1's password: ");
                    String loginPassword1 = scanner.nextLine();
                    player1 = userManager.login(loginUsername1, loginPassword1);

                    System.out.print("Enter Player 2's username: ");
                    String loginUsername2 = scanner.nextLine();
                    System.out.print("Enter Player 2's password: ");
                    String loginPassword2 = scanner.nextLine();
                    player2 = userManager.login(loginUsername2, loginPassword2);

                    if (player1 != null && player2 != null) {
                        System.out.println("Both players logged in successfully!");
                        // Start the game
                        Game game = new Game(player1, player2);
                        game.play();
                    } else {
                        System.out.println("Login failed. Invalid username or password.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting the game.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}