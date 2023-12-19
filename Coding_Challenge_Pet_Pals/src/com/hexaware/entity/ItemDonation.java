package com.hexaware.entity;

public class ItemDonation extends Donation {
    private String itemType;

    // Constructor
    public ItemDonation(String donorName, double amount, String itemType) {
        super(donorName, amount);
        this.itemType = itemType;
    }
    public ItemDonation() {
    	
    }
    

    @Override
	public String toString() {
		return "ItemDonation [itemType=" + itemType + "]";
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	// Implementation of RecordDonation
    @Override
    public void recordDonation() {
        System.out.println("Item Donation - Type: " + itemType + ", Amount: " + getAmount());
    }
}
