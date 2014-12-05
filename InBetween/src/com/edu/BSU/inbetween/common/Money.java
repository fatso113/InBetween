package com.edu.BSU.inbetween.common;


public class Money {
	private int value;

	public Money(int money) {
		this.value = money;
	}
	
	public int getAmount(){
		return this.value;
	}

	public void add(int wonMoney){
		this.value += wonMoney;
	}
		
	public void subtract(int lostMoney) {
		this.value -= lostMoney;
	}
	
}