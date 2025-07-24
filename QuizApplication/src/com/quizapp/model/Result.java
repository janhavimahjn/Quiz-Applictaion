package com.quizapp.model;

public class Result {
    private String username;
    private int score;

    // Constructor
    public Result(String username, int score) {
        this.username = username;
        this.score = score;
    }

    // Getter methods
    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    // Setter methods
    public void setUsername(String username) {
        this.username = username;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Display result details
    @Override
    public String toString() {
        return "User: " + username + ", Score: " + score;
    }
}
