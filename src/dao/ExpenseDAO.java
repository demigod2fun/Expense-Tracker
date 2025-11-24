package dao;

import model.Expense;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {
    
    // Create - Add new expense
    public synchronized boolean addExpense(Expense expense) throws SQLException {
        String sql = "INSERT INTO expenses (user_id, category_id, amount, expense_date, description) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, expense.getUserId());
            pstmt.setInt(2, expense.getCategoryId());
            pstmt.setDouble(3, expense.getAmount());
            pstmt.setDate(4, expense.getExpenseDate());
            pstmt.setString(5, expense.getDescription());
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // Read - Get all expenses for a user
    public List<Expense> getAllExpenses(int userId) throws SQLException {
        List<Expense> expenses = new ArrayList<>();
        String sql = "SELECT e.*, c.category_name FROM expenses e " +
                     "JOIN categories c ON e.category_id = c.category_id " +
                     "WHERE e.user_id = ? ORDER BY e.expense_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Expense expense = new Expense();
                    expense.setExpenseId(rs.getInt("expense_id"));
                    expense.setUserId(rs.getInt("user_id"));
                    expense.setCategoryId(rs.getInt("category_id"));
                    expense.setCategoryName(rs.getString("category_name"));
                    expense.setAmount(rs.getDouble("amount"));
                    expense.setExpenseDate(rs.getDate("expense_date"));
                    expense.setDescription(rs.getString("description"));
                    expense.setCreatedAt(rs.getTimestamp("created_at"));
                    expenses.add(expense);
                }
            }
        }
        return expenses;
    }
    
    // Update - Update existing expense
    public boolean updateExpense(Expense expense) throws SQLException {
        String sql = "UPDATE expenses SET category_id = ?, amount = ?, expense_date = ?, description = ? WHERE expense_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, expense.getCategoryId());
            pstmt.setDouble(2, expense.getAmount());
            pstmt.setDate(3, expense.getExpenseDate());
            pstmt.setString(4, expense.getDescription());
            pstmt.setInt(5, expense.getExpenseId());
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // Delete - Delete expense
    public boolean deleteExpense(int expenseId) throws SQLException {
        String sql = "DELETE FROM expenses WHERE expense_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, expenseId);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    // Get total expenses for user
    public double getTotalExpenses(int userId) throws SQLException {
        String sql = "SELECT SUM(amount) as total FROM expenses WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
            }
        }
        return 0.0;
    }
}
