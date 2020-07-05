package edu.miu.cs.cs525.reversi.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LaunchController implements Initializable {

    public Button button;

    @FXML
    private ImageView imageView;

    public void btnComputerToComputer(){
        System.out.println("You choose computer to computer option!!!");
    }

    public void btnHumanToComputer(){
        System.out.println("You choose human to computer option!!!");
    }

    public void btnHumanToHuman(){
        System.out.println("You choose human to human option!!!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("src/edu/miu/cs/cs525/reversi/image/reversi-game.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }
}
