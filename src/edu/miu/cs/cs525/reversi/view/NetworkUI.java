package edu.miu.cs.cs525.reversi.view;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;

public class NetworkUI {

    private BorderPane networkLayout;

    public void showNtkUIView() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Launch.class.getResource("NetworkUI.fxml"));
        networkLayout = loader.load();
        Scene scene = new Scene(networkLayout);
        ReversiSingleton.stage.setScene(scene);
        ReversiSingleton.stage.show();
    }
}
