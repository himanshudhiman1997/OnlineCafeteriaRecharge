package com.ajay.android.onlinecafeteriarecharge;

public class Items {
    String itemId;
    String itemName;
    String itemPrice;
    String itemQuantity;

    public Items()
    {

    }

    public Items(String itemId, String itemName, String itemPrice, String itemQuantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }
}
