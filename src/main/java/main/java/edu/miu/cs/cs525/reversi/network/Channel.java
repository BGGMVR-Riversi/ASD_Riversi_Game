package main.java.edu.miu.cs.cs525.reversi.network;

import main.java.edu.miu.cs.cs525.reversi.utils.Utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Channel implements Runnable {
	private DatagramSocket socket;
	private TargetJson targetJson = new JsonAdapter();
	AdapteeJson adapteeJson= new AdapteeJson();
	Utils utils = new Utils();

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
		System.out.println(packet);
		socket.receive(packet);
		String packetString = new String(buffer, 0, packet.getLength());
		if (targetJson.isJson(packetString)){
			System.out.println("AdapteeReceived: " + targetJson.JsontoString(packetString));
			return targetJson.JsontoString(packetString);
		}
		String msg = new String(buffer, 0, packet.getLength());
		System.out.println("Received: " + msg);
		return new String(buffer, 0, packet.getLength());
	}
	
	
	public String postRequest(URL url, String requestMethod, String pos) throws UnsupportedEncodingException, IOException {
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestMethod(requestMethod);
		connection.setRequestProperty("Content-Type", "application/json; utf-8");
		connection.setRequestProperty("Accept", "application/json");
		char[] positions= pos.toCharArray();
		System.out.println("B "+pos);
		int y=Character.getNumericValue(positions[1])-1;
		
		String jsonInputString = "{\"x\":"+utils.charToInt(positions[0])+",\"y\":"+y+"}";
		System.out.println("C "+jsonInputString);
		StringBuilder response = new StringBuilder();
		byte[] postDataBytes = jsonInputString.toString().getBytes("UTF-8");
		connection.setDoOutput(true);
		try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
			DatagramPacket packet = new DatagramPacket(postDataBytes, postDataBytes.length);
			String msg = new String(postDataBytes, 0, packet.getLength());
			System.out.println("Sending: "+msg);
			writer.write(postDataBytes);
			writer.flush();
			writer.close();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {

				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				System.out.println("Receiving"+response.toString());
			}
		} finally {
			connection.disconnect();
		}
		return response.toString();
	}
}
