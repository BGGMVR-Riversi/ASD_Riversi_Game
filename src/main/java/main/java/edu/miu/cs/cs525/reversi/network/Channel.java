package main.java.edu.miu.cs.cs525.reversi.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import main.java.edu.miu.cs.cs525.reversi.utils.Convert;
import main.java.edu.miu.cs.cs525.reversi.utils.ConvertToInt;

public class Channel implements Runnable {
	private DatagramSocket socket;
	private TargetJson targetJson = new JsonAdapter();
	AdapteeJson adapteeJson = new AdapteeJson();
	Convert convert = new ConvertToInt();

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
        char[] positions = msg.toCharArray();
        int y = Character.getNumericValue(positions[1]) - 1;
       /**************use this for local testing************************************/
        //String jsonInputString = "{\"x\":" + utils.charToInt(positions[0]) + ",\"y\":" + y + "}";
        /*********** Team 5 Json InputString format******************************/
        String jsonInputString = "{\"x\":" + y + ",\"y\":" + convert.stringToInt(positions[0]) + "}";
        System.out.println("sent from json " + jsonInputString);
        byte[] buffer = jsonInputString.getBytes();

		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		packet.setSocketAddress(address);
		System.out.println("Sent: " + msg);
		socket.send(packet);
	}

	public String receiveFrom() throws IOException {
		byte[] buffer = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		// System.out.println("Recieved packet: "+packet);
		socket.receive(packet);
		String packetString = new String(buffer, 0, packet.getLength());
		if (targetJson.isJson(packetString)) {
			System.out.println("AdapteeReceived: " + targetJson.JsontoString(packetString));
			return targetJson.JsontoString(packetString);
		}
		String msg = new String(buffer, 0, packet.getLength());
		System.out.println("Received: " + msg);
		return new String(buffer, 0, packet.getLength());
	}

    public String postRequest(URL url, String requestMethod, String pos)
            throws UnsupportedEncodingException, IOException {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod(requestMethod);
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        String jsonInputString="";
        if(pos.equals("-1-1")){
           jsonInputString = "{\"row\":" + -1 + ",\"col\":" + -1 + "}";

        }
      else {
            char[] positions = pos.toCharArray();
            System.out.println("B " + pos);
            int y = Character.getNumericValue(positions[1]) - 1;
            /***************  jsonInputString for Team 6****************************************/
            //String jsonInputString = "{\"x\":" + utils.charToInt(positions[0]) + ",\"y\":" + y + "}";
            /************************ jsonInputString for Team 1 and 3 use below one************************/
            //both ways can be used for different board views or similar for team1
            //String jsonInputString = "{\"row\":" + utils.charToInt(positions[0]) + ",\"col\":" + y + "}";
            jsonInputString = "{\"row\":" + y + ",\"col\":" + convert.stringToInt(positions[0]) + "}";
            System.out.println("C " + jsonInputString);
        }
        StringBuilder response = new StringBuilder();
        byte[] postDataBytes = jsonInputString.toString().getBytes("UTF-8");
        connection.setDoOutput(true);
        try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
            DatagramPacket packet = new DatagramPacket(postDataBytes, postDataBytes.length);
            String msg = new String(postDataBytes, 0, packet.getLength());
            System.out.println("Sending: " + msg);
            writer.write(postDataBytes);
            writer.flush();
            writer.close();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {

                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Receiving" + response.toString());
            }
        } finally {
            connection.disconnect();
        }
        return response.toString();
    }

    /****************************** Added  For Team 6**************************************************/
    public String postRequestTeam6(URL url, String requestMethod, String pos)
            throws UnsupportedEncodingException, IOException {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod(requestMethod);
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        char[] positions = pos.toCharArray();
        System.out.println("B " + pos);
        int y = Character.getNumericValue(positions[1]) - 1;
        /***************  jsonInput Team 6****************************************/
        String jsonInputString = "{\"x\":" + convert.stringToInt(positions[0]) + ",\"y\":" + y + "}";
        System.out.println("CTem6 " + jsonInputString);
        StringBuilder response = new StringBuilder();
        byte[] postDataBytes = jsonInputString.toString().getBytes("UTF-8");
        connection.setDoOutput(true);
        try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
            DatagramPacket packet = new DatagramPacket(postDataBytes, postDataBytes.length);
            String msg = new String(postDataBytes, 0, packet.getLength());
            System.out.println("SendingTeam6: " + msg);
            writer.write(postDataBytes);
            writer.flush();
            writer.close();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {

                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("ReceivingTeam6" + response.toString());
            }
        } finally {
            connection.disconnect();
        }
        return response.toString();
    }


    public String getRequestTeam2(URL url, String requestMethod) throws UnsupportedEncodingException, IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(requestMethod);
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        StringBuilder content = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            content = new StringBuilder();
            while ((line = input.readLine()) != null) {
                // Append each line of the response and separate them
                content.append(line);
                content.append(System.lineSeparator());
            }
        } finally {
            connection.disconnect();
        }
        return content.toString();
    }
}
