package edu.miu.cs.cs525.reversi.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class BoardUI {

    private BorderPane borderUILayout;

    public void showBorderView() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BoardUI.class.getResource("BoardUI.fxml"));
        borderUILayout = loader.load();
        Scene scene = new Scene(borderUILayout);
        ReversiSingleton.stage.setScene(scene);
        ReversiSingleton.stage.show();
    }
}
