package com.auditory.util;

public class IdentifierPool {
	private int id = 0;
	
	public int getId() {
		return id++;
	}
}
