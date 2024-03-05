// importing the necessary libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Task1 extends JFrame implements ActionListener {

    // components
    JPanel parentPanel;
    JPanel scorePanel;
    JPanel inputPanel;
    JPanel inputPanel1;
    JPanel inputPanel2;
    JPanel buttonPanel;

    JLabel scoreLabel;
    JLabel instructionLabel;
    JLabel hintLabel;

    JTextField inputField;

    JButton submitBtn;

    int actualNum;
    int trials;
    int MAX_TRIALS=10;

    Task1() {
        super("Guess the number");

        //upper panel
        scoreLabel = new JLabel("");
        Font font = new Font("Arial", Font.PLAIN, 30); 
        scoreLabel.setFont(font);
        scoreLabel.setVisible(false);
        scorePanel = new JPanel();
        scorePanel.add(scoreLabel);

        //middle panel

            //mini panel 1
            instructionLabel = new JLabel("Guess the number from 1-100:    ");
            inputField = new JTextField(10);
            inputPanel1 = new JPanel();
            inputPanel1.add(instructionLabel,BorderLayout.WEST);
            inputPanel1.add(inputField,BorderLayout.WEST);

            //mini panel 2
            hintLabel = new JLabel("");
            hintLabel.setVisible(false);
            inputPanel2 = new JPanel();
            inputPanel2.add(hintLabel,BorderLayout.CENTER);

        inputPanel = new JPanel(new GridLayout(2,1));
        inputPanel.add(inputPanel1);
        inputPanel.add(inputPanel2);


        //lower panel
        submitBtn = new JButton("Submit");
        submitBtn.addActionListener(this);
        buttonPanel = new JPanel();
        buttonPanel.add(submitBtn,BorderLayout.CENTER);


        //adding all panels
        parentPanel = new JPanel(new GridLayout(3,1));
        parentPanel.add(scorePanel);
        parentPanel.add(inputPanel);
        parentPanel.add(buttonPanel);

        add(parentPanel);

        //initialising the random number
        setRandomNumber();
        trials=0;

        //other features
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setRandomNumber() {
        actualNum = (int)(Math.random()*100)+1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();

        if(b.getText().equals("Submit")) {
            trials++;
            if(trials==MAX_TRIALS) {
                b.setText("Try again");
                displayScore(trials);
            }

            hintLabel.setVisible(true);

            int guess=0;
            try {
                guess = Integer.parseInt(inputField.getText());
            } catch (Exception ex) {
                hintLabel.setText("Please enter a number");
                trials--;
                return;
            }

            if(!(0<=guess && guess<=100)) {
                hintLabel.setText("Please enter within the range");
                trials--;
                return;
            }

            if(guess == actualNum) {
                b.setText("Try again");
                displayScore(trials);
                hintLabel.setText("You guessed it !!!");
                return;
            }
                
            hintLabel.setText(giveHint(guess));
            inputField.setText("");
        } else {
            trials=0;
            setRandomNumber();
            scoreLabel.setVisible(false);
            hintLabel.setVisible(false);
            inputField.setText("");
            b.setText("Submit");
        }
        
    }

    public void displayScore(int trials) {
        scoreLabel.setVisible(true);
        int score = (10-trials)+1;
        scoreLabel.setText("Your score is : "+score+" out of 10 !!!");
    }

    public String giveHint(int guess) {
        String str = new String();
        int diff = Math.abs(guess-actualNum);
        if (guess > actualNum) {
            if(diff > 30) 
                str="You guessed it too high";
            else if (diff < 30 && diff > 10)
                str = "think lower";
            else
                str = "think lower , you are closer";
        } 
        else {
            if(diff<10)
                str= "think higher , you are closer";
            else if (diff < 30 && diff > 10)
                str = "think higher";
            else
                str = "You guessed it too low";
        }

        return str;
    }

    public static void main(String[] args) {
        Task1 a = new Task1();
    }
}