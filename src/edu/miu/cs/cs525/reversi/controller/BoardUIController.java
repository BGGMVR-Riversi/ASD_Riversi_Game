package edu.miu.cs.cs525.reversi.controller;

import edu.miu.cs.cs525.reversi.model.Piece;
import edu.miu.cs.cs525.reversi.model.PieceType;
import edu.miu.cs.cs525.reversi.model.Tile;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class BoardUIController implements Initializable {

    public static final int TILE_SIZE = 60;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Tile boardMatrix[][] = new Tile[WIDTH][HEIGHT];

    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();

    @FXML
    private ImageView imgFirst;
    @FXML
    private ImageView imgLast;
    @FXML
    private ImageView imgPrevious;
    @FXML
    private ImageView imgNext;
    @FXML
    private ImageView imgPlay;
    @FXML
    private StackPane stackPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File fileFirst = new File("src/edu/miu/cs/cs525/reversi/image/first.png");
        File fileLast = new File("src/edu/miu/cs/cs525/reversi/image/last.png");
        File filePrevious = new File("src/edu/miu/cs/cs525/reversi/image/prev.png");
        File fileNext = new File("src/edu/miu/cs/cs525/reversi/image/next.png");
        File filePlay = new File("src/edu/miu/cs/cs525/reversi/image/play.png");

        Image first = new Image(fileFirst.toURI().toString());
        Image last = new Image(fileLast.toURI().toString());
        Image previous = new Image(filePrevious.toURI().toString());
        Image next = new Image(fileNext.toURI().toString());
        Image play = new Image(filePlay.toURI().toString());

        imgFirst.setImage(first);
        imgLast.setImage(last);
        imgPrevious.setImage(previous);
        imgNext.setImage(next);
        imgPlay.setImage(play);

        createBoard();
    }

    private void createBoard() {

        stackPane.getChildren().addAll(tileGroup,pieceGroup);

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                boolean isLight = (x + y) % 2 == 0;
                Tile tile = new Tile(isLight, x, y);

                boardMatrix[x][y] = tile;

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
//        return pane;
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

    @FXML
    public void handleNewGame() {
        System.out.println("Start New Game...");
    }

    @FXML
    public void handleSaveState() {
        System.out.println("Save game state...");
    }

    @FXML
    public void handleLoadState() {
        System.out.println("Load previous game state...");
    }

    @FXML
    public void handleExitGame() {
        System.out.println("Exiting game...");
        Platform.exit();
        System.exit(0);
    }

}
