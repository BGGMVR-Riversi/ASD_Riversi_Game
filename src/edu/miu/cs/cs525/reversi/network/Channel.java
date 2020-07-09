package edu.miu.cs.cs525.reversi.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

public class Channel implements Runnable {
	private DatagramSocket socket;

	public void bind(int port) throws SocketException {
		socket = new DatagramSocket(port);
	}

	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		socket.close();
	}

	@Override
	public void run() {
	}

	public void sendTo(SocketAddress address, String msg) throws IOException {
		byte[] buffer = msg.getBytes();
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		packet.setSocketAddress(address);
		System.out.println("Sent: " + msg);
		socket.send(packet);
	}

	public String receiveFrom() throws IOException {
		byte[] buffer = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		socket.receive(packet);
		String msg = new String(buffer, 0, packet.getLength());
		System.out.println("Received: " + msg);
		return new String(buffer, 0, packet.getLength());
	}
}
