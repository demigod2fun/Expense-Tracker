package dao;

import model.User;
import util.PasswordUtil;
import java.sql.*;

public class UserDAO {
    
    // ...existing code...
    // Create - Register new user
 public boolean registerUser(User user) throws SQLException {
    String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getEmail());
        // Hash the plain password, not a pre-hashed value
        String plainPassword = user.getPasswordHash();
        if (plainPassword == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        pstmt.setString(3, PasswordUtil.hashPassword(plainPassword));

        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLIntegrityConstraintViolationException e) {
        // duplicate username/email — caller can check usernameExists/emailExists if needed
        return false;
    }
}

    // Read - Login user (fetch stored hash and verify)
public User loginUser(String username, String password) throws SQLException {
    String sql = "SELECT * FROM users WHERE username = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, username);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String storedHash = rs.getString("password");
                // Verify the plain password against the stored hash
                if (PasswordUtil.verifyPassword(password, storedHash)) {
                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setCreatedAt(rs.getTimestamp("created_at"));
                    return user; // Return the user if password matches
                }
            }
        }
    }
    return null; // Return null if login fails
}
// ...existing code...
    
    // Read - Check if username exists
    public boolean usernameExists(String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    // Read - Check if email exists
    public boolean emailExists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
