package service;

import model.Expense;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

// Multithreading demonstration
public class ExportService extends Thread {
    private List<Expense> expenses;
    private String filename;
    
    public ExportService(List<Expense> expenses, String filename) {
        this.expenses = expenses;
        this.filename = filename;
    }
    
    @Override
    public void run() {
        try (FileWriter writer = new FileWriter(filename)) {
            // Write CSV header
            writer.append("Expense ID,Category,Amount,Date,Description\n");
            
            // Write expense data
            for (Expense expense : expenses) {
                writer.append(String.valueOf(expense.getExpenseId())).append(",");
                writer.append(expense.getCategoryName()).append(",");
                writer.append(String.valueOf(expense.getAmount())).append(",");
                writer.append(expense.getExpenseDate().toString()).append(",");
                writer.append(expense.getDescription()).append("\n");
            }
            
            System.out.println("Export completed: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
