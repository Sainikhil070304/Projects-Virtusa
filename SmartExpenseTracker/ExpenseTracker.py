import csv
import os
from datetime import datetime
import matplotlib.pyplot as plt
from collections import defaultdict

DATA_FILE = "expenses.csv"

CATEGORIES = ["Food", "Travel", "Bills", "Shopping", "Health", "Entertainment", "Other"]

SUGGESTIONS = {
    "Food":          "Try meal prepping at home — it can cut food costs by up to 40%.",
    "Travel":        "Consider carpooling or public transport to save on travel.",
    "Bills":         "Review your subscriptions — cancel anything you rarely use.",
    "Shopping":      "Wait 24 hours before any non-essential purchase.",
    "Health":        "Look for generic medicine alternatives — they work just as well.",
    "Entertainment": "Explore free local events or use student/family plan discounts.",
    "Other":         "Track these vague expenses more specifically next time.",
}


def load_expenses():
    if not os.path.exists(DATA_FILE):
        return []
    with open(DATA_FILE, newline="") as f:
        return list(csv.DictReader(f))


def save_expense(date, category, amount, description):
    file_exists = os.path.exists(DATA_FILE)
    with open(DATA_FILE, "a", newline="") as f:
        writer = csv.DictWriter(f, fieldnames=["date", "category", "amount", "description"])
        if not file_exists:
            writer.writeheader()
        writer.writerow({
            "date": date,
            "category": category,
            "amount": amount,
            "description": description
        })


def add_expense():
    print("\n   Add New Expense ")

    date_input = input("  Date (DD-MM-YYYY) or press Enter for today: ").strip()
    if not date_input:
        date = datetime.today().strftime("%d-%m-%Y")
    else:
        try:
            datetime.strptime(date_input, "%d-%m-%Y")
            date = date_input
        except ValueError:
            print("  Invalid date format. Using today's date.")
            date = datetime.today().strftime("%d-%m-%Y")

    print("  Categories:", ", ".join(f"{i+1}.{c}" for i, c in enumerate(CATEGORIES)))
    while True:
        try:
            choice = int(input("  Pick a category (1-7): "))
            if 1 <= choice <= len(CATEGORIES):
                category = CATEGORIES[choice - 1]
                break
            print("  Please pick a number between 1 and 7.")
        except ValueError:
            print("  Enter a number, not text.")

    while True:
        try:
            amount = float(input("  Amount (₹): "))
            if amount <= 0:
                print("  Amount must be greater than 0.")
                continue
            break
        except ValueError:
            print("  Please enter a valid number.")

    description = input("  Description: ").strip() or "No description"

    save_expense(date, category, round(amount, 2), description)
    print(f"\n Saved: ₹{amount:.2f} under {category} on {date}")


def view_all():
    expenses = load_expenses()
    if not expenses:
        print("\n  No expenses recorded yet.")
        return

    print(f"\n  {'Date':<14} {'Category':<15} {'Amount':>10}  Description")
    total = 0
    for e in expenses:
        print(f"  {e['date']:<14} {e['category']:<15} ₹{float(e['amount']):>8.2f}  {e['description']}")
        total += float(e['amount'])
    print(f"  {'Total':>30} ₹{total:>8.2f}")


def monthly_summary():
    expenses = load_expenses()
    if not expenses:
        print("\n  No expenses to summarize.")
        return

    months = sorted(set(e['date'][3:] for e in expenses), reverse=True)
    print("\n  Available months:", ", ".join(months))
    picked = input("  Enter month-year (MM-YYYY) or press Enter for latest: ").strip()
    target = picked if picked in months else months[0]

    filtered = [e for e in expenses if e['date'][3:] == target]
    if not filtered:
        print(f"  No data for {target}.")
        return

    category_totals = defaultdict(float)
    for e in filtered:
        category_totals[e['category']] += float(e['amount'])

    total = sum(category_totals.values())
    top_category = max(category_totals, key=category_totals.get)

    print(f"\n           Monthly Summary: {target} ")
    print(f"  {'Category':<18} {'Amount':>10}  {'% of Total':>10}")
    for cat, amt in sorted(category_totals.items(), key=lambda x: -x[1]):
        pct = (amt / total) * 100
        print(f"  {cat:<18} ₹{amt:>8.2f}  {pct:>9.1f}%")
    print(f"  {'Total':<18} ₹{total:>8.2f}")
    print(f"\n Highest spending: {top_category} (₹{category_totals[top_category]:.2f})")
    print(f"  Tip: {SUGGESTIONS[top_category]}")

    show_chart(category_totals, target)


def show_chart(category_totals, month_label):
    labels = list(category_totals.keys())
    values = list(category_totals.values())

    colors = ["#FF6B6B", "#4ECDC4", "#45B7D1", "#96CEB4", "#FFEAA7", "#DDA0DD", "#98D8C8"]

    fig, ax = plt.subplots(figsize=(7, 7))
    wedges, texts, autotexts = ax.pie(
        values,
        labels=labels,
        autopct="%1.1f%%",
        colors=colors[:len(labels)],
        startangle=140,
        pctdistance=0.82,
        wedgeprops=dict(width=0.6, edgecolor="white", linewidth=2)
    )

    for text in texts:
        text.set_fontsize(11)
    for auto in autotexts:
        auto.set_fontsize(9)
        auto.set_color("white")
        auto.set_fontweight("bold")

    ax.set_title(f"Expense Breakdown — {month_label}", fontsize=14, fontweight="bold", pad=20)

    total = sum(values)
    ax.text(0, 0, f"₹{total:.0f}\nTotal", ha="center", va="center",
            fontsize=12, fontweight="bold", color="#333333")

    chart_file = f"expense_chart_{month_label.replace('-', '_')}.png"
    plt.tight_layout()
    plt.savefig(chart_file, dpi=150, bbox_inches="tight")
    plt.show()
    print(f"\n Chart saved as {chart_file}")


def delete_last():
    expenses = load_expenses()
    if not expenses:
        print("\n  Nothing to delete.")
        return
    removed = expenses[-1]
    with open(DATA_FILE, "w", newline="") as f:
        writer = csv.DictWriter(f, fieldnames=["date", "category", "amount", "description"])
        writer.writeheader()
        writer.writerows(expenses[:-1])
    print(f"\n Deleted: ₹{removed['amount']} on {removed['date']} ({removed['category']})")


def main():
    print("Smart Expense Tracker")
    print("Track-Analyze-Save More")

    menu = {
        "1": ("Add an expense",       add_expense),
        "2": ("View all expenses",    view_all),
        "3": ("Monthly summary + chart", monthly_summary),
        "4": ("Delete last entry",    delete_last),
        "5": ("Exit",                 None),
    }

    while True:
        print("\n  What would you like to do?")
        for key, (label, _) in menu.items():
            print(f"    {key}. {label}")

        choice = input("\n  Your choice: ").strip()
        if choice == "5":
            print("\n  Goodbye! Keep tracking those expenses. \n")
            break
        elif choice in menu:
            menu[choice][1]()
        else:
            print("  Pick a number between 1 and 5.")


main()