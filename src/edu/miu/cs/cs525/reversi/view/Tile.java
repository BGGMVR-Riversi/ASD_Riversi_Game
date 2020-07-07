package edu.miu.cs.cs525.reversi.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static edu.miu.cs.cs525.reversi.view.UIAdaptee.TILE_SIZE;

public class Tile extends Rectangle {
    private Piece piece;

    public Tile (boolean light, int x, int y) {
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);

        relocate(x * TILE_SIZE, y * TILE_SIZE);

        setStroke(Color.BLACK);
        setFill(Color.valueOf("#347D39"));

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
