package com.quizapp.admin;

import javax.swing.*;

import com.quizapp.dao.QuestionDAO;
import com.quizapp.model.Question;

import java.awt.*;
import java.awt.event.ActionEvent;

public class AdminPanel extends JFrame {
    
    public AdminPanel() {
        setTitle("Admin Panel");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JButton manageQuestionsButton = new JButton("Manage Questions");
        JButton viewResultsButton = new JButton("View Quiz Results");
        JButton manageUsersButton = new JButton("Manage Users");
        JButton logoutButton = new JButton("Logout");

        manageQuestionsButton.addActionListener(e -> new QuestionManager());
        viewResultsButton.addActionListener(e -> new QuizResults());
        manageUsersButton.addActionListener(e -> new UserManager());
        logoutButton.addActionListener(e -> dispose());

        add(manageQuestionsButton);
        add(viewResultsButton);
        add(manageUsersButton);
        add(logoutButton);

        setVisible(true);
        
        Question newQuestion = new Question("What is Java?", "Language", "OS", "Browser", "Device", "Language");
        boolean isAdded = QuestionDAO.addQuestion(newQuestion);

        if (isAdded) {
            JOptionPane.showMessageDialog(null, "Question added successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to add question.");
        }

    }
}
