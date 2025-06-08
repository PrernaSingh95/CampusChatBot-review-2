import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class CampusChatBot {

    private static final Map<String, String> knowledgeBase = new HashMap<>();

    public static void main(String[] args) {
        initializeKnowledgeBase();
        Scanner scanner = new Scanner(System.in);

        System.out.println("CampusBot: Hi! Ask me anything about our campus (type 'exit' to quit).");

        try (FileWriter log = new FileWriter("chatlog.txt", true)) {
            while (true) {
                System.out.print("You: ");
                String userInput;

                try {
                    userInput = scanner.nextLine();
                } catch (NoSuchElementException | IllegalStateException e) {
                    System.out.println("CampusBot: Input error. Exiting.");
                    break;
                }

                if (userInput == null || userInput.trim().isEmpty()) {
                    System.out.println("CampusBot: Please enter something!");
                    continue;
                }

                userInput = userInput.toLowerCase().trim();

                if (userInput.contains("exit") || userInput.contains("bye")) {
                    System.out.println("CampusBot: Goodbye! Have a great day.");
                    log.write(LocalDateTime.now() + " | User: " + userInput + "\n");
                    log.write(LocalDateTime.now() + " | Bot: Goodbye! Have a great day.\n");
                    break;
                }

                boolean found = false;
                String response = "";

                for (String keyword : knowledgeBase.keySet()) {
                    if (userInput.contains(keyword)) {
                        response = knowledgeBase.get(keyword);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    response = "Sorry, I don't have an answer for that. Try rephrasing.";
                }

                System.out.println("CampusBot: " + response);

                // Log the chat
                log.write(LocalDateTime.now() + " | User: " + userInput + "\n");
                log.write(LocalDateTime.now() + " | Bot: " + response + "\n");
                log.flush();
            }
        } catch (IOException e) {
            System.out.println("CampusBot: Unable to write to chatlog.txt");
        }

        scanner.close();
    }

    private static void initializeKnowledgeBase() {
        knowledgeBase.put("admission", "Admissions are open from June to August. Visit the admissions office or our website.");
        knowledgeBase.put("library", "The library is open from 8 AM to 10 PM on weekdays.");
        knowledgeBase.put("hostel", "Hostels are available for both boys and girls. Contact the hostel office for more info.");
        knowledgeBase.put("placement", "Our placement cell is active year-round. Companies visit mainly from October to March.");
        knowledgeBase.put("canteen", "The canteen serves food from 9 AM to 7 PM daily.");
        knowledgeBase.put("fees", "Fee details are available on the official website or the accounts department.");
        knowledgeBase.put("courses", "We offer courses in engineering, management, arts, and science.");
        knowledgeBase.put("transport", "Bus services are available from 6 AM to 7 PM covering major city routes.");
        knowledgeBase.put("sports", "The campus has facilities for cricket, football, basketball, and a gym.");
        knowledgeBase.put("fee", "The transport fee is 1200 and university fee is 170000.");
        knowledgeBase.put("strength", "The strength in our university is about 1200000.");
    }
}
