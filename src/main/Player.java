package main;

public class Player extends Entity{

	private final String username;
	
	public Player(String username) {
		this.username = username;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Player) {
			return ((Player) obj).username.equals(this.username);
		}
		
		return false;
	}
	
}
