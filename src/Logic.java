import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Logic {
    private JFrame frame;
    private JPanel panel;
    private JLabel questionLabel;
    private JButton[] answerButtons;
    private JButton nextButton;
    private int currentQuestion = 0;
    private int correctAnswerIndex;

    public void startQuiz() {
        frame = new JFrame("Викторина");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        showRules();
    }

    private void showRules() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel rulesLabel = new JLabel("<html><h1><center>Саша, мы подготовили для тебя викторину. Тебе предстоит пройти её.</h1></center>" +
                "<html><h2>Правила викторины:</h2>" +
                "<ul>" +
                "<li>Используй своё чувство юмора!</li>" +
                "<li>Выбери один правильный ответ.</li>" +
                "<li>Правильный ответ будет подсвечен зелёным.</li>" +
                "<li>Неправильный ответ будет подсвечен красным.</li>" +
                "</ul>" +
                "<h1><center>Да прибудет с тобой сила!</h1></center>" +
                "</ul></html>", SwingConstants.CENTER);
        panel.add(rulesLabel, BorderLayout.CENTER);
        JButton understoodButton = new JButton("Я понял!");
        understoodButton.setFocusPainted(false);
        understoodButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            loadQuestion();
        });
        panel.add(understoodButton, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setVisible(true);
    }

    public void loadQuestion() {
        frame.getContentPane().removeAll();
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        questionLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(questionLabel);

        answerButtons = new JButton[3];
        nextButton = new JButton("Следующий вопрос");

        for (int i = 0; i < 3; i++) {
            answerButtons[i] = new JButton();
            answerButtons[i].setBackground(new Color(236, 232, 232, 230));
            panel.add(answerButtons[i]);
        }

        nextButton.setEnabled(false);
        nextButton.setFocusPainted(false);
        panel.add(nextButton);

        frame.add(panel);
        frame.setVisible(true);

        loadNextQuestion();
    }

    public void loadNextQuestion() {
        if (currentQuestion < DoNotOpen.getQuestions().length) {
            String[] currentQ = DoNotOpen.getQuestions()[currentQuestion];
            questionLabel.setText(currentQ[0]);
            correctAnswerIndex = Integer.parseInt(currentQ[4]);

            // Обновляем кнопки ответов и удаляем старые обработчики событий
            for (int i = 0; i < 3; i++) {
                answerButtons[i].setText(currentQ[i + 1]);
                answerButtons[i].setBackground(new Color(236, 232, 232, 230));
                answerButtons[i].setEnabled(true);
                answerButtons[i].setFocusPainted(false);
                // Удаляем ВСЕ существующие обработчики событий с кнопки
                for (ActionListener al : answerButtons[i].getActionListeners()) {
                    answerButtons[i].removeActionListener(al);
                }

                // Добавляем новый обработчик для каждой кнопки
                answerButtons[i].addActionListener(new AnswerListener(i, correctAnswerIndex, answerButtons, nextButton));
            }

            // Удаляем предыдущий обработчик для кнопки "Следующий вопрос", если он был
            for (ActionListener al : nextButton.getActionListeners()) {
                nextButton.removeActionListener(al);
            }

            nextButton.addActionListener(e -> {
                currentQuestion++;
                if (currentQuestion < DoNotOpen.getQuestions().length) {
                    loadNextQuestion();
                } else {
                    showBirthdayMessage();
                }
            });
            nextButton.setEnabled(false);
            nextButton.setFocusPainted(false);

        } else {
            showBirthdayMessage();
        }
    }

    public void showBirthdayMessage() {
        JFrame congratsFrame = new JFrame("Поздравляем с Днём Рождения!");
        congratsFrame.setSize(1920, 1080);
        congratsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel congratsLabel = new JLabel("<html><center><br><br><br>Ура! Саша, ты справился с викториной!<br>Вот твой праздничный торт, скорее загадывай желание, оно обязательно сбудется!<br></center></html>", JLabel.CENTER);
        congratsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        ImageIcon gifIcon = new ImageIcon("resources/birthdayCake.gif");
        JLabel gifLabel = new JLabel(gifIcon);
        panel.add(congratsLabel, BorderLayout.NORTH);
        panel.add(gifLabel, BorderLayout.CENTER);
        congratsFrame.add(panel);
        congratsFrame.setVisible(true);
    }

}
