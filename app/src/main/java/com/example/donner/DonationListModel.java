package com.example.donner;

public class DonationListModel {
    private String donationType;
    private int quantity;

public DonationListModel (){}

    public DonationListModel(String donationType, int quantity) {
        this.donationType = donationType;
        this.quantity = quantity;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
