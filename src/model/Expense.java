package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Expense {
    private int expenseId;
    private int userId;
    private int categoryId;
    private String categoryName;
    private double amount;
    private Date expenseDate;
    private String description;
    private Timestamp createdAt;

    public Expense() {}

    public Expense(int userId, int categoryId, double amount, Date expenseDate, String description) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.description = description;
    }

    public int getExpenseId() { return expenseId; }
    public void setExpenseId(int expenseId) { this.expenseId = expenseId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public Date getExpenseDate() { return expenseDate; }
    public void setExpenseDate(Date expenseDate) { this.expenseDate = expenseDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
