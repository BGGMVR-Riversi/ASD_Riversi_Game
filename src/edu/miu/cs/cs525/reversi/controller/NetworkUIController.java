package edu.miu.cs.cs525.reversi.controller;

import edu.miu.cs.cs525.reversi.view.ReversiSingleton;
import edu.miu.cs.cs525.reversi.view.UIAdapter;
import edu.miu.cs.cs525.reversi.view.UITarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class NetworkUIController implements Initializable {

    private ReversiSingleton reversiSingleton;
    private UITarget boardUI = new UIAdapter();

    private String player1HostAddress;
    private String player1PortNumber;
    private String player2HostAddress;
    private String player2PortNumber;

    @FXML
    private ImageView palyer1ImageView;
    @FXML
    private ImageView palyer2ImageView;
    @FXML
    private TextField txtPlayer1HostAddress;
    @FXML
    private TextField txtPlayer1PortNumber;
    @FXML
    private TextField txtPlayer2HostAddress;
    @FXML
    private TextField txtPlayer2PortNumber;

    public void btnBackToHome() throws Exception{
        reversiSingleton = ReversiSingleton.getInstance();
        reversiSingleton.showMainView();
    }

    public void btnConnect() throws Exception{
        player1HostAddress = txtPlayer1HostAddress.getText().trim();
        player1PortNumber = txtPlayer1PortNumber.getText().trim();
        player2HostAddress = txtPlayer2HostAddress.getText().trim();
        player2PortNumber = txtPlayer2PortNumber.getText().trim();
        System.out.println(toString());
        boardUI.showBorderView();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("src/edu/miu/cs/cs525/reversi/image/computerToComputer.jpg");
        Image image = new Image(file.toURI().toString());
        palyer1ImageView.setImage(image);
        palyer2ImageView.setImage(image);
    }

    public void textFieldsOnFocus(){
    }

    @Override
    public String toString() {
        return "NetworkUIController {" +
                "player1HostAddress='" + player1HostAddress + '\'' +
                ", player1PortNumber='" + player1PortNumber + '\'' +
                ", player2HostAddress='" + player2HostAddress + '\'' +
                ", player2PortNumber='" + player2PortNumber + '\'' +
                '}';
    }
}
