package com.quizapp.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import com.quizapp.dao.QuestionDAO;
import com.quizapp.model.Question;

public class QuestionManager extends JFrame {
    private JTextField questionField, option1Field, option2Field, option3Field, option4Field, answerField;
    
    public QuestionManager() {
        setTitle("Manage Questions");
        setSize(500, 300);
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Question:"));
        questionField = new JTextField();
        add(questionField);

        add(new JLabel("Option 1:"));
        option1Field = new JTextField();
        add(option1Field);

        add(new JLabel("Option 2:"));
        option2Field = new JTextField();
        add(option2Field);

        add(new JLabel("Option 3:"));
        option3Field = new JTextField();
        add(option3Field);

        add(new JLabel("Option 4:"));
        option4Field = new JTextField();
        add(option4Field);

        add(new JLabel("Correct Answer:"));
        answerField = new JTextField();
        add(answerField);

        JButton addButton = new JButton("Add Question");
        addButton.addActionListener(e -> addQuestion());
        add(addButton);

        setVisible(true);
    }

    private void addQuestion() {
    	
        String question = questionField.getText();
        String option1 = option1Field.getText();
        String option2 = option2Field.getText();
        String option3 = option3Field.getText();
        String option4 = option4Field.getText();
        String answer = answerField.getText();

        boolean success = QuestionDAO.addQuestion(new Question(question, option1, option2, option3, option4, answer));
        if (success) {
            JOptionPane.showMessageDialog(this, "Question added successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Error adding question!");
        }
    }
}
