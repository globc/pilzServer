package main;

public class Game extends Loop{

	private static final double DEFAULT_RATE = 1/60.0;
	
	public Game() {
		this(DEFAULT_RATE);
	}
	
	public Game(double rate) {
		super(rate);
	}

	@Override
	public void execute() {
		
	}
	
	public Game getState() {
		return this;
	}

}
