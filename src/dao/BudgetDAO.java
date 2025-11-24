package dao;

import model.Budget;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BudgetDAO {
    
    // Create - Add new budget
    public boolean addBudget(Budget budget) throws SQLException {
        String sql = "INSERT INTO budgets (user_id, category_id, budget_amount, start_date, end_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, budget.getUserId());
            pstmt.setInt(2, budget.getCategoryId());
            pstmt.setDouble(3, budget.getBudgetAmount());
            pstmt.setDate(4, budget.getStartDate());
            pstmt.setDate(5, budget.getEndDate());
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // Read - Get all budgets for user
    public List<Budget> getAllBudgets(int userId) throws SQLException {
        List<Budget> budgets = new ArrayList<>();
        String sql = "SELECT b.*, c.category_name FROM budgets b " +
                     "JOIN categories c ON b.category_id = c.category_id " +
                     "WHERE b.user_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Budget budget = new Budget();
                    budget.setBudgetId(rs.getInt("budget_id"));
                    budget.setUserId(rs.getInt("user_id"));
                    budget.setCategoryId(rs.getInt("category_id"));
                    budget.setCategoryName(rs.getString("category_name"));
                    budget.setBudgetAmount(rs.getDouble("budget_amount"));
                    budget.setStartDate(rs.getDate("start_date"));
                    budget.setEndDate(rs.getDate("end_date"));
                    budgets.add(budget);
                }
            }
        }
        return budgets;
    }
    
    // Delete budget
    public boolean deleteBudget(int budgetId) throws SQLException {
        String sql = "DELETE FROM budgets WHERE budget_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, budgetId);
            return pstmt.executeUpdate() > 0;
        }
    }
}
