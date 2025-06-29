import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MonkeyTyper extends JFrame implements KeyListener, ActionListener {

    String[] wordList = {"charizard", "pikachu", "snorlax", "umbreon", "lugia", "gengar","rayquaza","shaymin","emolga", "espeon", "hydreigon", "drampa", "reshiram", "zekrom"};
    ArrayList<String> allWords = new ArrayList<>();
    String paragraph = "";
    String whatUserTyped = "";
    int score = 0;
    int timeLeft = 60;

    JLabel wordsLabel, timerLabel;
    Timer timer;

    public MonkeyTyper() {
        setTitle("Typing Game :)");
        setSize(800, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        wordsLabel = new JLabel();
        wordsLabel.setBounds(20, 50, 750, 150);
        wordsLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        timerLabel = new JLabel("Time: 60");
        timerLabel.setBounds(700, 10, 100, 30);

        add(wordsLabel);
        add(timerLabel);

        addKeyListener(this);
        setFocusable(true);

        words();
        screen();
        time();
    }

    void words() {
        Random r = new Random();
        for (int i = 0; i < 50; i++) {
            allWords.add(wordList[r.nextInt(wordList.length)]);
        }
        StringBuilder sb = new StringBuilder();
        for (String w : allWords) {
            sb.append(w).append(" ");
        }
        paragraph = sb.toString();
    }

    void time() {
        timer = new Timer(1000, this);
        timer.start();
    }

    void screen() {
        StringBuilder output = new StringBuilder("<html>");

        for (int i = 0; i < whatUserTyped.length(); i++) {
            if (i < paragraph.length() && whatUserTyped.charAt(i) == paragraph.charAt(i)) {
                output.append("<font color='black'>").append(paragraph.charAt(i)).append("</font>");
            } else if (i < paragraph.length()) {
                output.append("<font color='red'>").append(paragraph.charAt(i)).append("</font>");
            }
        }

        if (whatUserTyped.length() < paragraph.length()) {
            output.append("<font color='gray'>").append(paragraph.substring(whatUserTyped.length())).append("</font>");
        }

        output.append("</html>");
        wordsLabel.setText(output.toString());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();

        if (c >= 32 && c <= 126 && whatUserTyped.length() < paragraph.length()) {
            whatUserTyped += c;
        }

        if (whatUserTyped.length() <= paragraph.length()) {
            screen();
        }

        if (c == ' ') {
            checkWord();
        }
    }

    void checkWord() {
        String[] typedWords = whatUserTyped.trim().split(" ");
        String[] correctWords = paragraph.trim().split(" ");

        int index = typedWords.length - 1;

        if (index >= 0 && index < correctWords.length) {
            if (typedWords[index].equals(correctWords[index])) {
                score++;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timeLeft--;
        timerLabel.setText("Time: " + timeLeft);

        if (timeLeft <= 0) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "time is up :( you get " + score + " points");
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && whatUserTyped.length() > 0) {
            whatUserTyped = whatUserTyped.substring(0, whatUserTyped.length() - 1);
            screen();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        new MonkeyTyper().setVisible(true);
    }
}
