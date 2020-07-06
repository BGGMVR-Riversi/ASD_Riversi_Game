package edu.miu.cs.cs525.reversi.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class PlayersUI {

    private BorderPane playersLayout;

    public void showPlayersUIView() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Launch.class.getResource("PlayersUI.fxml"));
        playersLayout = loader.load();
        Scene scene = new Scene(playersLayout);
        ReversiSingleton.stage.setScene(scene);
        ReversiSingleton.stage.show();
    }
}
