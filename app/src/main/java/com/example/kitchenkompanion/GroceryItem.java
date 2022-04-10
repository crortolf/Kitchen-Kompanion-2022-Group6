package com.example.kitchenkompanion;

public class GroceryItem {
    public String name, units;
    private boolean status;
    public float amount, minimumAmount;

    public GroceryItem(String name, String units, float amount) {
        this.name = name;
        this.units = units;
        this.amount = amount;
        this.status = false;
        minimumAmount = 0;
    }

    public GroceryItem(String name, String units, float amount, float minimumAmount) {
        this(name, units, amount);
        this.minimumAmount = minimumAmount;
    }

    public String toString() {
        String amountS, minimumAmountS;
        amountS = (amount % 1.0 == 0.0) ? String.valueOf((int) amount) : String.valueOf(amount);
        minimumAmountS = (minimumAmount % 1.0 == 0.0) ? String.valueOf((int) minimumAmount) : String.valueOf(minimumAmount);
        return amountS + " (min " + minimumAmountS + ") " + units  + "(s) of " + name;
    }

    public String amountToString() {
        String amountS;
        amountS = (amount % 1.0 == 0.0) ? String.valueOf((int) amount) : String.valueOf(amount);
        return amountS + units;
    }

    public String getName() {
        return name;
    }
    public String getUnits() { return units; }
    public float getAmount() { return amount; }
    public boolean getStatus() { return status; }
}
