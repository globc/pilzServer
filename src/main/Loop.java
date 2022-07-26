package main;

public abstract class Loop implements Runnable {
	
	private double rate;
	private boolean running = false;
	
	public Loop(double rate) {
		this.rate = rate;
		
	}
	
	public final void start() {
		this.running = true;
		new Thread(this).start();
	}
	
	public final void stop() {
		this.running = false;
	}

	@Override
	public void run() {
		long timeLast = System.nanoTime();
		float theta = 0;
		
		this.running = true;
		while (running) {
			long timeNow = System.nanoTime();
			long elapsedTime = timeNow - timeLast;
			theta += elapsedTime / 1000000000.0f;
			timeLast = timeNow;
			
			while (theta >= rate) { // TODO if Hz = null use elapsedTime
				
				execute();
				
				theta -= rate;
			}
			
		}
		
		conclude();
	}
	
	public abstract void execute();
	
	/*
	 * Override function.
	 */
	public void conclude() {}
	
}
