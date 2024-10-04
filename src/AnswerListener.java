import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class AnswerListener implements ActionListener {
    private int answerIndex;
    private int correctAnswerIndex;
    private JButton[] answerButtons;
    private JButton nextButton;

    public AnswerListener(int index, int correctAnswerIndex, JButton[] answerButtons, JButton nextButton) {
        this.answerIndex = index;
        this.correctAnswerIndex = correctAnswerIndex;
        this.answerButtons = answerButtons;
        this.nextButton = nextButton;
    }

    public void actionPerformed(ActionEvent e) {
        for (JButton button : answerButtons) {
            button.setEnabled(false);
        }
        if (answerIndex == correctAnswerIndex) {
            answerButtons[answerIndex].setBackground(new Color(134, 220, 93, 250));
        } else {
            answerButtons[answerIndex].setBackground(new Color(252, 78, 78, 250));
            answerButtons[correctAnswerIndex].setBackground(new Color(134, 220, 93, 250));
        }
        // Принудительно обновляем кнопки, чтобы изменения цвета стали видны
        for (JButton button : answerButtons) {
            button.repaint();
        }
        nextButton.setEnabled(true);
    }
}
