import javax.swing.*;

public class Quiz {

    public static void main(String[] args) {
        try {
            // Устанавливаем стандартный стиль Java иначе цвета не видны(((
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Logic quiz = new Logic();
        quiz.startQuiz();
    }
}

