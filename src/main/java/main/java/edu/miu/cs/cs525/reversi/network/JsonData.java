package main.java.edu.miu.cs.cs525.reversi.network;

public class JsonData {
	public void setX(Integer x) {
		this.x = x;
	}

	private Integer x;
	private String row;

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public Integer getCol() {
		return col;
	}

	public void setCol(Integer col) {
		this.col = col;
	}

	private Integer col;

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	private Integer y;
}
