# 🚖 FareCalc Travel Optimizer

A Python-based fare calculation system for a ride-sharing service.
This project simulates how cab fares are dynamically calculated based on distance, vehicle type, and surge pricing (peak hours).

---

## 📌 Features

* 🚗 Multiple vehicle options:

  * Economy
  * Premium
  * SUV
* 📊 Fare calculation based on **distance (per km rate)**
* ⚡ Surge pricing during peak hours (5 PM – 8 PM)
* 🔁 Continuous booking using loop (multiple rides)
* ⚠️ Error handling for invalid inputs
* 🧾 Clean formatted ride receipt

---

## ⚙️ Technologies Used

* Python 3
* Console-based application

---

## 🧠 Concepts Covered

* Dictionary mapping (`vehicle_rates`)
* Functions (`calculate_fare`)
* Conditional statements (`if`, `else`)
* Loops (`while`)
* Error handling (`try-except`)
* String formatting

---

## 🚀 How to Run

### 1️⃣ Open terminal in project folder

```bash
cd FareCalc
```

### 2️⃣ Run the script

```bash
python farecalc.py
```

---

## 📸 Output Screenshot

![FareCalc Output](citycafarecalcoutput.png)

---

## 📊 Sample Flow

1. Select vehicle type
2. Enter distance (km)
3. Enter booking hour (0–23)
4. System calculates fare:

   * Applies surge if time is between 17–20
5. Displays formatted receipt

---

## 💡 Example Calculation

* Vehicle: SUV
* Distance: 20 km
* Rate: ₹25/km → ₹500
* Peak Hour (Surge 1.5x): ₹750
* Final Fare: ₹750

---

## 🎯 Use Case

This system can be used in:

* Ride-sharing apps (like Uber/Ola)
* Travel cost estimation tools
* Backend pricing systems

---




