package edu.miu.cs.cs525.reversi.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardUIController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
