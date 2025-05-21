package projects;

    import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

    public class ExpenseTracker {
        static List<Transaction> transactions = new ArrayList<>();
        static Scanner scanner = new Scanner(System.in);
        static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        public static void main(String[] args) {
            System.out.println("Expense Tracker");
            while (true) {
                System.out.println("\n1. Add Transaction\n2. View Monthly Summary\n3. Load from File\n4. Save to File\n5. Exit");
                System.out.print("Enter choice: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> addTransaction();
                    case 2 -> showMonthlySummary();
                    case 3 -> loadFromFile();
                    case 4 -> saveToFile();
                    case 5 -> System.exit(0);
                    default -> System.out.println("Invalid choice.");
                }
            }
        }

        static void addTransaction() {
            System.out.print("Enter type (Income/Expense): ");
            String type = scanner.nextLine().trim();

            String category;
            if (type.equalsIgnoreCase("Income")) {
                System.out.print("Enter category (Salary/Business): ");
                category = scanner.nextLine().trim();
            } else if (type.equalsIgnoreCase("Expense")) {
                System.out.print("Enter category (Food/Rent/Travel): ");
                category = scanner.nextLine().trim();
            } else {
                System.out.println("Invalid type.");
                return;
            }

            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter date (yyyy-MM-dd): ");
            LocalDate date = LocalDate.parse(scanner.nextLine(), formatter);

            transactions.add(new Transaction(type, category, amount, date));
            System.out.println("Transaction added successfully.");
        }

        static void showMonthlySummary() {
            System.out.print("Enter month (yyyy-MM): ");
            String monthInput = scanner.nextLine().trim();

            double totalIncome = 0;
            double totalExpense = 0;
            Map<String, Double> categoryTotals = new HashMap<>();

            for (Transaction t : transactions) {
                String month = t.date.toString().substring(0, 7);
                if (month.equals(monthInput)) {
                    if (t.type.equalsIgnoreCase("Income")) {
                        totalIncome += t.amount;
                    } else {
                        totalExpense += t.amount;
                    }
                    categoryTotals.put(t.category,
                            categoryTotals.getOrDefault(t.category, 0.0) + t.amount);
                }
            }

            System.out.println("\nSummary for " + monthInput);
            System.out.println("Total Income: " + totalIncome);
            System.out.println("Total Expense: " + totalExpense);
            System.out.println("Net Savings: " + (totalIncome - totalExpense));
            System.out.println("\nBreakdown by Category:");
            for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }

        static void loadFromFile() {
            System.out.print("Enter file path to load: ");
            String filePath = scanner.nextLine().trim();
            try {
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                for (String line : lines.subList(1, lines.size())) {
                    String[] parts = line.split(",");
                    String type = parts[0];
                    String category = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    LocalDate date = LocalDate.parse(parts[3], formatter);
                    transactions.add(new Transaction(type, category, amount, date));
                }
                System.out.println("Data loaded successfully.");
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }

        static void saveToFile() {
            System.out.print("Enter file path to save: ");
            String filePath = scanner.nextLine().trim();
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
                writer.write("Type,Category,Amount,Date\n");
                for (Transaction t : transactions) {
                    writer.write(t.type + "," + t.category + "," + t.amount + "," + t.date + "\n");
                }
                System.out.println("Data saved successfully.");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }
    }

    class Transaction {
        String type;
        String category;
        double amount;
        LocalDate date;

        public Transaction(String type, String category, double amount, LocalDate date) {
            this.type = type;
            this.category = category;
            this.amount = amount;
            this.date = date;
        }
    }


