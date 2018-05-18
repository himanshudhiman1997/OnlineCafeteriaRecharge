package com.ajay.android.onlinecafeteriarecharge;

public class Card {

    String cardId;
    String uniqueName;
    String phoneNumber;
    String balance;

    public Card()
    {

    }

    public Card(String cardId, String uniqueName, String phoneNumber, String balance) {
        this.cardId = cardId;
        this.uniqueName = uniqueName;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public String getCardId() {
        return cardId;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBalance() {
        return balance;
    }
}
