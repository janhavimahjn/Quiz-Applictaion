package com.quizapp.dao;

import com.quizapp.model.Question;
import com.quizapp.dao.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
    
    // Method to retrieve all quiz questions from the database
    public static List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM questions";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Question q = new Question(
                   // rs.getInt("id"),
                    rs.getString("question"),
                    rs.getString("option1"),
                    rs.getString("option2"),
                    rs.getString("option3"),
                    rs.getString("option4"),
                    rs.getString("answer")
                );
                questions.add(q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    // Method to add a new question to the database
    public static boolean addQuestion(Question question) {
        String query = "INSERT INTO questions (question, option1, option2, option3, option4, answer) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, question.getQuestion());
            stmt.setString(2, question.getOption1());
            stmt.setString(3, question.getOption2());
            stmt.setString(4, question.getOption3());
            stmt.setString(5, question.getOption4());
            stmt.setString(6, question.getAnswer());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Returns true if a row is inserted

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
