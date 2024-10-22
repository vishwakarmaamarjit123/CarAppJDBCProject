package com.alpha.carapp.dto;

public class Car {
	
	private int id;
	private String name;
	private String brand;
	private double cost;
	private int manufactureYear;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getManufactureYear() {
		return manufactureYear;
	}
	public void setManufactureYear(int manufactureYear) {
		this.manufactureYear = manufactureYear;
	}
	@Override
	public String toString() {
		return "Car [id=" + id + ", name=" + name + ", brand=" + brand + ", cost=" + cost + ", manufactureYear="
				+ manufactureYear + "]";
	}
	
	
	
	

}
