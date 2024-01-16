package com.hexaware.entity;

public class OrderDetails {
	private int orderDetailID;
    private int orderId;
    private int productId;
    private int quantity;
	public OrderDetails(int orderDetailID, int orderId,int productId, int quantity) {
//		super();
		this.orderDetailID = orderDetailID;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}
    public OrderDetails() {
    	
    }
	
	public OrderDetails(int productId, int quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public OrderDetails(int order, int product, int quantity) {
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}
	public int getOrderDetailID() {
		return orderDetailID;
	}
	public void setOrderDetailID(int orderDetailID) {
		this.orderDetailID = orderDetailID;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderDetails [orderDetailID=" + orderDetailID + ", orderId=" + orderId + ", productId=" + productId
				+ ", quantity=" + quantity + "]";
	}
}
