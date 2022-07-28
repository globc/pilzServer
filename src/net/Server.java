package net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import main.Game;
import main.Loop;

public class Server implements Runnable{
	
	private static final int DEFAULT_PORT = 2222;
	
	private int port;
	private DatagramSocket socket;
	private DatagramPacket recPacket = new DatagramPacket(new byte[256], 256);
	private boolean listening = true;
	
	private Game game;
	
	private final List<Client> clients = new ArrayList<>();
	
	/* HashMap username -> Player */
	
	public Server() {
		this(DEFAULT_PORT);
	}
	
	public Server(int port) {
		this.game = new Game();
		
		
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
			
			try {
				socket.receive(recPacket); /* Client's message copied to packet */
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			process(recPacket);
		}
		
	}

	public void stop() {
		// TODO Stop game, listening, broadcast. Save game state
	}
	
	private void process(DatagramPacket packet) {
		byte[] data = packet.getData();
		
	}
	
	public class Broadcast extends Loop {

		private static final double DEFAULT_RATE = 1/20.0;
		
		public Broadcast() {
			this(DEFAULT_RATE);
		}
		
		public Broadcast(double rate) {
			super(rate);
		}
		
		private byte[] getUpdate(Client client) {
			// TODO filter unnecessary data (i.e. not visible to client, inventories,..) & unchanged data (delta compression)
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(game);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return baos.toByteArray();
		}

		@Override
		public void execute() {
			for (Client client : clients) {
				byte[] update = getUpdate(client);
				DatagramPacket sendPacket = new DatagramPacket(update, update.length, client.ip, client.port);
				
				try {
					socket.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
}