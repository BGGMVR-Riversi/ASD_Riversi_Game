package edu.miu.cs.cs525.reversi.model;

import edu.miu.cs.cs525.reversi.view.UIAdaptee;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static edu.miu.cs.cs525.reversi.view.UIAdaptee.TILE_SIZE;

public class Tile extends Rectangle {
    private UIAdaptee ui;
    private Piece piece;

    private double mouseX, mouseY;
    private double oldX, oldY;

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public Tile () {}

    public Tile (boolean light, int x, int y) {
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);

        relocate(x * TILE_SIZE, y * TILE_SIZE);

        setStroke(Color.BLACK);
        setFill(Color.valueOf("#347D39"));

    }

    public void move(int x, int y) {
        oldX = x * TILE_SIZE;
        oldY = y * TILE_SIZE;
        relocate(oldX, oldY);
    }

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
