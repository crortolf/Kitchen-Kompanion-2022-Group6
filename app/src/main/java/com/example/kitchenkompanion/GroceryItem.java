package com.example.kitchenkompanion;

public class GroceryItem {
    public String name, units;
    private boolean status;
    public float amount, minimumAmount;
    public String expirationDate;

    public GroceryItem(String name, String units, float amount) {
        this.name = name;
        this.units = units;
        this.amount = amount;
        this.status = false;
        minimumAmount = 0;
        expirationDate = "N/A";
    }

    public GroceryItem(String name, String units, float amount, float minimumAmount) {
        this(name, units, amount);
        this.minimumAmount = minimumAmount;
        this.expirationDate = "N/A";
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

    public void setExpirationDate(String expdate) {
        this.expirationDate = expdate;
    }

    public String getName() {
        return name;
    }
    public String getUnits() { return units; }
    public float getAmount() { return amount; }
    public boolean getStatus() { return status; }
}
