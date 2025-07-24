package com.quizapp.admin;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.quizapp.dao.UserDAO;
import com.quizapp.model.User;

public class UserManager extends JFrame {
    public UserManager() {
        setTitle("Manage Users");
        setSize(400, 300);
        setLayout(new GridLayout(0, 1));

        List<User> users = UserDAO.getAllUsers();
        for (User user : users) {
            add(new JLabel("Username: " + user.getUsername()));
        }

        JButton resetPasswordButton = new JButton("Reset Password");
        resetPasswordButton.addActionListener(e -> resetPassword());
        add(resetPasswordButton);

        setVisible(true);
    }

    private void resetPassword() {
        String username = JOptionPane.showInputDialog("Enter username to reset password:");
        if (username != null) {
            boolean success = UserDAO.resetPassword(username);
            JOptionPane.showMessageDialog(this, success ? "Password reset!" : "User not found!");
        }
    }
}

