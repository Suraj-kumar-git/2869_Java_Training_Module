package com.hexaware.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class Inventory {
	private int inventoryID;
    private Product product;
    private int quantityInStock;
    private Date lastStockUpdate;
    private List<Product> products;
    private Map<Integer, Integer> productQuantities; // Map product ID to quantity

	public Inventory(int inventoryID, Product product, int quantityInStock, Date lastStockUpdate) {
//		super();
		this.inventoryID = inventoryID;
		this.product = product;
		this.quantityInStock = quantityInStock;
		this.lastStockUpdate = lastStockUpdate;
	}
	public Inventory() {
		
	}public Product getProductById(int productId) {
        for (Product product : products) {
            if (product.getProductID() == productId) {
                return product;
            }
        }
        return null; // Return null if the product with the given ID is not found
    }
	public void removeFromInventory(Product product, int quantityToRemove) {
        if (productQuantities.containsKey(product.getProductID())) {
            int currentQuantity = productQuantities.get(product.getProductID());

            if (currentQuantity >= quantityToRemove) {
                // Sufficient quantity in inventory, update the quantity
                productQuantities.put(product.getProductID(), currentQuantity - quantityToRemove);
                System.out.println("Removed " + quantityToRemove + " units of " + product.getProductName() + " from inventory.");
            } else {
                // Insufficient quantity in inventory
                System.out.println("Insufficient stock for " + product.getProductName() + ". Unable to fulfill the order.");
                // You may throw an exception or handle the situation based on your requirements
            }
        } else {
            // Product not found in inventory
            System.out.println("Product " + product.getProductName() + " not found in inventory.");
            // You may throw an exception or handle the situation based on your requirements
        }
    }
	public boolean isProductAvailable(Product product, int quantityToCheck) {
        if (productQuantities.containsKey(product.getProductID())) {
            int availableQuantity = productQuantities.get(product.getProductID());
            return availableQuantity >= quantityToCheck;
        } else {
            // Product not found in inventory
            System.out.println("Product " + product.getProductName() + " not found in inventory.");
            return false;
        }
    }
	public List<Product> searchProductByKeyword(String keyword) {
        List<Product> searchResults = new ArrayList<>();

        for (Product product : products) {
            if (containsKeyword(product, keyword)) {
                searchResults.add(product);
            }
        }

        return searchResults;
    }

    private boolean containsKeyword(Product product, String keyword) {
        String productName = product.getProductName().toLowerCase();
        String productDescription = product.getDescription().toLowerCase();
        keyword = keyword.toLowerCase();

        return productName.contains(keyword) || productDescription.contains(keyword);
    }
	
	public int getInventoryID() {
		return inventoryID;
	}
	public void setInventoryID(int inventoryID) {
		this.inventoryID = inventoryID;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	public Date getLastStockUpdate() {
		return lastStockUpdate;
	}
	public void setLastStockUpdate(Date lastStockUpdate) {
		this.lastStockUpdate = lastStockUpdate;
	}
	@Override
	public String toString() {
		return "Inventory [inventoryID=" + inventoryID + ", product=" + product + ", quantityInStock=" + quantityInStock
				+ ", lastStockUpdate=" + lastStockUpdate + "]";
	}
    
}
