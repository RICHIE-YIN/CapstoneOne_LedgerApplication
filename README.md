# 💰 Accounting Ledger CLI

A **Java console-based ledger system** I built to handle deposits, payments, and generate financial reports — all stored in a simple CSV file.

Built entirely from scratch using **core Java**, `FileReader`, `BufferedWriter`, and `LocalDate/LocalTime` — no frameworks, no shortcuts.

---

**🧱 How to Run**

```bash
git clone https://github.com/YOUR-USERNAME/Accounting-Ledger.git
cd Accounting-Ledger
javac -d out $(find src -name "*.java")
java -cp out com.pluralsight.screens.HomeScreen
```
💡 Requires Java 17+

🗂️ All transactions are saved in:

src/main/java/com/pluralsight/data/transactions.csv

---

## 🧭 Features


**🏠 Home Screen**
<br>
`mainScreen()` – The main hub
<br>
Options:
<br>
- D → Add Deposit (`addDeposit()`)
- P → Make Payment (`makePayment()`)
- L → View Ledger
- X → Exit

`addDeposit()` - Prompts for description, vendor, and amount, then writes the entry to the CSV with the current date and time.
<br>
`makePayment()` - Works like deposit but turns the amount negative.

---

**📒 Ledger Screen**
<br>
`ledgerScreen()` – Navigation for:
<br>
- A → View All Transactions
- D → Deposits Only (`viewDeposits()`)
- P → Payments Only (`viewPayments()`)
- R → Reports Menu

`viewAll()` – Reads every line from the CSV and displays newest first
<br>
`viewDeposits()` / `viewPayments()` – Filters positive or negative transactions

---

**📊 Reports**
<br>
`reportsScreen()` – Access to all filters:
<br>
- `monthToDate()` → Filters current month
- `previousMonth()` → Handles January → December wrap
- `yearToDate()` → Filters current year
- `previousYear()` → Filters previous year
- `searchByVendor(String vendor)` → Finds all transactions from a specific vendor (case-insensitive)

---

**🧩 Ledger Object**
Ledger.java
Defines each transaction:
```bash
LocalDate date;
LocalTime time;
String description;
String vendor;
double amount;
```
Automatically sets the date/time on creation:
```bash
this.date = LocalDate.now();
this.time = LocalTime.now().withNano(0);
```

---

**💾 Example Data (CSV)**
```bash
date|time|description|vendor|amount
2025-10-16|19:10:12|Paycheck|Microsoft|3000.00
2025-10-16|19:15:04|Office Chair|Amazon|-89.50
```

---

**💡 Highlights**


✅ Newest-first listing using backward iteration
<br>
✅ Automatic date/time tracking
<br>
✅ Clean, simple CSV persistence
<br>
✅ Previous month/year filtering using LocalDate logic
<br>
✅ Modular, readable, and 100% file-based — no database required

---

## 👤 Author


Richie Yin
<br>
Seattle, WA
<br>
Built for the Java LTCA, Capstone One.

---

**🔭 Vision Board**
<br>
https://trello.com/invite/b/68f1e5d714c600f6113b6710/ATTI865ffb22af1cc05abcab66123445f14e8F5B7246/capstone
<br>
