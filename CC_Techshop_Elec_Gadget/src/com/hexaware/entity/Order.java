package com.hexaware.entity;

//import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Order {
	private int orderID;
//    private Customer customer;
	private int customerId;
    private LocalDate orderDate;
    private double totalAmount;
//    private List<OrderDetails> orderDetailsList;
    
	public Order(int orderID, int customerId, LocalDate orderDate, double totalAmount) {
		this.orderID = orderID;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
	}
	public Order() {
		
	}
	public Order(int customerID2, LocalDate orderDate2) {
		this.customerId=customerID2;
		this.orderDate=orderDate2;
	}
//	public void calculateTotalAmount() {
//        double totalAmount = 0.0;
//
//        for (OrderDetails orderDetails : orderDetailsList) {
//            // Assuming you have a getProduct() method in OrderDetails to get the associated Product
//            Product product = orderDetails.getProduct();
//
//            // Calculate the total amount for each OrderDetails item
//            totalAmount += orderDetails.getQuantity() * product.getPrice();
//        }
//
//        setTotalAmount(totalAmount); // Set the calculated total amount in the Order object
//    }

//    public boolean validateOrderDetails() {
//        for (OrderDetails orderDetails : orderDetailsList) {
//            // Validate each OrderDetails item, e.g., check if the quantity is non-negative
//            if (orderDetails.getQuantity() <= 0) {
//                System.out.println("Invalid quantity for productId: " + orderDetails.getProductId());
//                return false;
//            }
//
//            // Add more validation logic as needed...
//        }
//
//        return true; // If all validations pass
//    }
//
//    public List<OrderDetails> getOrderDetailsList() {
//        return orderDetailsList;
//    }
//	public Order(Customer customer) {
//		this.customer = customer;
//	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate localDate) {
		this.orderDate = localDate;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", customerID=" + customerId + ", orderDate=" + orderDate + ", totalAmount="
				+ totalAmount + "]";
	}
    
}
