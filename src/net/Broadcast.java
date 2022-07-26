package net;

import main.Loop;

public class Broadcast extends Loop{

private static final double DEFAULT_RATE = 1/60.0;
	
	public Broadcast() {
		this(DEFAULT_RATE);
	}
	
	public Game(double rate) {
		super(rate);
	}

	@Override
	public void execute() {
		
	}

}
