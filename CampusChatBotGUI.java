package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class CampusChatBotGUI extends JFrame {

    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    // Knowledge base same as tumhara console wala
    private static final Map<String, String> knowledgeBase = new HashMap<>();

    public CampusChatBotGUI() {
        setTitle("Campus Chat Bot");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center on screen

        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Send");

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // Send button and Enter key trigger
        ActionListener sendAction = e -> sendMessage();
        sendButton.addActionListener(sendAction);
        inputField.addActionListener(sendAction);

        initializeKnowledgeBase();
        chatArea.append("CampusBot: Hi! Ask me anything about our campus (type 'exit' to quit).\n");
    }

    private void sendMessage() {
        String userText = inputField.getText().trim();
        if (userText.isEmpty()) {
            return;
        }

        chatArea.append("You: " + userText + "\n");

        String lowerText = userText.toLowerCase();
        if (lowerText.contains("exit") || lowerText.contains("bye")) {
            chatArea.append("CampusBot: Goodbye! Have a great day.\n");
            inputField.setEditable(false);
            sendButton.setEnabled(false);
            return;
        }

        String botReply = getReply(lowerText);
        chatArea.append("CampusBot: " + botReply + "\n");

        inputField.setText("");
    }

    private String getReply(String userText) {
        for (String keyword : knowledgeBase.keySet()) {
            if (userText.contains(keyword)) {
                return knowledgeBase.get(keyword);
            }
        }
        return "Sorry, I don't have an answer for that. Try rephrasing.";
    }

    private void initializeKnowledgeBase() {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CampusChatBotGUI gui = new CampusChatBotGUI();
            gui.setVisible(true);
        });
    }
}
