# Java Expense Tracker - Complete Project

## Project Overview
A comprehensive Java-based Expense Tracker application demonstrating OOP principles, JDBC connectivity, Collections, Multithreading, and GUI implementation. Built to achieve maximum marks on the project rubric.

## Features
- ✅ User Authentication (Login/Register)
- ✅ Add, View, Update, Delete Expenses
- ✅ Category-based Expense Management
- ✅ Budget Tracking with Alerts
- ✅ Monthly/Yearly Reports
- ✅ Data Export to CSV (Multithreading)
- ✅ Responsive Java Swing GUI

## Technology Stack
- **Language**: Java SE 8+
- **GUI**: Java Swing
- **Database**: MySQL
- **JDBC**: MySQL Connector/J
- **Build Tool**: Maven (optional)

## Project Structure
```
ExpenseTracker/
├── src/
│   ├── model/
│   │   ├── User.java
│   │   ├── Expense.java
│   │   └── Budget.java
│   ├── dao/
│   │   ├── DatabaseConnection.java
│   │   ├── UserDAO.java
│   │   ├── ExpenseDAO.java
│   │   └── BudgetDAO.java
│   ├── service/
│   │   ├── AuthService.java
│   │   ├── ExpenseService.java
│   │   └── ExportService.java
│   ├── ui/
│   │   ├── LoginFrame.java
│   │   ├── RegisterFrame.java
│   │   ├── DashboardFrame.java
│   ├── util/
│   │   ├── PasswordUtil.java
│   │   └── ValidationUtil.java
│   └── Main.java
├── resources/
│   └── config.properties
├── schema.sql
├── lib/
│   └── mysql-connector-java-8.0.33.jar
└── README.md
```

## Database Setup

### Step 1: Create Database
```sql
CREATE DATABASE expense_tracker_db;
USE expense_tracker_db;
```

### Step 2: Run schema.sql
Execute the provided `schema.sql` file to create all necessary tables.

## Configuration

### Database Configuration
Edit `resources/config.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/expense_tracker_db
db.username=root
db.password=yourpassword
```

## How to Run

### Option 1: Using IDE (Recommended)
1. Import project into Eclipse/IntelliJ IDEA
2. Add MySQL Connector JAR to build path
3. Configure database in `config.properties`
4. Run `Main.java`

### Option 2: Command Line
```bash
# Compile
javac -cp ".:lib/mysql-connector-java-8.0.33.jar" -d bin src/**/*.java

# Run
java -cp "bin:lib/mysql-connector-java-8.0.33.jar" Main
```


## Key Features Demonstration

### 1. User Authentication
- Secure password hashing
- Session management
- Input validation

### 2. Expense Management
- Add expenses with categories
- View all expenses in table
- Update/Delete operations
- Filter by date range

### 3. Budget Tracking
- Set monthly budgets per category
- Real-time budget alerts
- Progress visualization

### 4. Reports & Export
- Monthly expense summaries
- Category-wise breakdown
- CSV export (multithreaded)

## Design Patterns Used
- **DAO Pattern**: Separates business logic from data access
- **Singleton Pattern**: Database connection management
- **MVC Pattern**: Separation of concerns (Model-View-Controller)
- **Factory Pattern**: Object creation for entities

## Security Features
- Password encryption (SHA-256)
- SQL injection prevention (PreparedStatement)
- Input validation and sanitization

## Testing Checklist
- [x] User registration and login
- [x] Add/Edit/Delete expenses
- [x] Budget creation and tracking
- [x] Report generation
- [x] CSV export functionality
- [x] Exception handling
- [x] Database connectivity

## Screenshots
Include screenshots of:
1. Login screen
2. Dashboard with expense list
3. Add expense form
4. Budget tracking view
5. Report generation

## Dependencies
- MySQL Connector/J 8.0.33+
- Java SE 8 or higher

## Troubleshooting

### Database Connection Error
- Verify MySQL is running
- Check credentials in `config.properties`
- Ensure database exists

### ClassNotFoundException
- Add MySQL Connector JAR to classpath

## Future Enhancements
- Email notifications for budget alerts
- Mobile app integration
- Cloud sync functionality
- Advanced analytics dashboard

## Author
**Lakshya Choudhary**  
B.Tech Computer Science Engineering  
Email: choudharylakshya05@gmail.com

## License
Educational project for academic submission.

## Submission Checklist
- [x] Complete source code
- [x] Database schema file
- [x] README with setup instructions
- [x] Presentation slides
- [x] All rubric requirements met
- [x] Code properly commented
- [x] GitHub repository public

---
