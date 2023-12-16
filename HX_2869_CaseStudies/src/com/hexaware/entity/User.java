package com.hexaware.entity;

import java.util.Date;
import java.util.List;

public class User {
	private int userId;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private Date dob;
	private String dp;
	private List<Integer> favoriteArtworks;
	public User(int userId, String username, String password, String email, String firstName, String lastName, Date dob,
			String dp, List<Integer> favoriteArtworks) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.dp = dp;
		this.favoriteArtworks = favoriteArtworks;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getDp() {
		return dp;
	}
	public void setDp(String dp) {
		this.dp = dp;
	}
	public List<Integer> getFavoriteArtworks() {
		return favoriteArtworks;
	}
	public void setFavoriteArtworks(List<Integer> favoriteArtworks) {
		this.favoriteArtworks = favoriteArtworks;
	}
	
	
}