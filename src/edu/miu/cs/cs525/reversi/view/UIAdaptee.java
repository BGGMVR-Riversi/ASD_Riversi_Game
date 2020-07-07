package edu.miu.cs.cs525.reversi.view;

import edu.miu.cs.cs525.reversi.model.Piece;
import edu.miu.cs.cs525.reversi.model.PieceType;
import edu.miu.cs.cs525.reversi.model.Tile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class UIAdaptee {

    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private BorderPane commonBorderPaneUI;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();

    private Parent createBoard() {

        Pane boardpane = new Pane();
        boardpane.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        boardpane.getChildren().addAll(tileGroup, pieceGroup);
        commonBorderPaneUI.setPrefSize(940, HEIGHT * TILE_SIZE);
//        commonBorderPaneUI.getChildren().addAll(pieceGroup);
//        commonBorderPaneUI.setCenter(boardpane);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                boolean isLight = (x + y) % 2 == 0;
                Tile tile = new Tile(isLight, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);

                Piece piece = null;

                if (x == 3 && y == 3) {
                    piece = makePiece(PieceType.WHITE, 3, 3);
                } else if (x == 3 && y == 4) {
                    piece =  makePiece(PieceType.BLACK, 3, 4);
                } else if (x == 4 && y == 3) {
                    piece = makePiece(PieceType.BLACK, 4, 3);
                } else if (x == 4 && y == 4) {
                    piece = makePiece(PieceType.WHITE, 4, 4);
                } else {

                }

                if (piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }

            }
        }
        return commonBorderPaneUI;
    }

    public int toBoard(double pixel) {
        return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE - 1;
    }

    public Piece makePiece(PieceType type, int x, int y) {
        Piece piece = new Piece(type, x, y);

        tileGroup.setOnMouseClicked(e -> {
            int posX = toBoard(e.getSceneX());
            int posY = toBoard(e.getSceneY());
            System.out.println("x=" + posX + ", y=" + posY);
            pieceGroup.getChildren().add(new Piece(type, posX, posY));
        });

        return piece;
    }

    public void showBorderView() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(UIAdaptee.class.getResource("BoardUI.fxml"));
        commonBorderPaneUI = loader.load();
//        Scene scene = new Scene(commonBorderPaneUI);
//        ReversiSingleton.stage.setScene(scene);
        Scene scene = new Scene(createBoard());
        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        ReversiSingleton.stage.setScene(scene);
        ReversiSingleton.stage.show();
    }

    public void showNtkUIView() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(UIAdaptee.class.getResource("NetworkUI.fxml"));
        commonBorderPaneUI = loader.load();
        Scene scene = new Scene(commonBorderPaneUI);
        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        ReversiSingleton.stage.setScene(scene);
        ReversiSingleton.stage.show();
    }

    public void showPlayersUIView() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(UIAdaptee.class.getResource("PlayersUI.fxml"));
        commonBorderPaneUI = loader.load();
        Scene scene = new Scene(commonBorderPaneUI);
        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        ReversiSingleton.stage.setScene(scene);
        ReversiSingleton.stage.show();
    }
}
