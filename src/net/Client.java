package net;

import java.net.InetAddress;

import main.Player;

public class Client {

	public final InetAddress ip;
	public final int port;
	
	private final Player player;
	
	public Client(InetAddress ip, int port, Player player) {
		this.ip = ip;
		this.port = port;
		this.player = player;
	}
	
}
