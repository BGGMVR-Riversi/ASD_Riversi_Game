package main.java.edu.miu.cs.cs525.reversi.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.URL;

import main.java.edu.miu.cs.cs525.reversi.common.BoardInfo;
import main.java.edu.miu.cs.cs525.reversi.common.GeneralPlayer;
import main.java.edu.miu.cs.cs525.reversi.common.Location;

public class NetworkPlayer extends GeneralPlayer {

	static Channel channel = new Channel();
	static String hostAddress;
	int portNumber;
	static int portNumber2;
	static InetSocketAddress address;
	private TargetJson targetJson = new JsonAdapter();

	public NetworkPlayer(String hostAddress, int portNumber, int portNumber2) {
		try {
			channel.bind(portNumber);
			NetworkPlayer.hostAddress = hostAddress;
			this.portNumber = portNumber;
			NetworkPlayer.portNumber2 = portNumber2;
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Location getMove(BoardInfo b) {

		try {
			channel.start();
			Location move = new Location();
			address = new InetSocketAddress(hostAddress, portNumber2);
			if (b.getStandardFormGame() != null && !b.getStandardFormGame().isEmpty()) {
				String pos[] = b.getStandardFormGame().split(" ");
				//System.out.println("A "+hostAddress);
				if (hostAddress.startsWith("http")) {
					String result = channel.postRequest(new URL(hostAddress), "POST", pos[pos.length - 1]);
					System.out.println("B "+result);
					if (targetJson.isJson(result)) {
						System.out.println("AdapteeReceived: " + targetJson.JsontoString(result));
						move.set(targetJson.JsontoString(result));
					}

				} else {
					channel.sendTo(address, pos[pos.length - 1]);
					move.set(channel.receiveFrom());
				}
				
			}
			return move;
		} catch (IOException e) {
			channel.stop();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String identify() throws Exception {
		return "We good";
	}

	public static void getMove1() {
		try {
			if (hostAddress != null) {
				channel.start();
				address = new InetSocketAddress(hostAddress, portNumber2);
				channel.sendTo(address, "Game over!!!");
				channel.stop();
				return;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
