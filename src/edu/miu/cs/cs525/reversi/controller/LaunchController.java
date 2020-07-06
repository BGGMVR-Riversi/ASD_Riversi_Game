package edu.miu.cs.cs525.reversi.controller;

import edu.miu.cs.cs525.reversi.view.Launch;
import edu.miu.cs.cs525.reversi.view.NetworkUI;
import edu.miu.cs.cs525.reversi.view.PlayersUI;
import edu.miu.cs.cs525.reversi.view.ReversiSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LaunchController implements Initializable {

    private PlayersUI playersUI = new PlayersUI();

    @FXML
    private ImageView imageView;

    public void btnNewGame() throws Exception {
        playersUI.showPlayersUIView();
    }

    public void btnAbout(){

    }

    public void btnRules(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("src/edu/miu/cs/cs525/reversi/image/reversi-game.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }
}
