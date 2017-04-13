package com.springinaction.springidol;

public class Cricketer implements Performer {

	int sixes;
	@Override
	public void perform() {
		System.out.println("Hitting "+sixes+" sixes");

	}
	public int getSixes() {
		return sixes;
	}
	public void setSixes(int sixes) {
		this.sixes = sixes;
	}

}
