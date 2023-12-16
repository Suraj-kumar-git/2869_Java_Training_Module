package com.hexaware.entity;

import java.time.LocalDate;
import java.util.Date;

public class Artwork {
	private int artworkId;
	private String title;
	private String desc;
	private LocalDate creationDate;
	private String medium;
	private String imageURL;
	
	public Artwork() {
		
	}
	
	public Artwork(int artworkId, String title, String desc, LocalDate date, String medium, String imageURL) {
		this.artworkId = artworkId;
		this.title = title;
		this.desc = desc;
		this.creationDate = date;
		this.medium = medium;
		this.imageURL = imageURL;
	}
	public int getArtworkId() {
		return artworkId;
	}
	public void setArtworkId(int artworkId) {
		this.artworkId = artworkId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public LocalDate getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	@Override
	public String toString() {
		return "Artwork [artworkId=" + artworkId + ", title=" + title + ", desc=" + desc + ", creationDate="
				+ creationDate + ", medium=" + medium + ", imageURL=" + imageURL + "]";
	}
}
