package main.java.edu.miu.cs.cs525.reversi.mediator;

public enum BoardEnum {
	ROW_COUNT(8), COL_COUNT(8), PLAYER_BLACK(1), PLAYER_WHITE(31), EMPTY(0), NO_GAME(-1), GAME_OVER(-2), MAX_MOVES(60),
	COMPUTER_PLAYER(1), NET_PLAYER(2), NO_SELECTION(-1);

	int value;

	BoardEnum(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}
}
