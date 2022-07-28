package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game extends Loop implements Serializable{

	private static final double DEFAULT_RATE = 1/60.0;
	List<Entity> entities = new ArrayList<>();
	
	public Game() {
		this(DEFAULT_RATE);
	}
	
	public Game(double rate) {
		super(rate);
	}

	@Override
	public void execute() {
		
	}

}
