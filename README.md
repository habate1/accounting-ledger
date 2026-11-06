# Accounting Ledger

A Java command-line application for tracking a small business's deposits and payments. Every transaction is saved to a CSV file, and the whole app is styled like a Linux SSH login session, with a welcome screen, shell prompts, and a logout screen.

> Built as a capstone project for the Pluralsight Java academy.

---

## Features

**Home screen**
- `D` Add a deposit
- `P` Make a payment (debit)
- `L` Open the ledger
- `X` Exit

**Ledger screen** (newest entries first)
- `A` All entries
- `D` Deposits only (positive amounts)
- `P` Payments only (negative amounts)
- `R` Reports
- `H` Back to home

**Reports screen**
1. Month to date
2. Previous month
3. Year to date
4. Previous year
5. Search by vendor
6. Custom search: filter by start date, end date, description, vendor, and amount. Any field left blank is not used as a filter.
0. Back to the ledger

**Extras**
- Terminal-style welcome and exit screens with a live balance and transaction count
- ANSI colors for prompts and output (green for deposits, red for payments)
- Colored table output with aligned columns
- Shell-style messages such as `grep: no matches found`

---

## Getting started

### Requirements
- Java 17 or newer
- Maven (or run it directly from IntelliJ IDEA)
- A terminal that supports ANSI colors (IntelliJ's Run window, macOS Terminal, or Windows Terminal)

### Run it

**From IntelliJ:** open the project and run `AccountingLedgerApp`.

---

## How to use it

1. Press **Enter** at the welcome screen to open the shop.
2. At the home menu, type a letter (`D`, `P`, `L`, or `X`) and press Enter.
3. **Adding a transaction:** enter a short description, the vendor, and the amount. The app stamps the current date and time and saves the entry to `transactions.csv`. Payments are stored as negative amounts.
4. **Viewing the ledger:** choose `L`, then pick all entries, deposits, payments, or reports.
5. **Search:** from Reports, choose `1 - 6` to filter through different fields. Press Enter to skip a field.

---

## Data format

All transactions are stored in `transactions.csv`, one per line, pipe-separated:

```
date|time|description|vendor|amount
```

Example:

```
2026-11-05|07:02:14|Morning espresso sales|Cash Register|312.50
2026-11-05|08:15:30|Oat milk delivery|Green Valley Dairy|-84.20
```

| Field | Format | Notes |
|---|---|---|
| date | `yyyy-MM-dd` | Filled in automatically for new entries |
| time | `HH:mm:ss` | Filled in automatically for new entries |
| description | text | |
| vendor | text | |
| amount | decimal | Positive = deposit, negative = payment |

---

## Project structure

```
accounting-ledger/
├── transactions.csv
├── pom.xml
└── src/main/java/com/pluralsight/
    ├── AccountingLedgerApp.java   # main method, home menu, loading and saving transactions
    ├── LedgerScreen.java          # ledger menu, reports, and table printing
    ├── Transaction.java           # data model for one ledger entry
    ├── Colors.java                # ANSI color constants and terminal helpers
    └── Screens.java               # welcome and exit screen
```

---

## Planning

Work was tracked on a GitHub Projects board using user stories, for example:

- As a user, I want to add a deposit so it is saved to the CSV.
- As a user, I want to view only payments, newest first.
- As a user, I want to run a custom search that combines several filters.

---

## What I learned

- Parsing text into typed objects (`LocalDate`, `LocalTime`, `double`) so data can be compared, filtered, and sorted
- Incorporating colors for text printed to the terminal
- Debugging by tracing values with print statements and checking list sizes
- Breaking repeated code into reusable methods and classes

### The hardest part
My first version of `readTransactions()` printed each line instead of converting it into a `Transaction` object, so every filter ran on an empty list. Once each line was split on the `|` character, parsed into real types, and added to a list, the Ledger and Reports features all worked.
I struggled with saving transactions because the CSV showed the object’s default text/garbage text instead of my data. I learned that objects need to be converted into a formatted string before writing them to a file.

---
