import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class WifiSecurityGUI extends JFrame {

    JTextField passwordField;
    JLabel resultLabel;

    public WifiSecurityGUI() {

        setTitle("WiFi Password Security Analyzer");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel title = new JLabel("Enter WiFi Password:");
        add(title);

        passwordField = new JTextField(20);
        add(passwordField);

        JButton checkButton =
                new JButton("Check Password");
        add(checkButton);

        JButton generateButton =
                new JButton("Generate Strong Password");
        add(generateButton);

        JButton saveButton =
                new JButton("Save Report");
        add(saveButton);

        resultLabel = new JLabel("");
        resultLabel.setFont(
                new Font("Arial", Font.BOLD, 16));

        add(resultLabel);

        checkButton.addActionListener(e -> checkPassword());

        generateButton.addActionListener(e -> generatePassword());

        saveButton.addActionListener(e -> saveReport());

        setVisible(true);
    }

    public void checkPassword() {

        String password = passwordField.getText();

        int score = 0;

        if (password.length() >= 8)
            score++;

        if (password.matches(".*[A-Z].*"))
            score++;

        if (password.matches(".*[a-z].*"))
            score++;

        if (password.matches(".*[0-9].*"))
            score++;

        if (password.matches(".*[!@#$%^&*()].*"))
            score++;

        if (password.equals("123456")
                || password.equals("password")) {

            resultLabel.setText("COMMON WEAK PASSWORD");
            resultLabel.setForeground(Color.RED);
            return;
        }

        if (score <= 2) {

            resultLabel.setText(
                    "WEAK PASSWORD | Crack Time: Seconds");

            resultLabel.setForeground(Color.RED);

        }
        else if (score <= 4) {

            resultLabel.setText(
                    "MEDIUM PASSWORD | Crack Time: Days");

            resultLabel.setForeground(Color.ORANGE);

        }
        else {

            resultLabel.setText(
                    "STRONG PASSWORD | Crack Time: Years");

            resultLabel.setForeground(Color.GREEN);
        }
    }

    public void generatePassword() {

        String chars =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvwxyz"
                + "0123456789"
                + "@#$%&*!";

        Random random = new Random();

        StringBuilder password =
                new StringBuilder();

        for (int i = 0; i < 12; i++) {

            password.append(
                    chars.charAt(
                            random.nextInt(chars.length())));
        }

        JOptionPane.showMessageDialog(
                this,
                "Strong Password:\n" + password);
    }

    public void saveReport() {

        try {

            FileWriter writer =
                    new FileWriter("report.txt", true);

            writer.write(
                    "Password: "
                            + passwordField.getText()
                            + "\n");

            writer.write(
                    "Result: "
                            + resultLabel.getText()
                            + "\n");

            writer.write(
                    "====================\n");

            writer.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Report Saved!");

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error Saving File");
        }
    }

    public static void main(String[] args) {

        new WifiSecurityGUI();
    }
}