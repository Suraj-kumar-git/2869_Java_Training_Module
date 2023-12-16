package com.hexaware.entity;

import java.util.Date;

public class Artist {
	private int artistId;
	private String name;
	private String biography;
	private Date birthdate;
	private String nationality;
	private String website;
	private String contactInformation;
	
	public Artist() {
		
	}
	public Artist(int artistId, String name, String biography, Date birthdate, String nationality, String website,
			String contactInformation) {
		this.artistId = artistId;
		this.name = name;
		this.biography = biography;
		this.birthdate = birthdate;
		this.nationality = nationality;
		this.website = website;
		this.contactInformation = contactInformation;
	}
	public int getArtistId() {
		return artistId;
	}
	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getContactInformation() {
		return contactInformation;
	}
	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}
	
	
}
