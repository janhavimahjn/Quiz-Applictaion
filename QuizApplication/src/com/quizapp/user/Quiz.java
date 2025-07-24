package com.quizapp.user;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.quizapp.dao.QuestionDAO;
import com.quizapp.dao.ResultDAO;
import com.quizapp.model.Question;

public class Quiz extends JFrame {
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int timeLeft = 15;
    private Timer timer;
    private JLabel questionLabel, timerLabel;
    private JRadioButton option1, option2, option3, option4;
    private ButtonGroup optionsGroup;
    private JButton nextButton;
    private String username; // Store logged-in user's name

    public Quiz(String username) {
        this.username = username;
        questions = QuestionDAO.getAllQuestions();
        setTitle("Quiz");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1));

        questionLabel = new JLabel();
        add(questionLabel);

        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();
        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        add(option1);
        add(option2);
        add(option3);
        add(option4);

        timerLabel = new JLabel("Time left: " + timeLeft + " sec");
        add(timerLabel);

        nextButton = new JButton("Next");
        nextButton.addActionListener(e -> nextQuestion());
        add(nextButton);

        startTimer();
        loadQuestion();
        setVisible(true);
    }

    private void startTimer() {
        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time left: " + timeLeft + " sec");
            if (timeLeft <= 0) {
                nextQuestion();
            }
        });
        timer.start();
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.size()) {
            timeLeft = 15;
            timer.restart();
            Question q = questions.get(currentQuestionIndex);
            questionLabel.setText(q.getQuestion());
            option1.setText(q.getOption1());
            option2.setText(q.getOption2());
            option3.setText(q.getOption3());
            option4.setText(q.getOption4());
        } else {
            finishQuiz();
        }
    }

    private void nextQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        String selectedAnswer = null;
        
        if (option1.isSelected()) selectedAnswer = option1.getText();
        if (option2.isSelected()) selectedAnswer = option2.getText();
        if (option3.isSelected()) selectedAnswer = option3.getText();
        if (option4.isSelected()) selectedAnswer = option4.getText();

        if (selectedAnswer != null && selectedAnswer.equals(currentQuestion.getAnswer())) {
            score++;
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            loadQuestion();
        } else {
            finishQuiz();
        }
    }

    private void finishQuiz() {
        timer.stop();
        ResultDAO.saveResult(username, score);
        JOptionPane.showMessageDialog(this, "Quiz Over! Your Score: " + score);
        dispose();
    }
}
