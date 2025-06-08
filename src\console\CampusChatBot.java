import java.util.Scanner;

public class CampusChatBot{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Campus ChatBot!");
        System.out.println("Type 'exit' to quit.");

        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("ChatBot: Thank you! Have a nice day.");
                break;
            } else if (input.contains("hello") || input.contains("hi")) {
                System.out.println("ChatBot: Hello! How can I assist you today?");
            } else if (input.contains("library")) {
                System.out.println("ChatBot: The library is open from 8 AM to 8 PM.");
            } else if (input.contains("canteen")) {
                System.out.println("ChatBot: The canteen serves food from 8 AM to 6 PM.");
            } else {
                System.out.println("ChatBot: Sorry, I didn't understand that.");
            }
        }
        scanner.close();
    }
}
