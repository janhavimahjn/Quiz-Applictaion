package com.quizapp.admin;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.quizapp.dao.ResultDAO;
import com.quizapp.model.Result;

public class QuizResults extends JFrame {
    public QuizResults() {
        setTitle("Quiz Results");
        setSize(400, 300);
        setLayout(new GridLayout(0, 1));

        List<Result> results = ResultDAO.getAllResults();
        for (Result result : results) {
            add(new JLabel("User: " + result.getUsername() + " | Score: " + result.getScore()));
        }

        setVisible(true);
    }
}
