CampusChatbotProject/
├── src/
│   ├── console/
│   │   ├── CampusChatbot.java
│   │   ├── BotResponse.java
│   │   ├── InputHandler.java
│   │   └── Utils.java
│   ├── gui/
│   │   └── CampusChatbotGUI.java
├── assets/
│   └── logo.png
├── docs/
│   ├── flowchart.png
│   └── architecture.pdf
├── README.md
├── .gitignore
├── LICENSE
└── run.sh

// src/console/CampusChatbot.java
public class CampusChatbot {
    public static void main(String[] args) {
        System.out.println("Welcome to CampusChatbot (Console Mode)!");
        InputHandler inputHandler = new InputHandler();
        BotResponse bot = new BotResponse();
        
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String input;
        
        while (true) {
            System.out.print("You: ");
            input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Bot: Goodbye!");
                break;
            }
            
            String cleanedInput = inputHandler.cleanInput(input);
            String reply = bot.getResponse(cleanedInput);
            System.out.println("Bot: " + reply);
        }
    }
}

// src/console/InputHandler.java
public class InputHandler {
    public String cleanInput(String input) {
        return input.trim().toLowerCase();
    }
}

// src/console/BotResponse.java
public class BotResponse {
    public String getResponse(String input) {
        switch (input) {
            case "hi":
            case "hello":
                return "Hello! How can I assist you about the campus today?";
            case "admission":
                return "You can apply online through our official portal. Need the link?";
            case "library":
                return "The library is open from 9 AM to 7 PM on weekdays.";
            case "hostel":
                return "Hostel applications are open for first-year students. Want to apply?";
            default:
                return "Sorry, I didn't understand that. Could you rephrase?";
        }
    }
}

// src/console/Utils.java
public class Utils {
    public static void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

// src/gui/CampusChatbotGUI.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CampusChatbotGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private BotResponse bot;

    public CampusChatbotGUI() {
        setTitle("CampusChatbot - GUI Version");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));

        inputField = new JTextField();
        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText();
                chatArea.append("You: " + userInput + "\n");
                String cleanedInput = userInput.trim().toLowerCase();
                String response = bot.getResponse(cleanedInput);
                chatArea.append("Bot: " + response + "\n");
                inputField.setText("");
            }
        });

        add(new JScrollPane(chatArea), BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);

        bot = new BotResponse();
        setVisible(true);
    }

    public static void main(String[] args) {
        new CampusChatbotGUI();
    }
}

// run.sh
#!/bin/bash
mkdir -p bin
javac -d bin src/console/*.java src/gui/*.java
java -cp bin console.CampusChatbot  # To run Console version
# java -cp bin gui.CampusChatbotGUI  # Uncomment to run GUI version

// .gitignore
*.class
bin/

// README.md
# CampusChatbot 🚀

CampusChatbot is a Java-based chatbot available in both Console and GUI versions. It assists students with campus-related queries like admissions, hostels, library, etc.

## 💡 Features
- Console-based chat interface
- GUI interface using Java Swing
- Clean and modular codebase
- Easily extendable for new questions

## 🛠️ Technologies Used
- Java (JDK 8+)
- Java Swing (for GUI)

## 🧩 File Structure
```
CampusChatbotProject/
├── src/
│   ├── console/           # Console version
│   └── gui/               # GUI version
├── assets/                # Logo or images
├── docs/                  # Project diagrams
├── README.md              # Documentation
├── run.sh                 # Run script
```

## ▶️ How to Run
```bash
chmod +x run.sh
./run.sh                # Runs console version
# To run GUI: edit run.sh and uncomment GUI line
```

## ✅ Sample Inputs
- "hi"
- "admission"
- "library"
- "hostel"

## 📌 Future Improvements
- NLP integration
- Database-backed FAQs

## 📄 License
MIT License
