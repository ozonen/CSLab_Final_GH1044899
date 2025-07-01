import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Pokemon_Monkey_Typer extends JFrame implements KeyListener, ActionListener {
    //All words in array list
    String[] wordList = {"charizard", "pikachu", "snorlax", "umbreon", "lugia", "gengar","rayquaza","shaymin","emolga", "espeon", "hydreigon", "drampa", "reshiram", "zekrom"};
    ArrayList<String> allWords = new ArrayList<>();
    String paragraph = ""; //paragraph
    String whatUserTyped = ""; //Counter of what the user has typed so far
    int score = 0; //Score counter
    int timeLeft = 60; //remaining time 

    JLabel wordsLabel, timerLabel;
    Timer timer;

    public Pokemon_Monkey_Typer() {   //Constructor of the game 
        setTitle("Typing Game :)"); //title
        setSize(800, 300); //size of the window
        setDefaultCloseOperation(EXIT_ON_CLOSE); //close application when window closes
        setLayout(null); //manual positioning

        wordsLabel = new JLabel(); //Label to display paragraph
        wordsLabel.setBounds(20, 50, 750, 150); //positon and size
        wordsLabel.setFont(new Font("Arial", Font.PLAIN, 20)); //Arial font

        timerLabel = new JLabel("Time: 60"); //Label to display time
        timerLabel.setBounds(700, 10, 100, 30); 

        //Adds both label into the game
        add(wordsLabel);  
        add(timerLabel);

        addKeyListener(this); //Make the window listen for keyboard input
        setFocusable(true); //Make sure the window can capture key events

        all_words(); //Random Paragraph
        current_screen(); //Display the paragraph
        remaining_time(); //Start the timer
    }

    //Method to randomize the paragraph
    void all_words() {
        Random r = new Random();
        for (int i = 0; i < 50; i++) { //Loop for choosing only 50 word from array list
            allWords.add(wordList[r.nextInt(wordList.length)]); //Put a random word to the paragraph
        }
        StringBuilder sb = new StringBuilder(); //To make paragraph one long string
        for (String w : allWords) { 
            sb.append(w).append(" "); //To list words with a space
        }
        paragraph = sb.toString(); //Save the paragraph
    }

    void remaining_time() {
        timer = new Timer(1000, this); //1000ms means 1 second 
        timer.start(); //To start the timer
    }

    void current_screen() { //For coloring the text with red, black and gray 
        StringBuilder output = new StringBuilder("<html>");

        for (int i = 0; i < whatUserTyped.length(); i++) {
            if (i < paragraph.length() && whatUserTyped.charAt(i) == paragraph.charAt(i)) {
                output.append("<font color='black'>").append(paragraph.charAt(i)).append("</font>"); // Black for every successfully typed word
            } else if (i < paragraph.length()) {
                output.append("<font color='red'>").append(paragraph.charAt(i)).append("</font>"); // Red for every mistyped word
            }
        }

        if (whatUserTyped.length() < paragraph.length()) { //Gray for the remaining or non typed words
            output.append("<font color='gray'>").append(paragraph.substring(whatUserTyped.length())).append("</font>");
        }

        output.append("</html>"); //End html
        wordsLabel.setText(output.toString()); //Update the label on the screen
    }

    @Override
    public void keyTyped(KeyEvent e) { //Triggered whenever a key is typed (any key)
        char c = e.getKeyChar(); //Typed character

        if (c >= 32 && c <= 126 && whatUserTyped.length() < paragraph.length()) { // Only accepts ASCII 32 to 126 
            whatUserTyped += c; //User input
        }

        if (whatUserTyped.length() <= paragraph.length()) {
            current_screen(); 
        }

        if (c == ' ') { //If space is pressed check the word if it is true or not
            checkWord();
        }
    }

    void checkWord() { // Method to check if the last typed word matches the correct word
        String[] typedWords = whatUserTyped.trim().split(" ");
        String[] correctWords = paragraph.trim().split(" ");

        int index = typedWords.length - 1; //index of the last typed word

        if (index >= 0 && index < correctWords.length) { //Loop method for increasing the score
            if (typedWords[index].equals(correctWords[index])) { 
                score++;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) { //Basically timer
        timeLeft--;
        timerLabel.setText("Time: " + timeLeft);

        if (timeLeft <= 0) {
            timer.stop(); //Timer stopper
            JOptionPane.showMessageDialog(this, "time is up :( you get " + score + " points"); //Pop up to show the final score
            System.exit(0); //Close the program
        }
    }

    @Override
    public void keyPressed(KeyEvent e) { //To detect backspace 
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && whatUserTyped.length() > 0) {
            whatUserTyped = whatUserTyped.substring(0, whatUserTyped.length() - 1); //Remove last character
            current_screen(); //Update screen after "backspace"
        }
    }

    @Override 
    public void keyReleased(KeyEvent e) {} //This is required by the "keyListener" but not used in this project

    public static void main(String[] args) { //Main method, starts the game
        new Pokemon_Monkey_Typer().setVisible(true);
    }
}
