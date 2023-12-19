package com.hexaware.entity;

public class Dog extends Pet {
    private String dogBreed;

    // Constructor
    public Dog() {
    	
    }
    public Dog(String name, int age, String breed, String dogBreed) {
        super(name, age, breed);
        this.dogBreed = dogBreed;
    }
	public String getDogBreed() {
		return dogBreed;
	}
	public void setDogBreed(String dogBreed) {
		this.dogBreed = dogBreed;
	}
	@Override
	public String toString() {
		return "Dog [dogBreed=" + dogBreed + "]";
	}

    
}
