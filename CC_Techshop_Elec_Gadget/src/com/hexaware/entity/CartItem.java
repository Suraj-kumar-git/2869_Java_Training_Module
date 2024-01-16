package com.hexaware.entity;

public class CartItem {
	private int cartItemId;
	private int productId;
	private String productName;
	private int quantity;
	private double totalAmount;
	private double customerId;

	public CartItem(int productId, String productName, int quantity, double totalAmount, double customerId) {
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.customerId = customerId;
	}

	public CartItem() {
	}

	public double getCustomerId() {
		return customerId;
	}

	public void setCustomerId(double customerId) {
		this.customerId = customerId;
	}

	public Product getProduct() {
		return new Product(this.productId, this.productName, this.totalAmount / this.quantity);
	}

//		public void setProduct(Product product) {
//			this.product = product;
//		}

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "CartItem [cartItemId=" + cartItemId + ", productId=" + productId + ", productName=" + productName
				+ ", quantity=" + quantity + ", totalAmount=" + totalAmount + "]";
	}

}
