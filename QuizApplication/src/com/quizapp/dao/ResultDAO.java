package com.quizapp.dao;

import com.quizapp.model.Result;
//import com.quizapp.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO {

    // Method to save user quiz result
    public static boolean saveResult(String username, int score) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO results (username, score) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setInt(2, score);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to fetch all results
    public static List<Result> getAllResults() {
        List<Result> results = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM results";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                results.add(new Result(rs.getString("username"), rs.getInt("score")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
