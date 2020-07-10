package edu.miu.cs.cs525.reversi.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;

import edu.miu.cs.cs525.reversi.common.BoardInfo;
import edu.miu.cs.cs525.reversi.common.GeneralPlayer;
import edu.miu.cs.cs525.reversi.common.Location;

public class NetworkPlayer extends GeneralPlayer {

	static Channel channel = new Channel();
	static String hostAddress;
	int portNumber;
	static int portNumber2;
	static InetSocketAddress address;

	public NetworkPlayer(String hostAddress, int portNumber, int portNumber2) {
		try {
			channel.bind(portNumber);
			NetworkPlayer.hostAddress = hostAddress;
			this.portNumber = portNumber;
			NetworkPlayer.portNumber2 = portNumber2;
			// address = new InetSocketAddress(hostAddress, portNumber);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Location getMove(BoardInfo b) {

		try {
			// channel.bind(1112);
			channel.start();
			Location move = new Location();
			// address = new InetSocketAddress("localhost", 2341);
			// System.out.println(hostAddress+" "+portNumber2);
			address = new InetSocketAddress(hostAddress, portNumber2);
			if (b.getStandardFormGame() != null && !b.getStandardFormGame().isEmpty()) {
				String pos[] = b.getStandardFormGame().split(" ");
				// System.out.println(hostAddress + " " + pos[pos.length - 1]);
				channel.sendTo(address, pos[pos.length - 1]);
			}
			// channel.run();
			move.set(channel.receiveFrom());

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
