# ExpenseTracker
# 📄 `data.csv` — Expense Tracker Input File

This CSV file contains a list of income and expense transactions used by the Expense Tracker Java application.

---

## 📌 File Format

The file must be in **CSV (Comma-Separated Values)** format with the following columns:


| Column    | Description                                                                 |
|-----------|-----------------------------------------------------------------------------|
| `Type`    | Either `Income` or `Expense`                                                |
| `Category`| Sub-category of the transaction (e.g., `Salary`, `Business`, `Food`, etc.) |
| `Amount`  | Numeric value of the transaction (positive number)                         |
| `Date`    | Date in `YYYY-MM-DD` format                                                 |

---

## ✅ Example

```csv
Type,Category,Amount,Date
Income,Salary,50000,2025-05-01
Expense,Food,2000,2025-05-02
Expense,Rent,10000,2025-05-03
Income,Business,20000,2025-05-04
Expense,Travel,5000,2025-05-05
