package edu.miu.cs.cs525.reversi.controller;

import edu.miu.cs.cs525.reversi.view.NetworkUI;
import edu.miu.cs.cs525.reversi.view.ReversiSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayersController implements Initializable {

    private NetworkUI networkUI = new NetworkUI();
    private static ReversiSingleton reversiSingletonObj = ReversiSingleton.getInstance();

    @FXML
    private ImageView imageView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("src/edu/miu/cs/cs525/reversi/image/players.gif");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    public void btnComputerToComputer() throws Exception{
        networkUI.showNtkUIView();
    }

    public void btnHumanToComputer(){
        System.out.println("You choose human to computer option!!!");
    }

    public void btnHumanToHuman(){
        System.out.println("You choose human to human option!!!");
    }

    public void btnHome() throws Exception {
        reversiSingletonObj.showMainView();
    }
}
