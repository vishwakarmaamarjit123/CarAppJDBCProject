package com.alpha.carapp.controller;

public class Singleton {
	static Singleton singleton = null;
	
	public static Singleton getSingleton() {
		if(singleton == null) { 
			singleton = new Singleton();
			return singleton;
		}
		else {
			return null;
		}
	}
		
	
	private Singleton() {
		System.out.println("hii");
	}
	
}
