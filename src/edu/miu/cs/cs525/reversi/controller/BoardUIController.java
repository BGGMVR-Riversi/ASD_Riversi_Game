package edu.miu.cs.cs525.reversi.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class BoardUIController implements Initializable {

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
