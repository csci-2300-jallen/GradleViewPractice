/*
 * Things to try:
 * 1. Change textColor so the labels are easier to read over the background image.
 * 2. Change the font sizes and styles for the word, misses, and letter buttons.
 * 3. Change letterRows and letterColumns to arrange the buttons differently.
 *    GridLayout uses the row count to calculate columns when rows is not zero.
 * 4. Change gap to experiment with spacing between buttons and panels.
 * 5. Set a panel's opaque setting to true and experiment with its background color.
 * 6. Change the window size and try a different layout for the feedback panel.
 */
package org.example.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// A fixed game screen for practicing Swing layouts, separate from the game logic.
public class GUI {
    // Try different colors, font sizes, and button arrangements here.
    private final Color feedbackColor = Color.WHITE;
    private final Color lettersColor = Color.LIGHT_GRAY;
    private final Color textColor = Color.BLACK;
    private final Font wordFont = new Font("SansSerif", Font.BOLD, 28);
    private final Font messageFont = new Font("SansSerif", Font.PLAIN, 18);
    private final Font buttonFont = new Font("SansSerif", Font.BOLD, 16);
    private final int letterRows = 2;
    private final int letterColumns = 13;
    private final int gap = 5;

    public GUI() {
        JFrame frame = new JFrame("Word Guessing Prototype");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setBackgroundImage(frame);

        JPanel feedback = createFeedbackPanel();
        frame.add(feedback);
        JPanel letters = createLettersPanel();
        frame.add(letters);
        
        frame.setSize(750, 250); // Width and height in pixels.
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setBackgroundImage(JFrame frame) {
        Image image = new ImageIcon(GUI.class.getResource("/background.jpg")).getImage();
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                // Stretch the image to the panel's current width and height.
                graphics.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        // GridLayout arguments: rows, columns, horizontal gap, vertical gap.
        background.setLayout(new GridLayout(2, 1, gap, gap));
        frame.setContentPane(background);
    }

    private JPanel createFeedbackPanel() {
        JPanel feedback = new JPanel(new GridLayout(2, 1, gap, gap));
        feedback.setBackground(feedbackColor);
        feedback.setOpaque(false); // Use true to show the panel's background color.

        JLabel word = new JLabel("A - - - E", JLabel.CENTER);
        word.setFont(wordFont);
        word.setForeground(textColor);

        JLabel misses = new JLabel("Misses: 2/5", JLabel.CENTER);
        misses.setFont(messageFont);
        misses.setForeground(textColor);

        feedback.add(word);
        feedback.add(misses);
        return feedback;
    }

    private JPanel createLettersPanel() {
        JPanel letters = new JPanel(new GridLayout(letterRows, letterColumns, gap, gap));
        letters.setBackground(lettersColor);
        letters.setOpaque(false); // Use true to show the panel's background color.

        for (char letter = 'A'; letter <= 'Z'; letter++) {
            JButton button = new JButton("" + letter);
            button.setFont(buttonFont);
            button.setForeground(textColor);
            letters.add(button);
        }
        return letters;
    }
}
