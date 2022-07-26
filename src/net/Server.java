package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server implements Runnable{
	
	private static final int DEFAULT_PORT = 2222;
	
	private int port;
	private DatagramSocket socket;
	private boolean listening = true;
	
	public Server() {
		this(DEFAULT_PORT);
	}
	
	public Server(int port) {
		this.port = port;
		
		try {
			DatagramSocket socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		listening = true;
		
		
		new Thread(this).start();
	}

	@Override
	public void run() { /* listen on port */
		while (listening) {
			byte[] buf = new byte[256];
			DatagramPacket packet = new DatagramPacket(buf, buf.length); /* Packet for receiving */
			
			try {
				socket.receive(packet); /* Client's message copied to packet */
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			process(packet);
		}
		
	}

	public void stop() {
		// TODO Stop game, listening, broadcast. Save game state
	}
	
	private void process(DatagramPacket packet) {
		// TODO Process receiving packets
		
	}
	
}