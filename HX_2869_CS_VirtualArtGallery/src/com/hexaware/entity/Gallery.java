package com.hexaware.entity;

public class Gallery {
	private int galleryId;
	private String name;
	private String desc;
	private String location;
	private int curator;
	private int openingHours;
	
	public Gallery(int galleryId, String name, String desc, String location, int curator, int openingHours) {
		this.galleryId = galleryId;
		this.name = name;
		this.desc = desc;
		this.location = location;
		this.curator = curator;
		this.openingHours = openingHours;
	}
	
	public Gallery() {
	}

	public int getGalleryId() {
		return galleryId;
	}

	public void setGalleryId(int galleryId) {
		this.galleryId = galleryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCurator() {
		return curator;
	}

	public void setCurator(int curator) {
		this.curator = curator;
	}

	public int getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(int openingHours) {
		this.openingHours = openingHours;
	}

	
	
}